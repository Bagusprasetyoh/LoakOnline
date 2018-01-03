package com.example.huthut.loakonline.Penjual;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_DAFTARBARANG;
import static com.example.huthut.loakonline.helper.AppConfig.URL_HAPUSBARANG;

/**
 * Created by Huthut on 12/20/2017.
 */

public class HapusBarangRequest extends StringRequest {
    private Map<String, String> params;

    public HapusBarangRequest(String uid, Response.Listener<String> listener){
        super(Request.Method.POST,URL_HAPUSBARANG, listener, null);
        params = new HashMap<>();
        params.put("id_detail", uid);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
