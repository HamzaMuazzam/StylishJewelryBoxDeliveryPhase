package com.example.stylishjewelryboxadmin.recyclerviews.singleitemsbyordernumber;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetItemByOrderID {

    @SerializedName("jod_id")
    @Expose
    private String jodId;
    @SerializedName("pname")
    @Expose
    private String pname;
    @SerializedName("psize")
    @Expose
    private String psize;
    @SerializedName("pprice")
    @Expose
    private String pprice;
    @SerializedName("pimage")
    @Expose
    private String pimage;
    @SerializedName("pquantity")
    @Expose
    private String pquantity;
    @SerializedName("pmeterial")
    @Expose
    private String pmeterial;
    @SerializedName("otime")
    @Expose
    private String otime;
    @SerializedName("odate")
    @Expose
    private String odate;
    @SerializedName("jtt_id_fk")
    @Expose
    private String jttIdFk;
    @SerializedName("jdb_id")
    @Expose
    private String jdbId;
    @SerializedName("jcd_id_fk")
    @Expose
    private String jcdIdFk;
    @SerializedName("orderstatus")
    @Expose
    private String orderstatus;
    @SerializedName("ordermianid")
    @Expose
    private String ordermianid;
    @SerializedName("jomd_location")
    @Expose
    private String jomdLocation;
    @SerializedName("jomd_longitude")
    @Expose
    private String jomdLongitude;
    @SerializedName("jomd_latitude")
    @Expose
    private String jomdLatitude;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetItemByOrderID() {
    }

    /**
     *
     * @param pquantity
     * @param jcdIdFk
     * @param orderstatus
     * @param pname
     * @param jttIdFk
     * @param pprice
     * @param pmeterial
     * @param jdbId
     * @param jomdLongitude
     * @param otime
     * @param psize
     * @param pimage
     * @param jodId
     * @param jomdLocation
     * @param odate
     * @param jomdLatitude
     * @param ordermianid
     */
    public GetItemByOrderID(String jodId, String pname, String psize, String pprice, String pimage, String pquantity, String pmeterial, String otime, String odate, String jttIdFk, String jdbId, String jcdIdFk, String orderstatus, String ordermianid, String jomdLocation, String jomdLongitude, String jomdLatitude) {
        super();
        this.jodId = jodId;
        this.pname = pname;
        this.psize = psize;
        this.pprice = pprice;
        this.pimage = pimage;
        this.pquantity = pquantity;
        this.pmeterial = pmeterial;
        this.otime = otime;
        this.odate = odate;
        this.jttIdFk = jttIdFk;
        this.jdbId = jdbId;
        this.jcdIdFk = jcdIdFk;
        this.orderstatus = orderstatus;
        this.ordermianid = ordermianid;
        this.jomdLocation = jomdLocation;
        this.jomdLongitude = jomdLongitude;
        this.jomdLatitude = jomdLatitude;
    }

    public String getJodId() {
        return jodId;
    }

    public void setJodId(String jodId) {
        this.jodId = jodId;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPsize() {
        return psize;
    }

    public void setPsize(String psize) {
        this.psize = psize;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getPquantity() {
        return pquantity;
    }

    public void setPquantity(String pquantity) {
        this.pquantity = pquantity;
    }

    public String getPmeterial() {
        return pmeterial;
    }

    public void setPmeterial(String pmeterial) {
        this.pmeterial = pmeterial;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public String getJttIdFk() {
        return jttIdFk;
    }

    public void setJttIdFk(String jttIdFk) {
        this.jttIdFk = jttIdFk;
    }

    public String getJdbId() {
        return jdbId;
    }

    public void setJdbId(String jdbId) {
        this.jdbId = jdbId;
    }

    public String getJcdIdFk() {
        return jcdIdFk;
    }

    public void setJcdIdFk(String jcdIdFk) {
        this.jcdIdFk = jcdIdFk;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrdermianid() {
        return ordermianid;
    }

    public void setOrdermianid(String ordermianid) {
        this.ordermianid = ordermianid;
    }

    public String getJomdLocation() {
        return jomdLocation;
    }

    public void setJomdLocation(String jomdLocation) {
        this.jomdLocation = jomdLocation;
    }

    public String getJomdLongitude() {
        return jomdLongitude;
    }

    public void setJomdLongitude(String jomdLongitude) {
        this.jomdLongitude = jomdLongitude;
    }

    public String getJomdLatitude() {
        return jomdLatitude;
    }

    public void setJomdLatitude(String jomdLatitude) {
        this.jomdLatitude = jomdLatitude;
    }

}