package com.example.stylishjewelryboxadmin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.activities.AllOrdersActivity;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;
import com.example.stylishjewelryboxadmin.networkAPis.deliveredOrdersNumbers.GetAllDeliveredOrder;
import com.example.stylishjewelryboxadmin.networkAPis.deliveredOrdersNumbers.GetAllDeliveredOrderResponse;
import com.example.stylishjewelryboxadmin.recyclerviews.deliveredOrders.DeliveredOrdersAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveredFragment extends Fragment {
    private View view;
    private WebServices webServices;
    public static List<GetAllDeliveredOrder> deliveredorder_list;
    public static RecyclerView recyclerView_deliveredordders;
    public static ProgressBar progressBar;
    public static DeliveredOrdersAdapter deliveredadapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_delivered, container, false);
        initviews();
        getDeliveredOrders();
        return view;
    }

    private void initviews() {
        progressBar = view.findViewById(R.id.progress_circularBar00);
        progressBar.setVisibility(View.VISIBLE);
        webServices = WebServices.RETROFIT.create(WebServices.class);

        recyclerView_deliveredordders = view.findViewById(R.id.recycerview_deliveredorders);


    }

    public void getDeliveredOrders() {

//        Toast.makeText(getContext(), "" + AllOrdersActivity.jcdid + "2" + AllOrdersActivity.area + AllOrdersActivity.orderbydate, Toast.LENGTH_LONG).show();

        webServices.getAllOrderDelivred(AllOrdersActivity.jcdid, "2", AllOrdersActivity.area).enqueue(new Callback<GetAllDeliveredOrderResponse>() {

            @Override
            public void onResponse(Call<GetAllDeliveredOrderResponse> call, Response<GetAllDeliveredOrderResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        deliveredorder_list = response.body().getGetAllDeliveredOrder();

                        deliveredadapter = new DeliveredOrdersAdapter(getContext(), deliveredorder_list);
                        recyclerView_deliveredordders.setAdapter(deliveredadapter);
                        recyclerView_deliveredordders.setLayoutManager(new LinearLayoutManager(getContext()));
                        deliveredadapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);

                    } else {

                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(getContext(), "if response false : " + response.body().getStatus(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllDeliveredOrderResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);


//                Toast.makeText(getContext(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
