package com.example.stylishjewelryboxadmin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.activities.AllOrdersActivity;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;
import com.example.stylishjewelryboxadmin.networkAPis.getordernumbers.GetAllOrder;
import com.example.stylishjewelryboxadmin.networkAPis.getordernumbers.GetAllOrderResponse;
import com.example.stylishjewelryboxadmin.recyclerviews.PendingOrdersAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingFragment extends Fragment {
    private View view;
    public static List<GetAllOrder> pendinglist = new ArrayList<>();
    public static PendingOrdersAdapter pendingOrdersAdapter;
    public static RecyclerView recyclerView_pendingorder;
    public static ProgressBar progressBar;
    private WebServices webServices;
    public static TextView tvname, tvphone, tvtotalorder;


    public PendingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pending, container, false);


        initviews();
        getAllOrders();
        return view;
    }

    private void initviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);
        progressBar = view.findViewById(R.id.progress_circularBar);
        progressBar.setVisibility(View.VISIBLE);
        tvname = view.findViewById(R.id.clientname);
        tvphone = view.findViewById(R.id.tvphoenofclient);
        tvtotalorder = view.findViewById(R.id.ttvtotalorders);
    }

    private void getAllOrders() {
        final String jcdid = AllOrdersActivity.jcdid;
        final String area = AllOrdersActivity.area;

        webServices.getAllPendingOrders(jcdid, "1", area, AllOrdersActivity.orderbydate).enqueue(new Callback<GetAllOrderResponse>() {
            @Override
            public void onResponse(Call<GetAllOrderResponse> call, Response<GetAllOrderResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        pendinglist = response.body().getGetAllOrder();
                        recyclerView_pendingorder = view.findViewById(R.id.pendingrecyclerview);
                        pendingOrdersAdapter = new PendingOrdersAdapter(getContext(), pendinglist);
                        recyclerView_pendingorder.setAdapter(pendingOrdersAdapter);
                        recyclerView_pendingorder.setLayoutManager(new LinearLayoutManager(getActivity()));
                        pendingOrdersAdapter.notifyDataSetChanged();
                        tvname.setText(AllOrdersActivity.name);
                        tvphone.setText(AllOrdersActivity.phone);
                        tvtotalorder.setText(AllOrdersActivity.totalorders);
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllOrderResponse> call, Throwable t) {
//                Toast.makeText(getContext(), "OnFailure: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }


}
