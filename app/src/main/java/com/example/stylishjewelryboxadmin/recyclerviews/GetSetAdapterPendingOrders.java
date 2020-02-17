package com.example.stylishjewelryboxadmin.recyclerviews;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.activities.AllOrdersActivity;
import com.example.stylishjewelryboxadmin.activities.SingleOrderItemsActivity;
import com.example.stylishjewelryboxadmin.fragments.DeliveredFragment;
import com.example.stylishjewelryboxadmin.fragments.PendingFragment;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;
import com.example.stylishjewelryboxadmin.networkAPis.getorders.GetOrderDelivered;
import com.example.stylishjewelryboxadmin.networkAPis.getorders.GetOrderDeliveredResponse;
import com.example.stylishjewelryboxadmin.networkAPis.getorders.GetOrderPending;
import com.example.stylishjewelryboxadmin.networkAPis.updateorderstatus.UpdateOrderStatus;
import com.example.stylishjewelryboxadmin.recyclerviews.deliveredOrders.DeliveredOrdersAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetSetAdapterPendingOrders extends RecyclerView.Adapter<GetSetViewHolder> {
    private Context context;
    private List<GetOrderPending> list;
    public static String name, totalorders, phone;
    WebServices webServices;
    int parseInt;
    String formatedDate;


    public GetSetAdapterPendingOrders(Context context, List<GetOrderPending> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GetSetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemlayout_getorderspending, parent, false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        formatedDate = simpleDateFormat.format(new Date());
        String totalorders = AllOrdersActivity.totalorders;
        webServices = WebServices.RETROFIT.create(WebServices.class);
        parseInt = Integer.parseInt(totalorders);
        return new GetSetViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final GetSetViewHolder holder, final int position) {

        final GetOrderPending orders = list.get(position);

        holder.tvordernumbers.setText(orders.getOrdermianid());

        holder.tvorderdate.setText(orders.getOdate());

        holder.totalpriceofoneorder.setText(orders.getTotalPrice());

        holder.tvtotalitems.setText(orders.getTotalItems());

        holder.tvordertime.setText(orders.getOtime());

        holder.view.setOnClickListener(v -> {
            Intent intent = new Intent(context, SingleOrderItemsActivity.class);
            intent.putExtra("ordernumber", orders.getOrdermianid())
                    .putExtra("totalprice", orders.getTotalPrice())
                    .putExtra("totalitems", orders.getTotalItems())
                    .putExtra("date", orders.getOdate())
                    .putExtra("time", orders.getOtime());
            name = AllOrdersActivity.name;
            totalorders = AllOrdersActivity.totalorders;
            phone = AllOrdersActivity.phone;
            context.startActivity(intent);
        });

        holder.btnmovetodelivered.setOnClickListener(v -> {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Order No: " + orders.getOrdermianid() + " Move to Delivered?");
            builder.setMessage("Are you sure to want to move in delivered orders?");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", (dialog, which) -> {
                PendingFragment.progressBar.setVisibility(View.VISIBLE);

                SimpleDateFormat simpleDateFormatfotTime = new SimpleDateFormat("hh:mm:ss a");
                Date todaytime = Calendar.getInstance().getTime();
                String time = simpleDateFormatfotTime.format(todaytime);
                webServices.updateOrderStatus(orders.getOrdermianid(), "2", "5", formatedDate, time).enqueue(new Callback<UpdateOrderStatus>() {
                    @Override
                    public void onResponse(Call<UpdateOrderStatus> call, Response<UpdateOrderStatus> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getStatus()) {
                                list.remove(holder.getAdapterPosition());
                                parseInt = parseInt - 1;
                                PendingFragment.tvtotalorder.setText(String.valueOf(parseInt));
                                notifyDataSetChanged();

                                webServices.getDeliveredOrders(orders.getOrdermianid()).enqueue(new Callback<GetOrderDeliveredResponse>() {
                                    @Override
                                    public void onResponse(Call<GetOrderDeliveredResponse> call, Response<GetOrderDeliveredResponse> response) {
                                        if (response.isSuccessful() && response.body() != null) {
                                            if (response.body().getStatus()) {
//                                                if (!DeliveredFragment.checkedfalse) {
                                                    List<GetOrderDelivered> orderDeliveredList = response.body().getGetOrderDelivered();

                                                    DeliveredFragment.finaldeliveredList.addAll(orderDeliveredList);
                                                    DeliveredFragment.deliveredadapter = new DeliveredOrdersAdapter(context, DeliveredFragment.finaldeliveredList);
                                                    DeliveredFragment.recyclerView_deliveredordders.setAdapter(DeliveredFragment.deliveredadapter);
                                                    DeliveredFragment.recyclerView_deliveredordders.setLayoutManager(new LinearLayoutManager(context));
                                                    DeliveredFragment.deliveredadapter.notifyDataSetChanged();
                                                    PendingFragment.progressBar.setVisibility(View.GONE);

//
//                                                } else {
//                                                    List<GetOrderDelivered> orderDeliveredList = response.body().getGetOrderDelivered();
//                                                    DeliveredFragment.finaldeliveredList.addAll(orderDeliveredList);
//                                                    DeliveredFragment.deliveredadapter.notifyDataSetChanged();
//                                                    PendingFragment.progressBar.setVisibility(View.GONE);
//                                                }


                                            } else {
                                                Toast.makeText(context, "A " + response.body().getStatus(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<GetOrderDeliveredResponse> call, Throwable t) {

                                    }
                                });

                            } else {
                                Toast.makeText(context, "Order Not Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateOrderStatus> call, Throwable t) {

                        Toast.makeText(context, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }).setNegativeButton("No", (dialog, which) -> {
                dialog.dismiss();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        });


    }

    @Override

    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
