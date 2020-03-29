package com.example.stylishjewelryboxadmin.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;
import com.example.stylishjewelryboxadmin.networkAPis.getclientbylocation.GetClientsByLocation;
import com.example.stylishjewelryboxadmin.networkAPis.getclientbylocation.GetClientsByLocationResponse;
import com.example.stylishjewelryboxadmin.recyclerviews.getClientbyLocation.GetClientByLocationAdapter;
import com.example.stylishjewelryboxadmin.utils.Utils;
import com.example.stylishjewelryboxadmin.utils.Veriables;
import com.google.android.material.snackbar.Snackbar;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.stylishjewelryboxadmin.activities.LoginActivityActivity.LOGIN_ID;

public class GetAllCientByLocationActivity extends AppCompatActivity {
    public static String areaname;
    WebServices webServices;
    RecyclerView recyclerView_getclietbylocation;
    GetClientByLocationAdapter getClientByLocationAdapter;
    ProgressBar progressBar;
    TextView tvDate;

    public static String strDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats);
        boolean mobileDataEnable = Utils.isMobileDataEnable(this);
        boolean wifiEnable = Utils.WifiEnable(this);
        if (mobileDataEnable || wifiEnable) {
            progressBar = findViewById(R.id.progress_circularBar);


//            progressBar.setVisibility(View.VISIBLE);
            initview();
            calander();


        } else {
            Utils.showCustomDialog(this, "No Internet Connection");
        }

    }

    private void calander() {
        Calendar calendar = Calendar.getInstance();
        int DAY = calendar.get(Calendar.DAY_OF_MONTH);
        int MONTH = calendar.get(Calendar.MONTH);
        int YEAR = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) -> {

            if (monthOfYear < 9) {
                strDate = year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth;
            } else {
                strDate = year + "-" + (+monthOfYear + 1) + "-" + dayOfMonth;
            }
            Toast.makeText(this, "date : " + strDate, Toast.LENGTH_SHORT).show();

            if (strDate != null) {
                tvDate.setText(strDate);
                getclientsbylocation(strDate);
            } else {
                tvDate.setText("Please Select Date First");
            }
        }, YEAR, MONTH, DAY);
        datePickerDialog.setOnCancelListener(dialog -> {
            View contextView = findViewById(R.id.context_view1);
            dialog.dismiss();
            Snackbar.make(contextView, "No Date Selected ", Snackbar.LENGTH_SHORT).setAction("Open Calender", v -> calander()).show();
        });
        datePickerDialog.show(getSupportFragmentManager(), "Date Picker");
    }

    private void initview() {
        tvDate = findViewById(R.id.tvDate);
        areaname = getIntent().getStringExtra(Veriables.AREA);
        webServices = WebServices.RETROFIT.create(WebServices.class);
        recyclerView_getclietbylocation = findViewById(R.id.recycerview_getclientsbylocaton);
        tvDate.setText("Please Select Date First");

    }


    private void getclientsbylocation(String strdate) {
//        Toast.makeText(getApplicationContext(),"str date :" +strdate,Toast.LENGTH_LONG).show();

        SharedPreferences sharedPreferences = this.getSharedPreferences("ForThisApp", MODE_PRIVATE);
        String login_id = sharedPreferences.getString(LOGIN_ID, "");


        if (areaname != null && !login_id.equals("")) {

            Toast.makeText(this, areaname + "   1  " + strdate + "  " + login_id, Toast.LENGTH_LONG).show();
            webServices.getclientbylocation(areaname, "0", strdate, login_id).enqueue(new Callback<GetClientsByLocationResponse>() {
                @Override
                public void onResponse(Call<GetClientsByLocationResponse> call, Response<GetClientsByLocationResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Boolean status = response.body().getStatus();

                        List<GetClientsByLocation> list = response.body().getGetClientsByLocation();

                        if (status && list != null) {
                            progressBar.setVisibility(View.GONE);
                            recyeclergetclient(list);
                        } else {
                            Toast.makeText(GetAllCientByLocationActivity.this, "No Record Found", Toast.LENGTH_SHORT).show();
                            View contextView = findViewById(R.id.context_view1);
                            Snackbar.make(contextView, "No Data Found Against " + strDate, Snackbar.LENGTH_SHORT).setAction("Open Calender", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    calander();
                                }
                            }).show();
                            calander();

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetClientsByLocationResponse> call, Throwable t) {

                }
            });
        }
    }

    private void recyeclergetclient(List list) {

        getClientByLocationAdapter = new GetClientByLocationAdapter(this, list);
        recyclerView_getclietbylocation.setAdapter(getClientByLocationAdapter);
        recyclerView_getclietbylocation.setLayoutManager(new LinearLayoutManager(this));

    }


    public void getclnderdate(View view) {
        calander();
    }
}
