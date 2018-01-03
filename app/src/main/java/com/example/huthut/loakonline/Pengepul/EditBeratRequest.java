package com.example.huthut.loakonline.Pengepul;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_EDITDETAILBARANG;


/**
 * Created by Huthut on 1/3/2018.
 */

public class EditBeratRequest extends StringRequest {
    private Map<String, String> params;

    public EditBeratRequest(String id_detail, String berat, Response.Listener<String> listener){
        super(Request.Method.POST,URL_EDITDETAILBARANG, listener, null);
        params = new HashMap<>();
        params.put("id_detail", id_detail);
        params.put("berat", berat);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}