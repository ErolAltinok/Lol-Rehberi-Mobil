package com.example.ErolMobil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 3 saniye beklettik
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

            // Kullanıcı geri tuşuna bastığında tekrar bu ekrana dönmemesi için kapatıyoruz
            finish();
        }, 3000);
    }
}