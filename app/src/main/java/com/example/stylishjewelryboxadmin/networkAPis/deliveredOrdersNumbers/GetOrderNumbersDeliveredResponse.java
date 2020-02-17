package com.example.stylishjewelryboxadmin.networkAPis.deliveredOrdersNumbers;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderNumbersDeliveredResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetOrderNumbersDelivered")
    @Expose
    private List<GetOrderNumbersDelivered> getOrderNumbersDelivered = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetOrderNumbersDeliveredResponse() {
    }

    /**
     *
     * @param status
     * @param getOrderNumbersDelivered
     */
    public GetOrderNumbersDeliveredResponse(Boolean status, List<GetOrderNumbersDelivered> getOrderNumbersDelivered) {
        super();
        this.status = status;
        this.getOrderNumbersDelivered = getOrderNumbersDelivered;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetOrderNumbersDelivered> getGetOrderNumbersDelivered() {
        return getOrderNumbersDelivered;
    }

    public void setGetOrderNumbersDelivered(List<GetOrderNumbersDelivered> getOrderNumbersDelivered) {
        this.getOrderNumbersDelivered = getOrderNumbersDelivered;
    }

}