package com.example.huthut.loakonline.Pengepul;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_DAFTARALLTRANSAKSI;

/**
 * Created by Huthut on 1/3/2018.
 */

public class DaftarTransaksiRequest extends StringRequest {

    private Map<String, String> params;

    public DaftarTransaksiRequest(String uid, Response.Listener<String> listener){
        super(Request.Method.POST,URL_DAFTARALLTRANSAKSI, listener, null);
        params = new HashMap<>();
        params.put("id_pengepul", uid);

    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
