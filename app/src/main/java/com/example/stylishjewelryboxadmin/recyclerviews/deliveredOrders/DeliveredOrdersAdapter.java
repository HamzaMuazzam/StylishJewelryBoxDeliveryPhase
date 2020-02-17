package com.example.stylishjewelryboxadmin.recyclerviews.deliveredOrders;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.activities.AllOrdersActivity;
import com.example.stylishjewelryboxadmin.fragments.DeliveredFragment;
import com.example.stylishjewelryboxadmin.fragments.PendingFragment;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;
import com.example.stylishjewelryboxadmin.networkAPis.getorders.GetOrderDelivered;
import com.example.stylishjewelryboxadmin.networkAPis.getorders.GetOrderPending;
import com.example.stylishjewelryboxadmin.networkAPis.getorders.GetOrderPendingResponse;
import com.example.stylishjewelryboxadmin.networkAPis.updateorderstatus.UpdateOrderStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveredOrdersAdapter extends RecyclerView.Adapter<DeliveredOrdersViewHolder> {
    private Context context;
    private List<GetOrderDelivered> list;
private WebServices webServices;
    public DeliveredOrdersAdapter(Context context, List<GetOrderDelivered> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DeliveredOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.deliveredorder_itemlayout, parent, false);
        webServices=WebServices.RETROFIT.create(WebServices.class);
        return new DeliveredOrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveredOrdersViewHolder holder, int position) {
        GetOrderDelivered model = list.get(position);
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



            String ordermianid = model.getOrdermianid();
            Toast.makeText(context,""+ordermianid,Toast.LENGTH_LONG).show();
            DeliveredFragment.progressBar.setVisibility(View.VISIBLE);
            webServices.updateOrderStatusDelivered(model.getOrdermianid(),"1","0","none","none").enqueue(new Callback<UpdateOrderStatus>() {
        @Override
        public void onResponse(Call<UpdateOrderStatus> call, Response<UpdateOrderStatus> response) {
            if (response.isSuccessful() && response.body()!=null){
                if (response.body().getStatus()){

                    webServices.getPendingOrder(AllOrdersActivity.jcdid,"1",model.getOrdermianid()).enqueue(new Callback<GetOrderPendingResponse>() {
                        @Override
                        public void onResponse(Call<GetOrderPendingResponse> call, Response<GetOrderPendingResponse> response) {
                            if (response.isSuccessful() && response.body()!=null){
                                if (response.body().getStatus()){
                                    List<GetOrderPending> getOrderPendinglist = response.body().getGetOrderPending();
                                    PendingFragment.finallist.addAll(getOrderPendinglist);
                                    PendingFragment.getSetAdapterPendingOrders.notifyDataSetChanged();
                                    DeliveredFragment.progressBar.setVisibility(View.GONE);
                                    list.remove(position);
                                    notifyDataSetChanged();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<GetOrderPendingResponse> call, Throwable t) {

                        }
                    });


                }
                else {
                    Toast.makeText(context,"Not Updated"+response.body().getStatus(),Toast.LENGTH_LONG).show();
                }

            }
        }

        @Override
        public void onFailure(Call<UpdateOrderStatus> call, Throwable t) {

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
