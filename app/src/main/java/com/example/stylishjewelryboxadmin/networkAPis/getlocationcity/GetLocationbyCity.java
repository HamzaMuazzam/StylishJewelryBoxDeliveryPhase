package com.example.stylishjewelryboxadmin.networkAPis.getlocationcity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLocationbyCity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("locationname")
    @Expose
    private String locationname;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetLocationbyCity() {
    }

    /**
     *
     * @param locationname
     * @param id
     */
    public GetLocationbyCity(String id, String locationname) {
        super();
        this.id = id;
        this.locationname = locationname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationname() {
        return locationname;
    }

    public void setLocationname(String locationname) {
        this.locationname = locationname;
    }

}