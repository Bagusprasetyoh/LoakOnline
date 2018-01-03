package com.example.huthut.loakonline.Penjual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.huthut.loakonline.R;
import com.example.huthut.loakonline.helper.SQLiteHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class CariActivity extends AppCompatActivity {
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);
        Bundle bundle = getIntent().getExtras();
        String id_jadwal = bundle.getString("id_jadwal");
        String latitude = bundle.getString("latitude");
        String longitude = bundle.getString("longitude");
        String alamat = bundle.getString("alamat");

        db = new SQLiteHandler(getApplicationContext());
        getCari(id_jadwal, latitude, longitude, alamat);
    }

    private void getCari(String id_jadwal, String latitude, String longitude, String alamat){
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                JSONObject jsonResponse = null;
                boolean error = false;
                try {
                    jsonResponse = new JSONObject(response);
                    error = jsonResponse.getBoolean("error");

                    if (!error){
                        JSONObject result = new JSONObject(response);
                        JSONArray array  = result.getJSONArray("pengepul");
                        Toast.makeText(CariActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };


        HashMap<String, String> user = db.getUserDetails();
        String uid = user.get("uid");

        CariRequest cari = new CariRequest(uid, id_jadwal, latitude, longitude, alamat, responseListener);
        RequestQueue queue = Volley.newRequestQueue(CariActivity.this);
        queue.add(cari);

    }
}
