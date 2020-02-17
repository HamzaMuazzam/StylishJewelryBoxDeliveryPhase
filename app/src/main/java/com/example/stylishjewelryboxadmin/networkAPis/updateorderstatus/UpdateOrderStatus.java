package com.example.stylishjewelryboxadmin.networkAPis.updateorderstatus;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateOrderStatus {

    @SerializedName("status")
    @Expose
    private Boolean status;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateOrderStatus() {
    }

    /**
     *
     * @param status
     */
    public UpdateOrderStatus(Boolean status) {
        super();
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}