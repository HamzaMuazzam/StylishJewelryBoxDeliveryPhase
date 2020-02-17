package com.example.stylishjewelryboxadmin.recyclerviews.singleitemsbyordernumber;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadmin.R;

public class SingleItemsViewHolder extends RecyclerView.ViewHolder {
    TextView tvitem_name,tvitem_totalprice,tv_item_materialtype,tv_item_size,tv_item_quantity,
            tv_singlepriceofitem;
    ImageView iv_getorderitems;
    public SingleItemsViewHolder(@NonNull View itemView) {
        super(itemView);
        tvitem_name=itemView.findViewById(R.id.tvitem_name);
        tvitem_totalprice=itemView.findViewById(R.id.tvitem_totalprice);
        tv_item_materialtype=itemView.findViewById(R.id.tv_item_materialtype);
        tv_item_size=itemView.findViewById(R.id.tv_item_size);
        tv_item_quantity=itemView.findViewById(R.id.tv_item_quantity);
        tv_singlepriceofitem=itemView.findViewById(R.id.tv_singlepriceofitem);
        iv_getorderitems=itemView.findViewById(R.id.iv_getorderitems);

    }
}
