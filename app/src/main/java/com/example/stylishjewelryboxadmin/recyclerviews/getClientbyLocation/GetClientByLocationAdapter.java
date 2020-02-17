package com.example.stylishjewelryboxadmin.recyclerviews.getClientbyLocation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.activities.AllOrdersActivity;
import com.example.stylishjewelryboxadmin.activities.GetAllCientByLocationActivity;
import com.example.stylishjewelryboxadmin.networkAPis.getclientbylocation.GetClientsByLocation;

import java.util.List;

public class GetClientByLocationAdapter extends RecyclerView.Adapter<GetClientByLocationViewHolder> {
    private Context context;
    private List<GetClientsByLocation> list;

    public GetClientByLocationAdapter(Context context, List<GetClientsByLocation> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GetClientByLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.getclientbylocation_item_layout, parent, false);
        return new GetClientByLocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetClientByLocationViewHolder holder, final int position) {
        final GetClientsByLocation modelclass = list.get(position);
        holder.clientname.setText(modelclass.getJcdName());

        holder.clientId.setText(modelclass.getJcdId());
        holder.clientname.setText(modelclass.getJcdName());
        holder.phone.setText(modelclass.getJcdPhone());
        holder.tvTotalOrders.setText(modelclass.getTotalOrder());

        holder.holderview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AllOrdersActivity.class);
                String jcdPhone = modelclass.getJcdPhone();
                String jcdId = modelclass.getJcdId();
                String jcdName = modelclass.getJcdName();
                String totalOrder = modelclass.getTotalOrder();
                intent.putExtra("phone", jcdPhone)
                        .putExtra("jcdid", jcdId)
                        .putExtra("name", jcdName)
                        .putExtra("totalorders", totalOrder)
                        .putExtra("orderbydate",GetAllCientByLocationActivity.strDate)
                        .putExtra("area", GetAllCientByLocationActivity.areaname);


                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
