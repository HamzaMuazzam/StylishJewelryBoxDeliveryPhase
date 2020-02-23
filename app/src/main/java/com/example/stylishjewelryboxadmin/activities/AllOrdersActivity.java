package com.example.stylishjewelryboxadmin.activities;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.fragments.DeliveredFragment;
import com.example.stylishjewelryboxadmin.fragments.PendingFragment;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;
import com.example.stylishjewelryboxadmin.recyclerviews.viewpager.ViewPagerAdapter;
import com.example.stylishjewelryboxadmin.utils.Utils;
import com.google.android.material.tabs.TabLayout;

public class AllOrdersActivity extends AppCompatActivity {
    Toolbar toolbar;
    WebServices webServices;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String orderbydate;


    public static String area, jcdid, location, phone, totalorders, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_cats);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        boolean mobileDataEnable = Utils.isMobileDataEnable(this);
        boolean wifiEnable = Utils.WifiEnable(this);
        if (mobileDataEnable || wifiEnable) {
            getvaluesfromintent();
            initviews();
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);


        } else {
            Utils.showCustomDialog(this, "Oho No Internet Connection");
        }


    }

    private void initviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.myViewPager);
        setSupportActionBar(toolbar);
    }

    private void getvaluesfromintent() {

        jcdid = getIntent().getStringExtra("jcdid");
        phone = getIntent().getStringExtra("phone");
        totalorders = getIntent().getStringExtra("totalorders");
        name = getIntent().getStringExtra("name");
        area = getIntent().getStringExtra("area");
        orderbydate = getIntent().getStringExtra("orderbydate");
//        Toast.makeText(this, jcdid + phone + name + totalorders+"\n"+area, Toast.LENGTH_SHORT).show();


    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new PendingFragment(), "Pending Orders", jcdid);
        viewPagerAdapter.addFragment(new DeliveredFragment(), "Delivered Orders", jcdid);
        viewPager.setAdapter(viewPagerAdapter);

    }
}
