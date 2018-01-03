package com.example.huthut.loakonline.Penjual;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_TAMBAHTRANSAKSI;

/**
 * Created by Huthut on 12/15/2017.
 */

public class TambahTransaksiRequest extends StringRequest {
    private Map<String, String> params;

    public TambahTransaksiRequest(String id_produk, String uid, Response.Listener<String> listener){
        super(Request.Method.POST,URL_TAMBAHTRANSAKSI, listener, null);
        params = new HashMap<>();
        params.put("id_produk", id_produk);
        params.put("id_penjual", uid);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
