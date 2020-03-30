package com.example.stylishjewelryboxadmin.recyclerviews;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.stylishjewelryboxadmin.networkAPis.deliveredOrdersNumbers.GetAllDeliveredOrder;
import com.example.stylishjewelryboxadmin.networkAPis.deliveredOrdersNumbers.GetAllDeliveredOrderResponse;
import com.example.stylishjewelryboxadmin.networkAPis.getordernumbers.GetAllOrder;
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

import static android.content.Context.MODE_PRIVATE;
import static com.example.stylishjewelryboxadmin.activities.LoginActivityActivity.LOGIN_ID;

public class PendingOrdersAdapter extends RecyclerView.Adapter<GetSetViewHolder> {
    private Context context;
    private List<GetAllOrder> list;
    public static String name, totalorders, phone;
    WebServices webServices;
    int parseInt;
    String formatedDate;


    public PendingOrdersAdapter(Context context, List<GetAllOrder> list) {
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

        final GetAllOrder orders = list.get(position);

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
            builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            builder.setPositiveButton("Yes", (dialog, which) -> {
                PendingFragment.progressBar.setVisibility(View.VISIBLE);
                SimpleDateFormat simpleDateFormatfotTime = new SimpleDateFormat("hh:mm:ss a");
                Date todaytime = Calendar.getInstance().getTime();
                String time = simpleDateFormatfotTime.format(todaytime);

                SharedPreferences sharedPreferences = context.getSharedPreferences("ForThisApp", MODE_PRIVATE);
                String login_id = sharedPreferences.getString(LOGIN_ID, "");

                webServices.updateOrderStatus(orders.getOrdermianid(), "1", login_id, formatedDate, time).enqueue(new Callback<UpdateOrderStatus>() {
                    @Override
                    public void onResponse(Call<UpdateOrderStatus> call, Response<UpdateOrderStatus> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getStatus()) {
                                Toast.makeText(context, "Just Updated ", Toast.LENGTH_SHORT).show();
                                updateDeliveredOrderFragmment(position);


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateOrderStatus> call, Throwable t) {
                        Toast.makeText(context, "Not Updated", Toast.LENGTH_LONG).show();
                        PendingFragment.progressBar.setVisibility(View.GONE);

                    }
                });

            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

    }

    private void updateDeliveredOrderFragmment(int pos) {

        webServices.getAllOrderDelivred(AllOrdersActivity.jcdid, "1", AllOrdersActivity.area).enqueue(new Callback<GetAllDeliveredOrderResponse>() {

            @Override
            public void onResponse(Call<GetAllDeliveredOrderResponse> call, Response<GetAllDeliveredOrderResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        List<GetAllDeliveredOrder> deliveredOrderList = response.body().getGetAllDeliveredOrder();

                        DeliveredOrdersAdapter deliveredadapter = new DeliveredOrdersAdapter(context, deliveredOrderList);
                        DeliveredFragment.recyclerView_deliveredordders.setAdapter(deliveredadapter);
                        DeliveredFragment.recyclerView_deliveredordders.setLayoutManager(new LinearLayoutManager(context));

                        deliveredadapter.notifyDataSetChanged();

                        DeliveredFragment.progressBar.setVisibility(View.GONE);

                        list.remove(pos);

                        notifyDataSetChanged();

                        PendingFragment.progressBar.setVisibility(View.GONE);

                        Toast.makeText(context, "Updated", Toast.LENGTH_LONG).show();


                    } else {
                        DeliveredFragment.progressBar.setVisibility(View.GONE);
//                        Toast.makeText(getContext(), "if response false : " + response.body().getStatus(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllDeliveredOrderResponse> call, Throwable t) {
                DeliveredFragment.progressBar.setVisibility(View.GONE);

//                Toast.makeText(getContext(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
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