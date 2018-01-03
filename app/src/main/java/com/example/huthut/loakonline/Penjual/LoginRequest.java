package com.example.huthut.loakonline.Penjual;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.huthut.loakonline.helper.AppConfig.URL_LOGIN;

/**
 * Created by Hut Hut on 5/3/2017.
 */

public class LoginRequest extends StringRequest {

    private Map<String, String> params;

    public LoginRequest(String username, String password, String status, Response.Listener<String> listener){
        super(Method.POST,URL_LOGIN, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("status", status);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
