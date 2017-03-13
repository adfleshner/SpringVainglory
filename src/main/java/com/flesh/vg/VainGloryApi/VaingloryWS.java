package com.flesh.vg.VainGloryApi;

import com.flesh.vg.Models.VG_Data;
import com.flesh.vg.Models.VG_Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

/**
 * Created by aaronfleshner on 3/13/17.
 */
public class VaingloryWS {

    private String apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmIzYTFkMC1lOWVlLTAxMzQtOWY2Ni0wMjQyYWMxMTAwMGIiLCJpc3MiOiJnYW1lbG9ja2VyIiwib3JnIjoiYWRmbGVzaG5lci1nbWFpbC1jb20iLCJhcHAiOiJhYmIxYzM0MC1lOWVlLTAxMzQtOWY2NS0wMjQyYWMxMTAwMGIiLCJwdWIiOiJzZW1jIiwidGl0bGUiOiJ2YWluZ2xvcnkiLCJzY29wZSI6ImNvbW11bml0eSIsImxpbWl0IjoxMH0.EHANNtOT0S5ZecqPxgrrT2fl6nA65KbrbIHpNrPm9AQ";
    private String baseUrl = "https://api.dc01.gamelockerapp.com/shards/";
    private Retrofit mVainglorRetrofit;


    public VaingloryWS() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        mVainglorRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).client(addVainGloryClientBuilder().build()).baseUrl(baseUrl).build();
    }

    private OkHttpClient.Builder addVainGloryClientBuilder(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            Request original = chain.request();
            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Authorization", apiKey)
                    .addHeader("Accept", "application/vnd.api+json")
                    .addHeader("X-TITLE-ID", "semc-vainglory");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
        return builder;
    }


    public void getVaingloryPlayers(Callback<VG_Data> callback){
        VaingloryApi api = mVainglorRetrofit.create(VaingloryApi.class);
        api.getPlayers().enqueue(callback);
    }
    public void getVaingloryPlayer(String playerName,Callback<VG_Data> callback){
        VaingloryApi api = mVainglorRetrofit.create(VaingloryApi.class);
        api.getPlayer(playerName).enqueue(callback);
    }



}