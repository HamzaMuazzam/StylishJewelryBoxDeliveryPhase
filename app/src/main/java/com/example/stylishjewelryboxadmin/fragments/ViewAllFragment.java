package com.example.stylishjewelryboxadmin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;

public class ViewAllFragment extends Fragment {
    private View view;
    private WebServices webServices;
    private RecyclerView recyclerView_meterial;
    private TextView tv_totalorderagaintslocation,tv_locationname;
//    meterial_adapter catsAdapter;
    public static String[] stringarray;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_view_all, container, false);
        initview();
        return view;
    }

/*
    private void getOrderbyLocationName() {
        webServices.getallordersbylocation(GetAllCientByLocationActivity.areaname, "1").enqueue(new Callback<GetOrderscoutbylocationResponse>() {
            @Override
            public void onResponse(Call<GetOrderscoutbylocationResponse> call, Response<GetOrderscoutbylocationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Integer count = response.body().getCount();
                    tv_totalorderagaintslocation.setText(String.valueOf(count));
                    tv_locationname.setText(GetAllCientByLocationActivity.areaname);
                }
            }

            @Override
            public void onFailure(Call<GetOrderscoutbylocationResponse> call, Throwable t) {

            }
        });
    }
*/

    private void initview() {
        webServices = WebServices.RETROFIT.create(WebServices.class);
        recyclerView_meterial = view.findViewById(R.id.recyclerview_all);
        tv_totalorderagaintslocation = view.findViewById(R.id.tv_totalorderagaintslocation);
        tv_locationname = view.findViewById(R.id.tv_locationname);
    }

//    private void getmeterials() {
//        webServices.getmterialname().enqueue(new Callback<GetAllMeterialCatResponse>() {
//            @Override
//            public void onResponse(Call<GetAllMeterialCatResponse> call, Response<GetAllMeterialCatResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    list = response.body().getGetAllMeterialCats();
//                    //                    for (int x = 0; x < list.size(); x++) {
////
////
/////*
////                        webServices.getAllOrders(GetAllCientByLocationActivity.areaname, "1", mcName).enqueue(new Callback<GetOrderResponse>() {
////                            @Override
////                            public void onResponse(Call<GetOrderResponse> call, Response<GetOrderResponse> response) {
////                                if (response.body() != null && response.isSuccessful()) {
////                                    Integer count = response.body().getCount();
//////                                    Toast.makeText(getContext(),"count against "+mcName +"\n"+
//////                                            String.valueOf(count), Toast.LENGTH_SHORT).show();
////                                    orderarray = new int[list.size()];
////                                    orderarray[finalX] = count;
////
////                                }
////
////                            }
////
////                            @Override
////                            public void onFailure(Call<GetOrderResponse> call, Throwable t) {
////
////                            }
////                        });
////*/
////
////                    }
//
//                    for (int x = 0; x < list.size(); x++) {
//                        String mcName = list.get(x).getMcName();
//                        stringarray = new String[list.size()];
//                        stringarray[x] = mcName;
//                    }
//                    meterial_recyclerview(list);
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<GetAllMeterialCatResponse> call, Throwable t) {
//
//            }
//        });
//
//    }

//    private void meterial_recyclerview(List list) {
//        catsAdapter = new meterial_adapter(getContext(), list);
//        recyclerView_meterial.setAdapter(catsAdapter);
//        recyclerView_meterial.setLayoutManager(new GridLayoutManager(getContext(),
//                2,
//                GridLayoutManager.VERTICAL,
//                false));
//    }

}
