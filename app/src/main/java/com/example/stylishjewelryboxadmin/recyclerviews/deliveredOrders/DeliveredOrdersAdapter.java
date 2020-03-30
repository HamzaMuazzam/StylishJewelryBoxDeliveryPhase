package com.example.stylishjewelryboxadmin.recyclerviews.deliveredOrders;

import android.app.AlertDialog;
import android.content.Context;
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
import com.example.stylishjewelryboxadmin.fragments.DeliveredFragment;
import com.example.stylishjewelryboxadmin.fragments.PendingFragment;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;
import com.example.stylishjewelryboxadmin.networkAPis.deliveredOrdersNumbers.GetAllDeliveredOrder;
import com.example.stylishjewelryboxadmin.networkAPis.getordernumbers.GetAllOrder;
import com.example.stylishjewelryboxadmin.networkAPis.getordernumbers.GetAllOrderResponse;
import com.example.stylishjewelryboxadmin.networkAPis.updateorderstatus.UpdateOrderStatus;
import com.example.stylishjewelryboxadmin.recyclerviews.PendingOrdersAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.stylishjewelryboxadmin.activities.AllOrdersActivity.area;
import static com.example.stylishjewelryboxadmin.activities.AllOrdersActivity.jcdid;
import static com.example.stylishjewelryboxadmin.activities.LoginActivityActivity.LOGIN_ID;

public class DeliveredOrdersAdapter extends RecyclerView.Adapter<DeliveredOrdersViewHolder> {
    private Context context;
    private List<GetAllDeliveredOrder> deliveredOrderAdapterlist;
    private WebServices webServices;

    public DeliveredOrdersAdapter(Context context, List<GetAllDeliveredOrder> deliveredOrderAdapterlist) {
        this.context = context;
        this.deliveredOrderAdapterlist = deliveredOrderAdapterlist;
    }

    @NonNull
    @Override
    public DeliveredOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.deliveredorder_itemlayout, parent, false);
        webServices = WebServices.RETROFIT.create(WebServices.class);
        return new DeliveredOrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveredOrdersViewHolder holder, int position) {
        GetAllDeliveredOrder model = deliveredOrderAdapterlist.get(position);
        holder.tv_d_orderdate.setText(model.getOdate());
        holder.tv_d_orderid.setText(model.getOrdermianid());
        holder.tv_d_orderby.setText(model.getDeliveredBy());
        holder.tv_d_orderdelivereddate.setText(model.getDeliveredDate());
        holder.tv_d_orderQuantity.setText(model.getTotalItems());
        holder.tv_d_orderprice.setText(model.getTotalPrice());
        holder.btn_undo.setOnClickListener(v -> {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Order No: " + model.getOrdermianid() + " Move to Pending?");
            builder.setMessage("Are you sure to want to move in Pending orders?");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", (dialog, which) -> {

                SharedPreferences sharedPreferences = context.getSharedPreferences("ForThisApp", MODE_PRIVATE);
                String login_id = sharedPreferences.getString(LOGIN_ID, "");

                DeliveredFragment.progressBar.setVisibility(View.VISIBLE);

                webServices.updateOrderStatusDelivered(model.getOrdermianid(), "0",
                        "0", "none", "none", login_id)
                        .enqueue(new Callback<UpdateOrderStatus>() {
                            @Override
                            public void onResponse(Call<UpdateOrderStatus> call, Response<UpdateOrderStatus> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    if (response.body().getStatus()) {
                                        Toast.makeText(context, "Moved", Toast.LENGTH_SHORT).show();

                                        getAllPendingOrdersToUpdate_PendingFragment(position, login_id);


                                    } else {
                                        Toast.makeText(context, "Not Updated" + response.body().getStatus(), Toast.LENGTH_LONG).show();
                                        DeliveredFragment.progressBar.setVisibility(View.GONE);

                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<UpdateOrderStatus> call, Throwable t) {
                                DeliveredFragment.progressBar.setVisibility(View.GONE);

                            }
                        });


            }).setNegativeButton("No", (dialog, which) -> {
                dialog.dismiss();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        });
    }

    private void getAllPendingOrdersToUpdate_PendingFragment(int position, String login_id) {
        webServices.getAllPendingOrders(jcdid, "0", area, AllOrdersActivity.orderbydate, login_id).enqueue(new Callback<GetAllOrderResponse>() {
            @Override
            public void onResponse(Call<GetAllOrderResponse> call, Response<GetAllOrderResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        List<GetAllOrder> allOrderList = response.body().getGetAllOrder();
                        PendingFragment.pendinglist.clear();
                        PendingOrdersAdapter pendingOrdersAdapter = new PendingOrdersAdapter(context, allOrderList);
                        PendingFragment.recyclerView_pendingorder.setAdapter(pendingOrdersAdapter);
                        PendingFragment.recyclerView_pendingorder.setLayoutManager(new LinearLayoutManager(context));
                        PendingFragment.progressBar.setVisibility(View.GONE);
                        DeliveredFragment.progressBar.setVisibility(View.GONE);
                        deliveredOrderAdapterlist.remove(position);
                        notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onFailure(Call<GetAllOrderResponse> call, Throwable t) {
                PendingFragment.progressBar.setVisibility(View.GONE);
                DeliveredFragment.progressBar.setVisibility(View.GONE);


            }
        });


    }

    @Override
    public int getItemCount() {
        return deliveredOrderAdapterlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
