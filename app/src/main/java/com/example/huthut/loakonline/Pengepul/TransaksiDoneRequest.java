package com.example.huthut.loakonline.Pengepul;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_TRANSAKSIDONE;

/**
 * Created by Huthut on 1/3/2018.
 */

public class TransaksiDoneRequest extends StringRequest {

    private Map<String, String> params;

    public TransaksiDoneRequest(String transaksi, Response.Listener<String> listener){
        super(Method.POST,URL_TRANSAKSIDONE, listener, null);
        params = new HashMap<>();
        params.put("transaksi", transaksi);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

