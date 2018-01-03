package com.example.huthut.loakonline.Penjual;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_AMBILJADWAL;

/**
 * Created by Huthut on 12/31/2017.
 */

public class JadwalRequest extends StringRequest {
    private Map<String, String> params;

    public JadwalRequest(String hari, Response.Listener<String> listener){
        super(Request.Method.POST,URL_AMBILJADWAL, listener, null);
        params = new HashMap<>();
        params.put("hari", hari);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

