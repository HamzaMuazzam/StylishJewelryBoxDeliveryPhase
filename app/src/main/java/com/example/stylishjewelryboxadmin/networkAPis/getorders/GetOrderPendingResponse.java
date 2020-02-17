package com.example.stylishjewelryboxadmin.networkAPis.getorders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOrderPendingResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetOrderPending")
    @Expose
    private List<GetOrderPending> getOrderPending = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetOrderPendingResponse() {
    }

    /**
     *
     * @param getOrderPending
     * @param status
     */
    public GetOrderPendingResponse(Boolean status, List<GetOrderPending> getOrderPending) {
        super();
        this.status = status;
        this.getOrderPending = getOrderPending;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetOrderPending> getGetOrderPending() {
        return getOrderPending;
    }

    public void setGetOrderPending(List<GetOrderPending> getOrderPending) {
        this.getOrderPending = getOrderPending;
    }

}