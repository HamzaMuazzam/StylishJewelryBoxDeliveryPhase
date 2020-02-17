package com.example.stylishjewelryboxadmin.recyclerviews.deliveredOrders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadmin.R;

public class DeliveredOrdersViewHolder extends RecyclerView.ViewHolder {
TextView tv_d_orderid,tv_d_orderdate,tv_d_orderdelivereddate,tv_d_orderby,tv_d_orderQuantity,tv_d_orderprice;

Button btn_undo;
    public DeliveredOrdersViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_d_orderid=itemView.findViewById(R.id.tv_d_orderid);
        tv_d_orderdate=itemView.findViewById(R.id.tv_d_orderdate);
        tv_d_orderdelivereddate=itemView.findViewById(R.id.tv_d_orderdelivereddate);
        tv_d_orderQuantity=itemView.findViewById(R.id.tv_d_orderQuantity);
        tv_d_orderprice=itemView.findViewById(R.id.tv_d_orderprice);
        tv_d_orderby=itemView.findViewById(R.id.tv_d_orderby);
        btn_undo=itemView.findViewById(R.id.btn_undo);
    }
}
