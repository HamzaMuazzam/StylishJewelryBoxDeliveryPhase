package com.example.stylishjewelryboxadmin.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.utils.Utils;

public class OrderByNotificationActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;


    CardView card_clicktoMainScreen;
    TextView tv_NameForNotiScren, tv_IDForNotiScren, tv_TotalForNotiScren, tv_LocationForNotiScren, tv_DateForNotiScren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_by_notification);
        initviews();

        if (Utils.isMobileDataEnable(this) || Utils.WifiEnable(this)) {
            String date = getIntent().getStringExtra("key");
            String location = getIntent().getStringExtra("key1");
            String totalorders = getIntent().getStringExtra("key2");
            Toast.makeText(this, "" + totalorders + location + date, Toast.LENGTH_LONG).show();
            boolean status = sharedPreferences.getBoolean("STATUS", false);
            if (status) {
                card_clicktoMainScreen.setVisibility(View.VISIBLE);
                tv_NameForNotiScren.setText(sharedPreferences.getString(LoginActivityActivity.NAME, ""));
                tv_IDForNotiScren.setText(sharedPreferences.getString(LoginActivityActivity.LOGIN_ID, ""));
                tv_TotalForNotiScren.setText(totalorders);
                tv_LocationForNotiScren.setText(location);
                tv_DateForNotiScren.setText(date);
            }
            {
                Toast.makeText(this, "Please Login to see Details", Toast.LENGTH_SHORT).show();
            }


        }
    }

    private void initviews() {
        sharedPreferences = this.getSharedPreferences("ForThisApp", MODE_PRIVATE);

        card_clicktoMainScreen = findViewById(R.id.card_clicktoMainScreen);

        tv_DateForNotiScren = findViewById(R.id.tv_DateForNotiScren);
        tv_TotalForNotiScren = findViewById(R.id.tv_TotalForNotiScren);

        tv_LocationForNotiScren = findViewById(R.id.tv_LocationForNotiScren);
        tv_IDForNotiScren = findViewById(R.id.tv_IDForNotiScren);
        tv_NameForNotiScren = findViewById(R.id.tv_NameForNotiScren);
    }
}
