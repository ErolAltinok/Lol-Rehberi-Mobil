package com.example.ErolMobil;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Üstteki gri Action Bar'ı gizliyorum.
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_favorites);

        LinearLayout container = findViewById(R.id.favoritesContainer);
        DatabaseHelper db = new DatabaseHelper(this);

        ArrayList<HashMap<String, String>> taktikler = db.getAllTactics();

        if (taktikler.isEmpty()) {
            TextView tv = new TextView(this);
            tv.setText("Henüz Vadi'den bir taktik kaydetmedin.");
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(16);
            container.addView(tv);
        } else {
            for (HashMap<String, String> t : taktikler) {
                TextView tv = new TextView(this);
                tv.setText("• " + t.get("sampiyon") + "\n  " + t.get("taktik") + "\n");
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(16);
                tv.setPadding(0, 0, 0, 30);
                container.addView(tv);
            }
        }
    }

    // XML'deki geri butonuna basıldığında tetiklenecek
    public void geriDon(View view) {
        finish(); // Bu ekranı kapatır ve hafızadan silerek ana ekrana döndürürr
    }

    // XML'deki tümünü sil butonuna basıldığında tetiklenecek fonksiyon
    public void hepsiniSil(View view) {
        DatabaseHelper db = new DatabaseHelper(this);
        db.deleteAllTactics();

        // Veriler silindikten sonra ekranın güncellenmesi için sayfayı kapatıp tekrar anında açıyoruz
        finish();
        startActivity(getIntent());
    }
}