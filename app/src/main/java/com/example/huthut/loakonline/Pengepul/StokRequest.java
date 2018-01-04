package com.example.huthut.loakonline.Pengepul;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_STOK;

/**
 * Created by Huthut on 1/4/2018.
 */

public class StokRequest extends StringRequest {

    private Map<String, String> params;

    public StokRequest(String uid, Response.Listener<String> listener){
        super(Method.POST,URL_STOK, listener, null);
        params = new HashMap<>();
        params.put("id_pengepul", uid);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
