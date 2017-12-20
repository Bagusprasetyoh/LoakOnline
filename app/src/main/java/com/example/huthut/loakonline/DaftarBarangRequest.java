package com.example.huthut.loakonline;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_DAFTARBARANG;

/**
 * Created by Huthut on 12/17/2017.
 */

public class DaftarBarangRequest extends StringRequest {
    private Map<String, String> params;

    public DaftarBarangRequest(String uid, Response.Listener<String> listener){
        super(Request.Method.POST,URL_DAFTARBARANG, listener, null);
        params = new HashMap<>();
        params.put("id_penjual", uid);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
