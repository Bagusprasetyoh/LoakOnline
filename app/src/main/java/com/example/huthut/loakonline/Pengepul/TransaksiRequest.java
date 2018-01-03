package com.example.huthut.loakonline.Pengepul;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_TRANSAKSI;

/**
 * Created by Huthut on 1/2/2018.
 */

public class TransaksiRequest extends StringRequest {

    private Map<String, String> params;

    public TransaksiRequest(String id_transaksi, Response.Listener<String> listener){
        super(Request.Method.POST,URL_TRANSAKSI, listener, null);
        params = new HashMap<>();
        params.put("id_transaksi", id_transaksi);
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
