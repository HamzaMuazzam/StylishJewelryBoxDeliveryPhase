package com.example.stylishjewelryboxadmin.networkAPis;

import com.example.stylishjewelryboxadmin.networkAPis.deliveredOrdersNumbers.GetOrderNumbersDeliveredResponse;
import com.example.stylishjewelryboxadmin.networkAPis.getLoginDetails.GetLoginDetailResponse;
import com.example.stylishjewelryboxadmin.networkAPis.getclientbylocation.GetClientsByLocationResponse;
import com.example.stylishjewelryboxadmin.networkAPis.getclientinfo.GetClientInfoResponse;
import com.example.stylishjewelryboxadmin.networkAPis.getlocationcity.GetLocationbyCityResponse;
import com.example.stylishjewelryboxadmin.networkAPis.getordernumbers.GetOrderNumbersResponse;
import com.example.stylishjewelryboxadmin.networkAPis.getorders.GetOrderDeliveredResponse;
import com.example.stylishjewelryboxadmin.networkAPis.getorders.GetOrderPendingResponse;
import com.example.stylishjewelryboxadmin.networkAPis.updateorderstatus.UpdateOrderStatus;
import com.example.stylishjewelryboxadmin.recyclerviews.singleitemsbyordernumber.GetItemByOrderIDResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WebServices {
    String BASEURL = "http://sourceinflow.com/jewelry/apis/";
    String GETLOCATIONBYCITY = "get_locations_bycity.php";
    String GETCLIENTBYLOCATION = "getClientBylocation.php";

    String GETCLIENT = "getClientdetails.php";

    String UPDATEORDERSTATUS = "updateorderstatus.php";
    String UPDATEORDERSTATUSDELIVERED = "movetopending.php";

    String GETORDERNuMERS = "getordersnumberaslist.php";
    String GETPENDINGORDERS = "getOrderPending.php";
    String GETITEMBYORDERID = "getsingleitemsperorder.php";
    String GETORDERNUMBERDELIVRED = "getordernumberdelivered.php";
    String GETDELIVERDORDERS = "getdeliveredorders.php";
    String DeliveryBoySignUp = "deliveryboy_singup.php";
    String GETLOGINDETAILS = "getLogin.php";



    Retrofit RETROFIT = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();





    @FormUrlEncoded
    @POST(GETLOCATIONBYCITY)
    Call<GetLocationbyCityResponse> getlocationsbycity(@Field("cityname") String cityname);
//    @GET(GETMETERIAL)
//    Call<GetAllMeterialCatResponse> getmterialname();
    @FormUrlEncoded
    @POST(GETCLIENT)
    Call<GetClientInfoResponse> getclientinfo(@Field("clientid") String clientid);


    @FormUrlEncoded
    @POST(GETCLIENTBYLOCATION)
    Call<GetClientsByLocationResponse> getclientbylocation(@Field("location") String location, @Field("statusid") String statusid
    ,@Field("orderbydate") String  orderbydate);


       @FormUrlEncoded
    @POST(GETORDERNuMERS)
    Call<GetOrderNumbersResponse> getOrderNumbers(@Field("clientid") String clientid,
                                                  @Field("orderstatus") String orderstatusid,
                                                  @Field("area") String area,@Field("orderbydate") String orderbydate);
    @FormUrlEncoded
    @POST(GETPENDINGORDERS)
    Call<GetOrderPendingResponse> getPendingOrder(@Field("clientid") String clientid,
                                                  @Field("orderstatus") String orderstatusid,
                                                  @Field("orderid") String orderid );
    @FormUrlEncoded
    @POST(GETITEMBYORDERID)
    Call<GetItemByOrderIDResponse> getItemsByOrderID(@Field("orderid") String orderid );
    @FormUrlEncoded
    @POST(UPDATEORDERSTATUS)
    Call<UpdateOrderStatus> updateOrderStatus(@Field("orderid") String orderid,
                                              @Field("updateorderstatus") String updateorderstatus,
                                              @Field("delivered_by") String delivered_by,
                                              @Field("delivered_date") String delivered_date,
                                              @Field("delivered_time") String delivered_time);


    @FormUrlEncoded
    @POST(UPDATEORDERSTATUSDELIVERED)
    Call<UpdateOrderStatus> updateOrderStatusDelivered(@Field("orderid") String orderid,
                                              @Field("updateorderstatus") String updateorderstatus,
                                              @Field("delivered_by") String delivered_by,
                                              @Field("delivered_date") String delivered_date,
                                              @Field("delivered_time") String delivered_time);


    @FormUrlEncoded
    @POST(GETORDERNUMBERDELIVRED)
    Call<GetOrderNumbersDeliveredResponse> getOrdernumberDelivred(@Field("clientid")String clientid,
                                                                  @Field("orderstatus")String orderstatus,
                                                                  @Field("area")String area,
                                                                  @Field("date")String date);


    @FormUrlEncoded
    @POST(GETDELIVERDORDERS)
    Call<GetOrderDeliveredResponse> getDeliveredOrders(@Field("orderid") String orderid);


    @FormUrlEncoded
    @POST(DeliveryBoySignUp)
    Call<DeliveryBoysignup> deliveryBoySignUp(@Field("uid") String uid,
                                              @Field("name") String name,
                                              @Field("email") String email,
                                              @Field("password") String password,
                                              @Field("img") String img,
                                              @Field("location") String location,
                                              @Field("singup_date") String singup_date,
                                              @Field("phone") String phone,
                                              @Field("status") String status
    );

    @FormUrlEncoded
    @POST(GETLOGINDETAILS)
    Call<GetLoginDetailResponse> getLogin(@Field("phone") String phone,
                                          @Field("name") String username);


}
