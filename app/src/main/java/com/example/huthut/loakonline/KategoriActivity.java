package com.example.huthut.loakonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.huthut.loakonline.Class.Kategori;
import com.example.huthut.loakonline.helper.SQLiteHandler;
import com.example.huthut.loakonline.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class KategoriActivity extends AppCompatActivity {
    private ArrayList<Kategori> listKategori;
    private ListView list;
    private ListViewAdapter adapter;
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (ListView) findViewById(R.id.kategori);
        listKategori = new ArrayList<>();

        GetKategori();
        pilihKategori();
    }

    private void GetKategori() {

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
                        JSONArray array  = result.getJSONArray("kategori");

                        for(int i=0;i<array.length();i++) {
                            JSONObject catObj = array.getJSONObject(i);
                            String id = catObj.getString("id_produk");
                            String kategor = catObj.getString("nama_produk");
                            Kategori cat = new Kategori(id, kategor);
                            listKategori.add(cat);
                        }

                        adapter = new ListViewAdapter(KategoriActivity.this,
                                R.layout.list_view_row_item,
                                listKategori);
                        list.setAdapter(adapter);
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };
        KategoriRequest kategoriRequest = new KategoriRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(KategoriActivity.this);
        queue.add(kategoriRequest);
    }

    private void pilihKategori() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id_produk;

                id_produk = String.valueOf(listKategori.get(i).getId());
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse = null;
                        boolean error = false;
                        try {
                            jsonResponse = new JSONObject(response);
                            error = jsonResponse.getBoolean("error");

                            if (!error){
                                Intent intent = new Intent(KategoriActivity.this, DaftarBarangActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                };

                // SqLite database handler
                db = new SQLiteHandler(getApplicationContext());

                // session manager
                session = new SessionManager(getApplicationContext());

                HashMap<String, String> user = db.getUserDetails();

                String uid = user.get("uid");

                TambahTransaksiRequest TambahRequest = new TambahTransaksiRequest(id_produk, uid, responseListener);
                RequestQueue queue = Volley.newRequestQueue(KategoriActivity.this);
                queue.add(TambahRequest);
            }
        });
    }

}
