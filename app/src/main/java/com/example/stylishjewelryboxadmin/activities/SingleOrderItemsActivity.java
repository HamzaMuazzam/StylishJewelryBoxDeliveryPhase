package com.example.stylishjewelryboxadmin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;
import com.example.stylishjewelryboxadmin.recyclerviews.singleitemsbyordernumber.GetItemByOrderID;
import com.example.stylishjewelryboxadmin.recyclerviews.singleitemsbyordernumber.GetItemByOrderIDResponse;
import com.example.stylishjewelryboxadmin.recyclerviews.singleitemsbyordernumber.GetItemsAdapter;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleOrderItemsActivity extends AppCompatActivity {
    WebServices webServices;
    private RecyclerView recyclerView;
    private GetItemsAdapter getItemsAdapter;
    String ordernumber, totalprice, totalitems, date, time;
    Button btn_gotomap;
    ProgressBar progressBar;
    TextView tv_onumber, tv_o_total_items, tv_o_date, tv_o_totalprice, tv_o_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_order_items);
        initviews();
        getvalues();
        getitems();
    }

    private void initviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);
        recyclerView = findViewById(R.id.recycer_getsingleitems);
        tv_onumber = findViewById(R.id.tv_onumber);
        tv_o_total_items = findViewById(R.id.tv_o_total_items);
        tv_o_date = findViewById(R.id.tv_o_date);
        tv_o_totalprice = findViewById(R.id.tv_o_totalprice);
        tv_o_time = findViewById(R.id.tv_o_time);
        btn_gotomap = findViewById(R.id.btn_gotomap);
        progressBar = findViewById(R.id.progress_circularBar);
        progressBar.setVisibility(View.VISIBLE);


    }

    private void getvalues() {

        ordernumber = getIntent().getStringExtra("ordernumber");
        totalprice = getIntent().getStringExtra("totalprice");
        totalitems = getIntent().getStringExtra("totalitems");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");

    }

    private void getitems() {
        webServices.getItemsByOrderID(ordernumber).enqueue(new Callback<GetItemByOrderIDResponse>() {
            @Override
            public void onResponse(Call<GetItemByOrderIDResponse> call, Response<GetItemByOrderIDResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getStatus()) {
                    List<GetItemByOrderID> getItemByOrderIDlist = response.body().getGetItemByOrderID();

                    getItemsAdapter = new GetItemsAdapter(SingleOrderItemsActivity.this, getItemByOrderIDlist);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SingleOrderItemsActivity.this));
                    recyclerView.setAdapter(getItemsAdapter);

                    getItemsAdapter.notifyDataSetChanged();
                    tv_onumber.setText(ordernumber);
                    tv_o_totalprice.setText(totalprice);
                    tv_o_total_items.setText(totalitems);
                    tv_o_date.setText(date);
                    tv_o_time.setText(time);
                    progressBar.setVisibility(View.GONE);

                    btn_gotomap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String location = GetItemsAdapter.location;
                            String jomdLatitude = GetItemsAdapter.jomdLatitude;
                            String jomdLongitude = GetItemsAdapter.jomdLongitude;
                            if (!jomdLatitude.equalsIgnoreCase("0.0") || jomdLongitude.equalsIgnoreCase("0.0")) {
                                Intent intent = new Intent(SingleOrderItemsActivity.this, MapsActivity.class);
                                intent.putExtra("lat", jomdLatitude).putExtra("lng", jomdLongitude).putExtra("location", location);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(SingleOrderItemsActivity.this, jomdLatitude+"\n"+jomdLongitude, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<GetItemByOrderIDResponse> call, Throwable t) {

            }
        });
    }


}
