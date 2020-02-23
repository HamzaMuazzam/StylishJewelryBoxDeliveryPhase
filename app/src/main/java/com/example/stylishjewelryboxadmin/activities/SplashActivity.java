package com.example.stylishjewelryboxadmin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.utils.Utils;

import static com.example.stylishjewelryboxadmin.activities.LoginActivityActivity.NAME;
import static com.example.stylishjewelryboxadmin.activities.LoginActivityActivity.PHONENUMBER;
import static com.example.stylishjewelryboxadmin.activities.LoginActivityActivity.UUID;

public class SplashActivity extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initviews();
        handler();
    }

    private void handler() {
        new Handler().postDelayed(() ->
                        checklogindetails(),
                2000);
    }

    private void checklogindetails() {
        String uuid = Utils.getPreferences(UUID, this);
        String number = Utils.getPreferences(PHONENUMBER, this);
        String name = Utils.getPreferences(NAME, this);
        if (!uuid.equalsIgnoreCase("null") && !name.equalsIgnoreCase("null") && !number.equalsIgnoreCase("null")) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivityActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    private void initviews() {
        progressBar = findViewById(R.id.progress_splash);

    }
}
