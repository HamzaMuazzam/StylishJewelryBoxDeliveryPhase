package com.example.stylishjewelryboxadmin.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stylishjewelryboxadmin.R;

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

            initviews();
            handler();


    }

    private void initviews() {
        progressBar = findViewById(R.id.progress_splash);
        tv_splasglogo = findViewById(R.id.tv_splasglogo);
        progressBar.setVisibility(View.VISIBLE);
        tv_splasglogo.setVisibility(View.VISIBLE);

    }

/*
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
*/

    private void handler() {
        new Handler().postDelayed(this::checklogindetails,
                2000);
    }

    private void checklogindetails() {
//        String uuid = Utils.getPreferences(UUID, this);

        SharedPreferences sharedPref = this.getSharedPreferences("ForThisApp", Context.MODE_PRIVATE);
        String name = sharedPref.getString(LoginActivityActivity.NAME, "");
        String number = sharedPref.getString(LoginActivityActivity.PHONENUMBER, "");
//        String user_id = sharedPref.getString(LoginActivityActivity.LOGIN_ID, "");

        boolean status = sharedPref.getBoolean("STATUS", false);


        if (name != "" && number != "" && status) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivityActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }


}
