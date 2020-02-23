package com.example.stylishjewelryboxadmin.networkAPis.deliveredOrdersNumbers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllDeliveredOrderResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetAllDeliveredOrder")
    @Expose
    private List<GetAllDeliveredOrder> getAllDeliveredOrder = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllDeliveredOrderResponse() {
    }

    /**
     *
     * @param getAllDeliveredOrder
     * @param status
     */
    public GetAllDeliveredOrderResponse(Boolean status, List<GetAllDeliveredOrder> getAllDeliveredOrder) {
        super();
        this.status = status;
        this.getAllDeliveredOrder = getAllDeliveredOrder;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetAllDeliveredOrder> getGetAllDeliveredOrder() {
        return getAllDeliveredOrder;
    }

    public void setGetAllDeliveredOrder(List<GetAllDeliveredOrder> getAllDeliveredOrder) {
        this.getAllDeliveredOrder = getAllDeliveredOrder;
    }

}