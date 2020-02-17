package com.example.stylishjewelryboxadmin.recyclerviews;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadmin.R;

public class GetSetViewHolder  extends RecyclerView.ViewHolder {
    TextView tvordernumbers,tvtotalitems,totalpriceofoneorder,tvordertime,tvorderdate;
    Button btnmovetodelivered;
    View view;
    public GetSetViewHolder(@NonNull View itemView) {
        super(itemView);

        tvordernumbers=itemView.findViewById(R.id.tvordernumbers);
        tvtotalitems=itemView.findViewById(R.id.tvtotalitems);
        totalpriceofoneorder=itemView.findViewById(R.id.totalpriceofoneorder);
        tvordertime=itemView.findViewById(R.id.tvordertime);
        btnmovetodelivered=itemView.findViewById(R.id.btnmovetodelivered);
        tvorderdate=itemView.findViewById(R.id.tvorderdate);
        view=itemView;
    }
}
