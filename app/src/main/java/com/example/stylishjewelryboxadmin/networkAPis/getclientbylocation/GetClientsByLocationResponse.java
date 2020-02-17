package com.example.stylishjewelryboxadmin.networkAPis.getclientbylocation;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetClientsByLocationResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetClientsByLocation")
    @Expose
    private List<GetClientsByLocation> getClientsByLocation = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetClientsByLocationResponse() {
    }

    /**
     *
     * @param getClientsByLocation
     * @param status
     */
    public GetClientsByLocationResponse(Boolean status, List<GetClientsByLocation> getClientsByLocation) {
        super();
        this.status = status;
        this.getClientsByLocation = getClientsByLocation;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetClientsByLocation> getGetClientsByLocation() {
        return getClientsByLocation;
    }

    public void setGetClientsByLocation(List<GetClientsByLocation> getClientsByLocation) {
        this.getClientsByLocation = getClientsByLocation;
    }

}