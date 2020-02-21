package com.example.stylishjewelryboxadmin.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;
import com.example.stylishjewelryboxadmin.networkAPis.getlocationcity.GetLocationbyCity;
import com.example.stylishjewelryboxadmin.networkAPis.getlocationcity.GetLocationbyCityResponse;
import com.example.stylishjewelryboxadmin.utils.Utils;
import com.example.stylishjewelryboxadmin.utils.Veriables;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    ArrayAdapter<String> arrayAdapter;
    Button btn_getorders;
    WebServices webServices;
    List<GetLocationbyCity> list;
    String[] locationarray;
    String[] selectarea;
    ProgressBar progressBar;
    Toolbar toolbar;
    TextView tv_username_delivry;
    ImageView iv_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean mobileDataEnable = Utils.isMobileDataEnable(this);
        boolean wifiEnable = Utils.WifiEnable(this);
        if (mobileDataEnable || wifiEnable) {
            selectarea = new String[1];
            selectarea[0] = "Select Location";
            // arraystest();
            initviews();
            webservice();
            logout();
        } else {
            Utils.showCustomDialog(this, "No Internet Connection");
            btn_getorders.setOnClickListener(v -> Utils.showCustomDialog(MainActivity.this, "No Internet Connection"));
        }
    }

    private void logout() {

        iv_logout.setOnClickListener(v -> {
            Utils.savePreferences(LoginActivityActivity.NAME," ",this);
            Utils.savePreferences(LoginActivityActivity.PHONENUMBER," ",this);
            Utils.savePreferences(LoginActivityActivity.UUID," ",this);
            Intent intent = new Intent(MainActivity.this, LoginActivityActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

    }

//    private void arraystest() {
//        int[] array1 = {1, 2, 3};
//        int[] array2 = {4, 5, 6};
//        int aLen = array1.length;
//        int bLen = array2.length;
//        int[] result = new int[aLen + bLen];
//        System.arraycopy(array1, 0, result, 0, aLen);
//        System.arraycopy(array2, 0, result, aLen, bLen);
//        String s = Arrays.toString(result);
//        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
//    }

    private void webservice() {
        webServices.getlocationsbycity("Lahore").enqueue(new Callback<GetLocationbyCityResponse>() {
            @Override
            public void onResponse(Call<GetLocationbyCityResponse> call, Response<GetLocationbyCityResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    list = response.body().getGetLocationbyCity();
                    locationarray = new String[list.size()];
                    int length = locationarray.length;

                    if (list != null) {
                        for (int x = 0; x < list.size(); x++) {
                            locationarray[x] = list.get(x).getLocationname();

                        }
                        spinnerwork();


                    } else {
                        Toast.makeText(MainActivity.this, "list is empty", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetLocationbyCityResponse> call, Throwable t) {
                String message = t.getMessage();
                Toast.makeText(MainActivity.this, "OnFailure Exection: " + message, Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void initviews() {


        toolbar = findViewById(R.id.toolbar_name);
        iv_logout = findViewById(R.id.iv_logout);
        tv_username_delivry = findViewById(R.id.tv_username_delivry);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        String name = Utils.getPreferences(LoginActivityActivity.NAME, this);
        tv_username_delivry.setText("Logged in as " + name);

        btn_getorders = findViewById(R.id.btn_getorder);
        webServices = WebServices.RETROFIT.create(WebServices.class);
        spinner = findViewById(R.id.order_spiner);
        progressBar = findViewById(R.id.progress_circularBar);
        progressBar.setVisibility(View.VISIBLE);

    }

    private void spinnerwork() {
        //String colors[] = {"Select Location", "Blue", "White", "Yellow", "Black", "Green", "Purple", "Orange", "Grey"};


//        arrayAdapter = ArrayAdapter.createFromResource(
//                this, R.locationarray.numbers, android.R.layout.simple_spinner_item
//        );

        String[] locationarray1 = this.locationarray;
        String[] locationarray2 = selectarea;
        int length1 = locationarray1.length;
        int length2 = locationarray2.length;
        String[] new_array = new String[length1 + length2];
        System.arraycopy(locationarray2, 0, new_array, 0, length2);
        System.arraycopy(locationarray1, 0, new_array, length2, length1);
        int length = new_array.length;
        Toast.makeText(this, String.valueOf(length), Toast.LENGTH_SHORT).show();


        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new_array);

        arrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spinner.setAdapter(arrayAdapter);
        progressBar.setVisibility(View.GONE);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent != null) {
            final String location_name = parent.getItemAtPosition(position).toString();
            ((TextView) parent.getChildAt(0)).setText(location_name);
            ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#132656"));
            // Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
            btn_getorders.setOnClickListener(v -> {
                if (!location_name.equalsIgnoreCase("Select Location")) {
                    Intent intent = new Intent(MainActivity.this, GetAllCientByLocationActivity.class);
                    intent.putExtra(Veriables.AREA, location_name);
                    startActivity(intent);
                } else {
                    View contextView = findViewById(R.id.context_view);
                    Snackbar.make(contextView, "Please Select Location First ", Snackbar.LENGTH_SHORT).
                            setAction("ok", v1 -> {
                            }).setAction("OK", v12 -> {
                    }).show();
                }
            });


            //Intent intent;
            /*
            switch (position) {
                case 0:

                    break;
                case 1:
                    intent = new Intent(MainActivity.this, GetAllCientByLocationActivity.class);
                    startActivity(intent);
                break;
                case 2:
                    intent = new Intent(MainActivity.this, GetAllCientByLocationActivity.class);
                    startActivity(intent);
                    break;
                case 3:
                    Toast.makeText(this, "case 3", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(this, "case 4", Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    Toast.makeText(this, "case 5", Toast.LENGTH_SHORT).show();
                    break;
                case 6:
                    Toast.makeText(this, "case 6", Toast.LENGTH_SHORT).show();
                    break;
                case 7:
                    Toast.makeText(this, "case 7", Toast.LENGTH_SHORT).show();
                    break;
                case 8:
                    Toast.makeText(this, "case 8", Toast.LENGTH_SHORT).show();
                    break;
                case 9:
                    Toast.makeText(this, "case 9", Toast.LENGTH_SHORT).show();
                    break;
                case 10:
                    Toast.makeText(this, "case 10", Toast.LENGTH_SHORT).show();
                    break;
            }
*/

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
