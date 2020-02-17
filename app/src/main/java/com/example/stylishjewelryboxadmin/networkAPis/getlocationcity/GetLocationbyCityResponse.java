package com.example.stylishjewelryboxadmin.networkAPis.getlocationcity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetLocationbyCityResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetLocationbyCity")
    @Expose
    private List<GetLocationbyCity> getLocationbyCity = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetLocationbyCityResponse() {
    }

    /**
     *
     * @param getLocationbyCity
     * @param status
     */
    public GetLocationbyCityResponse(Boolean status, List<GetLocationbyCity> getLocationbyCity) {
        super();
        this.status = status;
        this.getLocationbyCity = getLocationbyCity;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetLocationbyCity> getGetLocationbyCity() {
        return getLocationbyCity;
    }

    public void setGetLocationbyCity(List<GetLocationbyCity> getLocationbyCity) {
        this.getLocationbyCity = getLocationbyCity;
    }

}