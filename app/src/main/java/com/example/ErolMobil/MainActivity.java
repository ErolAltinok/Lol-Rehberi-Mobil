package com.example.ErolMobil;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuote, tvStatus;
    private EditText etCharacterName;
    private Button btnSave, btnRefresh;
    private DatabaseHelper dbHelper;

    // API'den çektiğimiz son bilgileri tutmak için değişkenler
    private String guncelSampiyon = "Bilinmiyor";
    private String guncelTaktik = "Bilinmiyor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuote = findViewById(R.id.tvQuote);
        tvStatus = findViewById(R.id.tvStatus);
        etCharacterName = findViewById(R.id.etCharacterName);
        btnSave = findViewById(R.id.btnSave);
        btnRefresh = findViewById(R.id.btnRefresh);

        dbHelper = new DatabaseHelper(this);

        // Bildirim izinleri
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        // Uygulama açılır açılmaz ilk veriyi çekiyorum
        apiVerisiniCek();

        // KAYDET BUTONU
        btnSave.setOnClickListener(v -> {
            String manuelGirdi = etCharacterName.getText().toString().trim();

            // Eğer kutuya bir şey yazıldıysa onu al, boşsa API'den gelen son bilgiyi kullan
            if (!manuelGirdi.isEmpty()) {
                guncelTaktik = manuelGirdi;
                guncelSampiyon = "Özel Taktik";
            }

            boolean basarili = dbHelper.addTactic(guncelSampiyon, guncelTaktik);

            if (basarili) {
                tvStatus.setText("Vadi'ye kaydedildi!");
                etCharacterName.setText(""); // Kutuyu temizle
                widgetGuncelle();
            } else {
                tvStatus.setText("Kaydedilirken hata oluştu.");
            }
        });

        // YENİLE BUTONU
        btnRefresh.setOnClickListener(v -> {
            tvQuote.setText("Riot sunucularına bağlanılıyor...");
            apiVerisiniCek();
        });
    }

    private void apiVerisiniCek() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Karmaşık hata bloklarını burada gizledik, asıl işlemleri metodun içine aldımö
                riotSunucusunaBaglan();
            } catch (Exception ignored) {}
        });
    }

    private void riotSunucusunaBaglan() throws Exception {
        // Riot Data Dragon API'sine bağlandık
        URL url = new URL("https://ddragon.leagueoflegends.com/cdn/13.24.1/data/tr_TR/champion.json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Gelen JSON verilerini okuduk
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // JSON verisini parçala ve devasa listeden rastgele bir şampiyon seç
        JSONObject jsonObject = new JSONObject(response.toString());
        JSONObject dataObj = jsonObject.getJSONObject("data");

        JSONArray keys = dataObj.names();
        int randomInt = new Random().nextInt(keys.length());
        String randomKey = keys.getString(randomInt);

        // Şampiyonun adını ve kısa hikayesini çektik
        JSONObject champObj = dataObj.getJSONObject(randomKey);
        guncelSampiyon = champObj.getString("name");
        guncelTaktik = champObj.getString("blurb");

        // Ekranı güncelledik
        runOnUiThread(() -> tvQuote.setText(guncelSampiyon + "\n\n" + guncelTaktik));
    }

    private void widgetGuncelle() {
        Intent intent = new Intent(this, MyWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] ids = AppWidgetManager.getInstance(getApplication())
                .getAppWidgetIds(new ComponentName(getApplication(), MyWidgetProvider.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);
    }

    //Bu metodu XML tasarımında Favoriler butonuna tıkladığımızda kullanacağız
    public void favorilereGit(View view) {
        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
        startActivity(intent);
    }
}