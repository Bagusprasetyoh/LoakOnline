package com.example.huthut.loakonline.Pengepul;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_TAMBAHJADWAL;

/**
 * Created by Huthut on 1/4/2018.
 */

public class TambahJadwalRequest extends StringRequest {

    private Map<String, String> params;

    public TambahJadwalRequest(String id_pengepul, String id_jadwal, Response.Listener<String> listener){
        super(Method.POST,URL_TAMBAHJADWAL, listener, null);
        params = new HashMap<>();
        params.put("id_pengepul", id_pengepul);
        params.put("id_jadwal", id_jadwal);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

