package com.example.stylishjewelryboxadmin.networkAPis.deliveredOrdersNumbers;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderNumbersDelivered {

    @SerializedName("order_id")
    @Expose
    private String orderId;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetOrderNumbersDelivered() {
    }

    /**
     *
     * @param orderId
     */
    public GetOrderNumbersDelivered(String orderId) {
        super();
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}