package com.example.stylishjewelryboxadmin.networkAPis.getclientinfo;



  import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetClientInfoResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetClientInfo")
    @Expose
    private List<GetClientInfo> getClientInfo = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetClientInfoResponse() {
    }

    /**
     *
     * @param getClientInfo
     * @param status
     */
    public GetClientInfoResponse(Boolean status, List<GetClientInfo> getClientInfo) {
        super();
        this.status = status;
        this.getClientInfo = getClientInfo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetClientInfo> getGetClientInfo() {
        return getClientInfo;
    }

    public void setGetClientInfo(List<GetClientInfo> getClientInfo) {
        this.getClientInfo = getClientInfo;
    }

}