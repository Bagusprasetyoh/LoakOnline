package com.example.huthut.loakonline.Pengepul;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.huthut.loakonline.Class.Stok;
import com.example.huthut.loakonline.Class.Transaksi;
import com.example.huthut.loakonline.R;
import com.example.huthut.loakonline.helper.SQLiteHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class StokActivity extends AppCompatActivity {
    private SQLiteHandler db;
    private ArrayList<Stok> listStok;
    private ListView list;
    private ListAdapterStok adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stok);
        db = new SQLiteHandler(getApplicationContext());
        listStok = new ArrayList<>();
        list = (ListView) findViewById(R.id.listStok);

        ambilStok();
    }

    private void ambilStok(){
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
                        JSONArray array  = result.getJSONArray("stok");

                        for (int i = 0; i < array.length() ; i++) {
                            JSONObject catObj = array.getJSONObject(i);
                            String id = catObj.getString("id_produk_gudang");
                            String nama = catObj.getString("nama_produk_gudang");
                            String stok = catObj.getString("total_stok");

                            Stok gudang = new Stok(id, nama, Integer.parseInt(stok));
                            listStok.add(gudang);
                        }

                        adapter = new ListAdapterStok(StokActivity.this,
                                R.layout.list_view_stok,
                                listStok);
                        list.setAdapter(adapter);
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };

        HashMap<String, String> user = db.getUserDetails();
        String uid = user.get("uid");

        StokRequest stok = new StokRequest(uid, responseListener);
        RequestQueue queue = Volley.newRequestQueue(StokActivity.this);
        queue.add(stok);
    }
}
