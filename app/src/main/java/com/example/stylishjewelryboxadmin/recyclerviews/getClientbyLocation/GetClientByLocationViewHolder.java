package com.example.stylishjewelryboxadmin.recyclerviews.getClientbyLocation;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadmin.R;

public class GetClientByLocationViewHolder  extends RecyclerView.ViewHolder {
    TextView clientname, clientId, tvTotalOrders,phone;
    View holderview;
    public GetClientByLocationViewHolder(@NonNull View itemView) {
        super(itemView);
        clientname =itemView.findViewById(R.id.tvclientname);
        clientId =itemView.findViewById(R.id.tvclientId);
        tvTotalOrders =itemView.findViewById(R.id.tvTotalOrders);
        phone =itemView.findViewById(R.id.tvphone);
           holderview=itemView;
    }
}
