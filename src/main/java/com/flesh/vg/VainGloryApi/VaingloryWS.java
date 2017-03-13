package com.flesh.vg.VainGloryApi;

import com.flesh.vg.Models.VG_Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Starting the Web service
 * Created by aaronfleshner on 3/13/17.
 */
public class VaingloryWS {

    private String apiKey = "";
    private String baseUrl = "https://api.dc01.gamelockerapp.com/shards/";
    private Retrofit mVainglorRetrofit;


    public VaingloryWS() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        mVainglorRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).client(addVainGloryClientBuilder().build()).baseUrl(baseUrl).build();
    }


    private void getApiKey(){
        Properties properties = new Properties();
        InputStream inputStream =
                this.getClass().getClassLoader().getResourceAsStream("local.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        apiKey=properties.getProperty("apiKey");
    }

    private OkHttpClient.Builder addVainGloryClientBuilder(){
        //get api key from local properties
        getApiKey();
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
