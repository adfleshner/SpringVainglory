package com.flesh.vg.VainGloryApi;

import com.flesh.vg.Models.VG_Data;
import com.flesh.vg.Models.VG_Item;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

import static com.flesh.vg.VainGloryApi.VaingloryApi.CURRENT_REGION;

/**
 * Created by aaronfleshner on 3/13/17.
 */
public interface VaingloryApi {

    //the current region the server is hitting.
    public static final String CURRENT_REGION = ServerRegions.NA;

    @GET(CURRENT_REGION+"/players")
    Call<VG_Data> getPlayers();


    @GET(CURRENT_REGION+"/players?")
    Call<VG_Data> getPlayer(@Query("filter[playerNames]")String playerName);


}
