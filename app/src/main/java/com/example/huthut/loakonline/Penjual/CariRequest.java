package com.example.huthut.loakonline.Penjual;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_AMBILJADWAL;
import static com.example.huthut.loakonline.helper.AppConfig.URL_CARIPENGEPUL;

/**
 * Created by Huthut on 1/2/2018.
 */

public class CariRequest extends StringRequest {
    private Map<String, String> params;

    public CariRequest(String uid, String id_jadwal, String latitude, String longitude, String alamat, Response.Listener<String> listener){
        super(Request.Method.POST,URL_CARIPENGEPUL, listener, null);
        params = new HashMap<>();
        params.put("user", uid);
        params.put("id_jadwal", id_jadwal);
        params.put("latitude", latitude);
        params.put("longitude", longitude);
        params.put("alamat", alamat);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
