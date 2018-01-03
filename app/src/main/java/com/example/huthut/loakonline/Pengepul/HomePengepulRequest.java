package com.example.huthut.loakonline.Pengepul;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_DETAILTRANSAKSI;

/**
 * Created by Huthut on 1/2/2018.
 */

public class HomePengepulRequest extends StringRequest {

    private Map<String, String> params;

    public HomePengepulRequest(String id_transaksi, Response.Listener<String> listener){
        super(Method.POST,URL_DETAILTRANSAKSI, listener, null);
        params = new HashMap<>();
        params.put("id_transaksi", id_transaksi);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

