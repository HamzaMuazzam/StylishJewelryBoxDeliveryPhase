package com.example.stylishjewelryboxadmin.networkAPis.getorders;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderDeliveredResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetOrderDelivered")
    @Expose
    private List<GetOrderDelivered> getOrderDelivered = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetOrderDeliveredResponse() {
    }

    /**
     *
     * @param getOrderDelivered
     * @param status
     */
    public GetOrderDeliveredResponse(Boolean status, List<GetOrderDelivered> getOrderDelivered) {
        super();
        this.status = status;
        this.getOrderDelivered = getOrderDelivered;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetOrderDelivered> getGetOrderDelivered() {
        return getOrderDelivered;
    }

    public void setGetOrderDelivered(List<GetOrderDelivered> getOrderDelivered) {
        this.getOrderDelivered = getOrderDelivered;
    }

}