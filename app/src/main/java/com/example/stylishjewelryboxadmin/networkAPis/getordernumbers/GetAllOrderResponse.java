package com.example.stylishjewelryboxadmin.networkAPis.getordernumbers;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllOrderResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetAllOrder")
    @Expose
    private List<GetAllOrder> getAllOrder = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllOrderResponse() {
    }

    /**
     *
     * @param getAllOrder
     * @param status
     */
    public GetAllOrderResponse(Boolean status, List<GetAllOrder> getAllOrder) {
        super();
        this.status = status;
        this.getAllOrder = getAllOrder;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetAllOrder> getGetAllOrder() {
        return getAllOrder;
    }

    public void setGetAllOrder(List<GetAllOrder> getAllOrder) {
        this.getAllOrder = getAllOrder;
    }

}