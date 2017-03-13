package com.flesh.vg.VainGloryApi;

import com.flesh.vg.Models.VG_Data;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



/**
 * Start of the API
 * Created by aaronfleshner on 3/13/17.
 */
public interface VaingloryApi {

    //the current region the server is hitting.
    String CURRENT_REGION = ServerRegions.NA;

    @GET(CURRENT_REGION+"/players")
    Call<VG_Data> getPlayers();


    @GET(CURRENT_REGION+"/players?")
    Call<VG_Data> getPlayer(@Query("filter[playerNames]")String playerName);


}
