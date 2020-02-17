package com.example.stylishjewelryboxadmin.networkAPis.getclientinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetClientInfo {

    @SerializedName("jcd_id")
    @Expose
    private String jcdId;
    @SerializedName("jcd_name")
    @Expose
    private String jcdName;
    @SerializedName("jcd_phone")
    @Expose
    private String jcdPhone;
    @SerializedName("jcd_instructions")
    @Expose
    private String jcdInstructions;



    @SerializedName("jcd_location")
    @Expose
    private String jcd_location;



    @SerializedName("jcd_latitude")
    @Expose
    private String jcd_latitude;



    @SerializedName("jcd_longitude")
    @Expose
    private String jcd_longitude;


    public String getJcd_latitude() {
        return jcd_latitude;
    }

    public void setJcd_latitude(String jcd_latitude) {
        this.jcd_latitude = jcd_latitude;
    }

    public String getJcd_longitude() {
        return jcd_longitude;
    }

    public void setJcd_longitude(String jcd_longitude) {
        this.jcd_longitude = jcd_longitude;
    }

    public String getJcd_location() {
        return jcd_location;
    }

    public void setJcd_location(String jcd_location) {
        this.jcd_location = jcd_location;
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public GetClientInfo() {
    }

    /**
     *
     * @param jcdPhone
     * @param jcdId
     * @param jcdName
     * @param jcdInstructions
     */
    public GetClientInfo(String jcdId, String jcdName, String jcdPhone, String jcdInstructions) {
        super();
        this.jcdId = jcdId;
        this.jcdName = jcdName;
        this.jcdPhone = jcdPhone;
        this.jcdInstructions = jcdInstructions;
    }

    public String getJcdId() {
        return jcdId;
    }

    public void setJcdId(String jcdId) {
        this.jcdId = jcdId;
    }

    public String getJcdName() {
        return jcdName;
    }

    public void setJcdName(String jcdName) {
        this.jcdName = jcdName;
    }

    public String getJcdPhone() {
        return jcdPhone;
    }

    public void setJcdPhone(String jcdPhone) {
        this.jcdPhone = jcdPhone;
    }

    public String getJcdInstructions() {
        return jcdInstructions;
    }

    public void setJcdInstructions(String jcdInstructions) {
        this.jcdInstructions = jcdInstructions;
    }

}