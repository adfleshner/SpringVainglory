package com.flesh.vg;

import com.flesh.vg.Models.VG_Data;
import com.flesh.vg.Models.VG_Item;
import com.flesh.vg.VainGloryApi.VaingloryApi;
import com.flesh.vg.VainGloryApi.VaingloryWS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Where the Website starts
 * Created by aaronfleshner on 3/13/17.
 */
@RestController
public class StartController {

    private VaingloryWS ws = new VaingloryWS();

    @RequestMapping("/")
    public ResponseEntity<String> home(){
        ws.getVaingloryPlayer("fl3sh",new Callback<VG_Data>() {
            @Override
            public void onResponse(Call<VG_Data> call, Response<VG_Data> response) {
                System.out.println("Players name: "+response.body().data.get(0).attributes.name);
            }

            @Override
            public void onFailure(Call<VG_Data> call, Throwable t) {
                System.out.println("Get Player Error");
            }
        });
        return ResponseEntity.ok("Hello");
    }

}
