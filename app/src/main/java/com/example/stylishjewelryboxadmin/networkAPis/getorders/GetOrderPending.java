package com.example.stylishjewelryboxadmin.networkAPis.getorders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderPending {


    @SerializedName("total_items")
    @Expose
    private String totalItems;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("ordermianid")
    @Expose
    private String ordermianid;
    @SerializedName("odate")
    @Expose
    private String odate;
    @SerializedName("otime")
    @Expose
    private String otime;

    public GetOrderPending(String totalItems, String totalPrice, String ordermianid, String odate, String otime) {
        super();
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
        this.ordermianid = ordermianid;
        this.odate = odate;
        this.otime = otime;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrdermianid() {
        return ordermianid;
    }

    public void setOrdermianid(String ordermianid) {
        this.ordermianid = ordermianid;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

}