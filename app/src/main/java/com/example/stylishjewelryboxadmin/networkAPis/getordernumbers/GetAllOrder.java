package com.example.stylishjewelryboxadmin.networkAPis.getordernumbers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllOrder {

    @SerializedName("total_items")
    @Expose
    private String totalItems;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("odate")
    @Expose
    private String odate;
    @SerializedName("otime")
    @Expose
    private String otime;
    @SerializedName("ordermianid")
    @Expose
    private String ordermianid;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllOrder() {
    }

    /**
     *
     * @param totalItems
     * @param totalPrice
     * @param odate
     * @param ordermianid
     * @param otime
     */
    public GetAllOrder(String totalItems, String totalPrice, String odate, String otime, String ordermianid) {
        super();
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
        this.odate = odate;
        this.otime = otime;
        this.ordermianid = ordermianid;
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

    public String getOrdermianid() {
        return ordermianid;
    }

    public void setOrdermianid(String ordermianid) {
        this.ordermianid = ordermianid;
    }

}