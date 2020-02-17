package com.example.stylishjewelryboxadmin.networkAPis.getLoginDetails;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLoginDetail {

    @SerializedName("jdb_id")
    @Expose
    private String jdbId;
    @SerializedName("jdb_uid")
    @Expose
    private String jdbUid;
    @SerializedName("jdb_name")
    @Expose
    private String jdbName;
    @SerializedName("jdb_email")
    @Expose
    private String jdbEmail;
    @SerializedName("jdb_password")
    @Expose
    private String jdbPassword;
    @SerializedName("jdb_img")
    @Expose
    private String jdbImg;
    @SerializedName("jdb_phone")
    @Expose
    private String jdbPhone;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetLoginDetail() {
    }

    /**
     *
     * @param jdbName
     * @param jdbEmail
     * @param jdbId
     * @param jdbUid
     * @param jdbPhone
     * @param jdbPassword
     * @param jdbImg
     */
    public GetLoginDetail(String jdbId, String jdbUid, String jdbName, String jdbEmail, String jdbPassword, String jdbImg, String jdbPhone) {
        super();
        this.jdbId = jdbId;
        this.jdbUid = jdbUid;
        this.jdbName = jdbName;
        this.jdbEmail = jdbEmail;
        this.jdbPassword = jdbPassword;
        this.jdbImg = jdbImg;
        this.jdbPhone = jdbPhone;
    }

    public String getJdbId() {
        return jdbId;
    }

    public void setJdbId(String jdbId) {
        this.jdbId = jdbId;
    }

    public String getJdbUid() {
        return jdbUid;
    }

    public void setJdbUid(String jdbUid) {
        this.jdbUid = jdbUid;
    }

    public String getJdbName() {
        return jdbName;
    }

    public void setJdbName(String jdbName) {
        this.jdbName = jdbName;
    }

    public String getJdbEmail() {
        return jdbEmail;
    }

    public void setJdbEmail(String jdbEmail) {
        this.jdbEmail = jdbEmail;
    }

    public String getJdbPassword() {
        return jdbPassword;
    }

    public void setJdbPassword(String jdbPassword) {
        this.jdbPassword = jdbPassword;
    }

    public String getJdbImg() {
        return jdbImg;
    }

    public void setJdbImg(String jdbImg) {
        this.jdbImg = jdbImg;
    }

    public String getJdbPhone() {
        return jdbPhone;
    }

    public void setJdbPhone(String jdbPhone) {
        this.jdbPhone = jdbPhone;
    }

}