package com.example.stylishjewelryboxadmin.networkAPis.getorders;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderDelivered {

    @SerializedName("total_items")
    @Expose
    private String totalItems;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("ordermianid")
    @Expose
    private String ordermianid;
    @SerializedName("odate")
    @Expose
    private String odate;
    @SerializedName("otime")
    @Expose
    private String otime;
    @SerializedName("delivered_date")
    @Expose
    private String deliveredDate;
    @SerializedName("delivered_time")
    @Expose
    private String deliveredTime;
    @SerializedName("delivered_by")
    @Expose
    private String deliveredBy;
    @SerializedName("jomd_datetime")
    @Expose
    private String jomdDatetime;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetOrderDelivered() {
    }

    /**
     *
     * @param deliveredDate
     * @param totalItems
     * @param deliveredBy
     * @param totalPrice
     * @param odate
     * @param ordermianid
     * @param deliveredTime
     * @param jomdDatetime
     * @param otime
     */
    public GetOrderDelivered(String totalItems, String totalPrice, String ordermianid, String odate, String otime, String deliveredDate, String deliveredTime, String deliveredBy, String jomdDatetime) {
        super();
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
        this.ordermianid = ordermianid;
        this.odate = odate;
        this.otime = otime;
        this.deliveredDate = deliveredDate;
        this.deliveredTime = deliveredTime;
        this.deliveredBy = deliveredBy;
        this.jomdDatetime = jomdDatetime;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrdermianid() {
        return ordermianid;
    }

    public void setOrdermianid(String ordermianid) {
        this.ordermianid = ordermianid;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(String deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public String getDeliveredBy() {
        return deliveredBy;
    }

    public void setDeliveredBy(String deliveredBy) {
        this.deliveredBy = deliveredBy;
    }

    public String getJomdDatetime() {
        return jomdDatetime;
    }

    public void setJomdDatetime(String jomdDatetime) {
        this.jomdDatetime = jomdDatetime;
    }

}