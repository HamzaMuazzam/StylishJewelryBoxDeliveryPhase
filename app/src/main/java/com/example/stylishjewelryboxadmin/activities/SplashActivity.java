package com.example.stylishjewelryboxadmin.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.utils.Utils;

import static com.example.stylishjewelryboxadmin.activities.LoginActivityActivity.NAME;
import static com.example.stylishjewelryboxadmin.activities.LoginActivityActivity.PHONENUMBER;
import static com.example.stylishjewelryboxadmin.activities.LoginActivityActivity.UUID;

public class SplashActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView tv_splasglogo;
    private int MY_REQ_CODE = 1001;
    private boolean mpermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //here we have to call a predefine method to request permission

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            initviews();
            handler();
        } else {

            checkReadPhoneStatePermission();

        }

    }

    private void checkReadPhoneStatePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_PHONE_STATE)) {
            new AlertDialog.Builder(this).setTitle("Permission Needed")
                    .setMessage("This  permission needed to run this app properly on your device")
                    .setPositiveButton("OK", (dialog, which) -> ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.
                            permission.READ_PHONE_STATE}, MY_REQ_CODE)).setCancelable(false)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.
                    permission.READ_PHONE_STATE}, MY_REQ_CODE);
        }

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
        tv_splasglogo = findViewById(R.id.tv_splasglogo);
        progressBar.setVisibility(View.VISIBLE);
        tv_splasglogo.setVisibility(View.VISIBLE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_REQ_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initviews();
                handler();
            } else {
                Toast.makeText(this, "You can't Run app without permission", Toast.LENGTH_LONG).show();
            }
        }

    }
}
