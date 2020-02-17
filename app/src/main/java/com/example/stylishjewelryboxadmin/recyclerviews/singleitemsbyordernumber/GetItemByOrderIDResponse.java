package com.example.stylishjewelryboxadmin.recyclerviews.singleitemsbyordernumber;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetItemByOrderIDResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetItemByOrderID")
    @Expose
    private List<GetItemByOrderID> getItemByOrderID = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetItemByOrderIDResponse() {
    }

    /**
     *
     * @param getItemByOrderID
     * @param status
     */
    public GetItemByOrderIDResponse(Boolean status, List<GetItemByOrderID> getItemByOrderID) {
        super();
        this.status = status;
        this.getItemByOrderID = getItemByOrderID;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetItemByOrderID> getGetItemByOrderID() {
        return getItemByOrderID;
    }

    public void setGetItemByOrderID(List<GetItemByOrderID> getItemByOrderID) {
        this.getItemByOrderID = getItemByOrderID;
    }

}
