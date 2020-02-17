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
import com.example.stylishjewelryboxadmin.networkAPis.getordernumbers.GetOrderNumber;
import com.example.stylishjewelryboxadmin.networkAPis.getordernumbers.GetOrderNumbersResponse;
import com.example.stylishjewelryboxadmin.networkAPis.getorders.GetOrderPending;
import com.example.stylishjewelryboxadmin.networkAPis.getorders.GetOrderPendingResponse;
import com.example.stylishjewelryboxadmin.recyclerviews.GetSetAdapterPendingOrders;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingFragment extends Fragment {
    private View view;
    public static RecyclerView recyclerView_pendingorder;
    private WebServices webServices;
    public static  List<GetOrderPending> finallist = new ArrayList<>();
    public static ProgressBar progressBar;
    public static GetSetAdapterPendingOrders getSetAdapterPendingOrders;

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
        getordersbyid();
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

    private void getordersbyid() {
        final String jcdid = AllOrdersActivity.jcdid;
        String phone = AllOrdersActivity.phone;
        final String area = AllOrdersActivity.area;
        webServices.getOrderNumbers(jcdid, "1", area, AllOrdersActivity.orderbydate).enqueue(new Callback<GetOrderNumbersResponse>() {
            @Override
            public void onResponse(Call<GetOrderNumbersResponse> call, Response<GetOrderNumbersResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        final List<GetOrderNumber> list = response.body().getGetOrderNumbers();
                        for (int x = 0; x < list.size(); x++) {
                            final String ordermianid = list.get(x).getOrdermianid();
                            webServices.getPendingOrder(jcdid, "1", ordermianid).enqueue(new Callback<GetOrderPendingResponse>() {
                                @Override
                                public void onResponse(Call<GetOrderPendingResponse> call, Response<GetOrderPendingResponse> response) {
                                    if (response.isSuccessful() && response.body() != null && response.body().getStatus()) {
                                        List<GetOrderPending> list1 = response.body().getGetOrderPending();
                                        for (int z = 0; z < list1.size(); z++) {

                                            finallist.addAll(list1);
                                            if (finallist.size() == list.size()) {
//                                                Toast.makeText(getContext(), "" + finallist.size(), Toast.LENGTH_SHORT).show();

                                                recyclerView_pendingorder = view.findViewById(R.id.pendingrecyclerview);
                                                getSetAdapterPendingOrders = new GetSetAdapterPendingOrders(getContext(), finallist);
                                                recyclerView_pendingorder.setAdapter(getSetAdapterPendingOrders);
                                                recyclerView_pendingorder.setLayoutManager(new LinearLayoutManager(getActivity()));
                                                getSetAdapterPendingOrders.notifyDataSetChanged();
                                                tvname.setText(AllOrdersActivity.name);
                                                tvphone.setText(AllOrdersActivity.phone);
                                                tvtotalorder.setText(AllOrdersActivity.totalorders);
                                                progressBar.setVisibility(View.GONE);

                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<GetOrderPendingResponse> call, Throwable t) {

                                }


                            });

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<GetOrderNumbersResponse> call, Throwable t) {

            }
        });

    }



 /*   private void getallorders() {
        String jcdid = AllOrdersActivity.jcdid;

        webServices.getAllOrders(jcdid, "1").enqueue(new Callback<GetOrderResponse>() {
            @Override
            public void onResponse(Call<GetOrderResponse> call, Response<GetOrderResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Boolean status = response.body().getStatus();
                    List<GetOrder> list = response.body().getGetOrders();
                    if (status) {
                        getallordersrecyclerview(list);
                    }

                }

            }

            @Override
            public void onFailure(Call<GetOrderResponse> call, Throwable t) {

            }
        });
    }

    private void getallordersrecyclerview(List list) {
        recyclerView_pendingorder = view.findViewById(R.id.pendingrecyclerview);
        getAllOrdersAdapter = new GetAllOrdersAdapter(getActivity(), list);
        recyclerView_pendingorder.setAdapter(getAllOrdersAdapter);
        recyclerView_pendingorder.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
*/

}
