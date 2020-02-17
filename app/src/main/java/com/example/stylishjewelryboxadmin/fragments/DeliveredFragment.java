package com.example.stylishjewelryboxadmin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.activities.AllOrdersActivity;
import com.example.stylishjewelryboxadmin.activities.GetAllCientByLocationActivity;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;
import com.example.stylishjewelryboxadmin.networkAPis.deliveredOrdersNumbers.GetOrderNumbersDelivered;
import com.example.stylishjewelryboxadmin.networkAPis.deliveredOrdersNumbers.GetOrderNumbersDeliveredResponse;
import com.example.stylishjewelryboxadmin.networkAPis.getorders.GetOrderDelivered;
import com.example.stylishjewelryboxadmin.networkAPis.getorders.GetOrderDeliveredResponse;
import com.example.stylishjewelryboxadmin.recyclerviews.deliveredOrders.DeliveredOrdersAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveredFragment extends Fragment {
    private View view;
    private WebServices webServices;
    public  static RecyclerView recyclerView_deliveredordders;
    public static Boolean checkedfalse=false;
    public static ProgressBar progressBar;

    public static DeliveredOrdersAdapter deliveredadapter;

    public static List<GetOrderDelivered> finaldeliveredList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_delivered, container, false);
        initviews();
        getDeliveredOrdersByClient();
        return view;
    }

    private void initviews() {
        progressBar=view.findViewById(R.id.progress_circularBar00);
        progressBar.setVisibility(View.VISIBLE);
        webServices = WebServices.RETROFIT.create(WebServices.class);
        finaldeliveredList = new ArrayList<>();
        recyclerView_deliveredordders = view.findViewById(R.id.recycerview_deliveredorders);


    }

    private void getDeliveredOrdersByClient() {
        Toast.makeText(getContext(), "" + AllOrdersActivity.jcdid + "2" + AllOrdersActivity.area + AllOrdersActivity.orderbydate, Toast.LENGTH_LONG).show();

        webServices.getOrdernumberDelivred(AllOrdersActivity.jcdid, "2", AllOrdersActivity.area, GetAllCientByLocationActivity.strDate).enqueue(new Callback<GetOrderNumbersDeliveredResponse>() {
            @Override
            public void onResponse(Call<GetOrderNumbersDeliveredResponse> call, Response<GetOrderNumbersDeliveredResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        List<GetOrderNumbersDelivered> list = response.body().getGetOrderNumbersDelivered();
                        for (int x = 0; x < list.size(); x++) {
                            String orderId = list.get(x).getOrderId();


                            webServices.getDeliveredOrders(orderId).enqueue(new Callback<GetOrderDeliveredResponse>() {
                                @Override
                                public void onResponse(Call<GetOrderDeliveredResponse> call, Response<GetOrderDeliveredResponse> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        List<GetOrderDelivered> deliveredList = response.body().getGetOrderDelivered();
                                        finaldeliveredList.addAll(deliveredList);
                                        if (finaldeliveredList.size() == list.size()) {

                                            deliveredadapter = new DeliveredOrdersAdapter(getContext(), finaldeliveredList);
                                            recyclerView_deliveredordders.setAdapter(deliveredadapter);
                                            recyclerView_deliveredordders.setLayoutManager(new LinearLayoutManager(getContext()));
                                            deliveredadapter.notifyDataSetChanged();
                                            progressBar.setVisibility(View.GONE);


                                        }


                                    }

                                }

                                @Override
                                public void onFailure(Call<GetOrderDeliveredResponse> call, Throwable t) {
                                    Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    } else {
                        checkedfalse = false;
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(getContext(), "if response false : " + response.body().getStatus(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetOrderNumbersDeliveredResponse> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
