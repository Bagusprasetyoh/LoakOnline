package com.example.huthut.loakonline.Pengepul;

import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_UPDATE_TRANSAKSI;

/**
 * Created by Huthut on 1/2/2018.
 */

public class PenjualRequest extends StringRequest {

    private Map<String, String> params;

    public PenjualRequest(String uid, String transaksi, Response.Listener<String> listener){
        super(Method.POST,URL_UPDATE_TRANSAKSI, listener, null);
        params = new HashMap<>();
        params.put("id_pengepul", uid);
        params.put("transaksi", transaksi);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
