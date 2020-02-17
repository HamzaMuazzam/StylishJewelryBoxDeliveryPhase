package com.example.stylishjewelryboxadmin.networkAPis.getordernumbers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderNumber {

    @SerializedName("ordermianid")
    @Expose
    private String ordermianid;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetOrderNumber() {
    }

    /**
     *
     * @param ordermianid
     */
    public GetOrderNumber(String ordermianid) {
        super();
        this.ordermianid = ordermianid;
    }

    public String getOrdermianid() {
        return ordermianid;
    }

    public void setOrdermianid(String ordermianid) {
        this.ordermianid = ordermianid;
    }

}