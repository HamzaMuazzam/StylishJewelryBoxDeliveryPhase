package com.example.stylishjewelryboxadmin.recyclerviews.singleitemsbyordernumber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GetItemsAdapter extends RecyclerView.Adapter<SingleItemsViewHolder> {
    private Context context;
    private List<GetItemByOrderID> list;
    public static String location, jomdLongitude, jomdLatitude;


    public GetItemsAdapter(Context context, List<GetItemByOrderID> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SingleItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.getorderitems_itemlayout
                , parent, false);
        return new SingleItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleItemsViewHolder holder, int position) {
        GetItemByOrderID modelclass = list.get(position);
        holder.tv_item_materialtype.setText(modelclass.getPmeterial());
        holder.tv_item_quantity.setText(modelclass.getPquantity());
        holder.tv_item_size.setText(modelclass.getPsize());
        holder.tvitem_name.setText(modelclass.getPname());
        holder.tvitem_totalprice.setText(modelclass.getPprice());
        String pquantity = modelclass.getPquantity();

        String pprice = modelclass.getPprice();
        int parseprice = Integer.parseInt(pprice);
        int parsequantity= Integer.parseInt(pquantity);
        int singleprice = parseprice / parsequantity;
        holder.tv_singlepriceofitem.setText(String.valueOf(singleprice));

        Toast.makeText(context, "" + modelclass.getPimage(), Toast.LENGTH_LONG).show();
        Picasso.get().load(modelclass.getPimage()).into(holder.iv_getorderitems);
        location = modelclass.getJomdLocation();
        jomdLatitude = modelclass.getJomdLatitude();
        jomdLongitude = modelclass.getJomdLongitude();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
