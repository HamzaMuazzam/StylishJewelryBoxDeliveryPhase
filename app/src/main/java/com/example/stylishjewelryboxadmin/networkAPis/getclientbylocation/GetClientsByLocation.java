package com.example.stylishjewelryboxadmin.networkAPis.getclientbylocation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetClientsByLocation {

    @SerializedName("jcd_id")
    @Expose
    private String jcdId;
    @SerializedName("jcd_phone")
    @Expose
    private String jcdPhone;
    @SerializedName("jcd_name")
    @Expose
    private String jcdName;
    @SerializedName("total order")
    @Expose
    private String totalOrder;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetClientsByLocation() {
    }

    /**
     *
     * @param jcdPhone
     * @param jcdId
     * @param totalOrder
     * @param jcdName
     */
    public GetClientsByLocation(String jcdId, String jcdPhone, String jcdName, String totalOrder) {
        super();
        this.jcdId = jcdId;
        this.jcdPhone = jcdPhone;
        this.jcdName = jcdName;
        this.totalOrder = totalOrder;
    }

    public String getJcdId() {
        return jcdId;
    }

    public void setJcdId(String jcdId) {
        this.jcdId = jcdId;
    }

    public String getJcdPhone() {
        return jcdPhone;
    }

    public void setJcdPhone(String jcdPhone) {
        this.jcdPhone = jcdPhone;
    }

    public String getJcdName() {
        return jcdName;
    }

    public void setJcdName(String jcdName) {
        this.jcdName = jcdName;
    }

    public String getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(String totalOrder) {
        this.totalOrder = totalOrder;
    }

}