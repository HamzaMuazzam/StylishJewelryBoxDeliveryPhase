package com.example.stylishjewelryboxadmin.networkAPis.getordernumbers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOrderNumbersResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetOrderNumbers")
    @Expose
    private List<GetOrderNumber> getOrderNumbers = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetOrderNumbersResponse() {
    }

    /**
     *
     * @param getOrderNumbers
     * @param status
     */
    public GetOrderNumbersResponse(Boolean status, List<GetOrderNumber> getOrderNumbers) {
        super();
        this.status = status;
        this.getOrderNumbers = getOrderNumbers;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetOrderNumber> getGetOrderNumbers() {
        return getOrderNumbers;
    }

    public void setGetOrderNumbers(List<GetOrderNumber> getOrderNumbers) {
        this.getOrderNumbers = getOrderNumbers;
    }

}