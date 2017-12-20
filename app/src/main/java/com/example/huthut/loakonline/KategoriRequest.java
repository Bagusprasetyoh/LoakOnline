package com.example.huthut.loakonline;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import static com.example.huthut.loakonline.helper.AppConfig.URL_KATEGORI;

/**
 * Created by Huthut on 12/10/2017.
 */

public class KategoriRequest extends StringRequest {

    public KategoriRequest(Response.Listener<String> listener){
        super(Method.POST,URL_KATEGORI, listener, null);
    }
}
