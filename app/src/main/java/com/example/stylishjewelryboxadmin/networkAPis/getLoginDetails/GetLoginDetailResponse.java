package com.example.stylishjewelryboxadmin.networkAPis.getLoginDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetLoginDetailResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetLoginDetail")
    @Expose
    private List<GetLoginDetail> getLoginDetail = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetLoginDetailResponse() {
    }

    /**
     *
     * @param getLoginDetail
     * @param status
     */
    public GetLoginDetailResponse(Boolean status, List<GetLoginDetail> getLoginDetail) {
        super();
        this.status = status;
        this.getLoginDetail = getLoginDetail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetLoginDetail> getGetLoginDetail() {
        return getLoginDetail;
    }

    public void setGetLoginDetail(List<GetLoginDetail> getLoginDetail) {
        this.getLoginDetail = getLoginDetail;
    }

}