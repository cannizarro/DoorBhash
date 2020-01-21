package com.cannizarro.doorbhash;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.PUT;

/**
 * Webrtc_Step3
 * Created by vivek-3102 on 11/03/17.
 */


public interface TurnServer {
    @PUT("/_turn/MyFirstApp")
    Call<TurnServerPojo> getIceCandidates(@Header("Authorization") String authkey);
}
