package com.example.stylishjewelryboxadmin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;
import com.example.stylishjewelryboxadmin.networkAPis.getclientinfo.GetClientInfo;
import com.example.stylishjewelryboxadmin.networkAPis.getclientinfo.GetClientInfoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_detailsActivity extends AppCompatActivity {

    TextView tvordernumber, tv_totalprice,
            tv_customername, tvdeliveryaddress, tvdatandtime, tvproductname,
            tvorderstatus, tvmeterialtype, tvpaymenttype, tvquantity, tvsize, tvinstuctions;

    String orderstutus, paymenttype, deliveryboyid,
            date, time, meterrial, pname,
            pprice, orderid, psize, pquantity;
    WebServices webServices;

    ImageView imageView;
    String image;
    String jcdId;
    String jcdName;
    String jcdPhone;
    String jcdInstructions;
    String jcd_location;
    String jcd_latitude="0";
    String jcd_longitude="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        initviews();
        getintentValues();
        getclient();
        setvalues();

    }

    private void initviews()
    {
        webServices = WebServices.RETROFIT.create(WebServices.class);
        tvordernumber = findViewById(R.id.tvordernumber);
        tv_totalprice = findViewById(R.id.tv_totalprice);
        tv_customername = findViewById(R.id.tv_customername);
        tvdeliveryaddress = findViewById(R.id.tvdeliveryaddress);
        tvdatandtime = findViewById(R.id.tvdatandtime);
        tvorderstatus = findViewById(R.id.tvorderstatus);
        tvmeterialtype = findViewById(R.id.tvmeterialtype);
        tvproductname = findViewById(R.id.tvproductname);
        tvquantity = findViewById(R.id.tvquantity);
        tvsize = findViewById(R.id.tvsize);
        tvinstuctions = findViewById(R.id.tvinstuctions);
        tvpaymenttype = findViewById(R.id.tvpaymenttype);
        imageView = findViewById(R.id.iv_productimage);

    }

    private void getintentValues() {

        orderstutus = getIntent().getStringExtra("orderstutus");

        paymenttype = getIntent().getStringExtra("paymenttype");
        deliveryboyid = getIntent().getStringExtra("deliveryboyid");


        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        meterrial = getIntent().getStringExtra("meterrial");
        pname = getIntent().getStringExtra("pname");
        pprice = getIntent().getStringExtra("pprice");
        pquantity = getIntent().getStringExtra("pquantity");
        orderid = getIntent().getStringExtra("orderid");
        psize = getIntent().getStringExtra("psize");
        image = getIntent().getStringExtra("image");
        jcdId = getIntent().getStringExtra("clientid");


        Glide.with(this).load(image).into(imageView);

    }

    private void getclient() {
        Toast.makeText(this, jcdId, Toast.LENGTH_SHORT).show();
        webServices.getclientinfo(jcdId).enqueue(new Callback<GetClientInfoResponse>() {
            @Override
            public void onResponse(Call<GetClientInfoResponse> call, Response<GetClientInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<GetClientInfo> list = response.body().getGetClientInfo();
                    for (int x = 0; x < list.size(); x++) {
                        jcdId = list.get(x).getJcdId();
                        jcdName = list.get(x).getJcdName();
                        jcdPhone = list.get(x).getJcdPhone();
                        jcdInstructions = list.get(x).getJcdInstructions();
                        jcd_location = list.get(x).getJcd_location();

                        jcd_latitude = list.get(x).getJcd_latitude();
                        jcd_longitude = list.get(x).getJcd_longitude();


                        tv_customername.setText(jcdName);
                        tvinstuctions.setText(jcdInstructions);
                        tvdeliveryaddress.setText(jcd_location);

                        Toast.makeText(Order_detailsActivity.this, jcd_latitude+"\n"+
                                jcd_longitude, Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<GetClientInfoResponse> call, Throwable t) {

            }
        });
    }

    private void setvalues() {
        tvordernumber.setText(orderid);
        tv_totalprice.setText(pprice);
        tvmeterialtype.setText(meterrial);
        tvdatandtime.setText(date + " " + time);
        tvproductname.setText(pname);

        if (orderstutus.equalsIgnoreCase("1")) tvorderstatus.setText(R.string.pending);
        if (paymenttype.equalsIgnoreCase("1")) tvpaymenttype.setText(R.string.cod);

        tvquantity.setText(pquantity);
        tvsize.setText(psize);


    }

    public void gotolocation(View view) {

        if (jcd_latitude.equalsIgnoreCase("0") && jcd_longitude.equalsIgnoreCase("0")) {
            Toast.makeText(this, "Latitude and longitude not entered by user", Toast.LENGTH_SHORT).show();
        } else {

            Intent intent = new Intent(Order_detailsActivity.this,MapsActivity.class);

            intent.putExtra("latitude", jcd_latitude).putExtra("longitude", jcd_longitude);

            startActivity(intent);

        }


    }
}
