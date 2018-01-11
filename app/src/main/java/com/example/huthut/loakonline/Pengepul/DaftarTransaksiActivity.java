package com.example.huthut.loakonline.Pengepul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.huthut.loakonline.Class.Transaksi;
import com.example.huthut.loakonline.R;
import com.example.huthut.loakonline.helper.SQLiteHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DaftarTransaksiActivity extends AppCompatActivity {
    private SQLiteHandler db;
    private ArrayList<Transaksi> listTransaksi;
    private ListView list;
    private ListAdapterAllTransaksi adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_transaksi);
        db = new SQLiteHandler(getApplicationContext());
        listTransaksi = new ArrayList<>();
        list = (ListView) findViewById(R.id.daftarTransaksi);

        getAllTransaksi();
        selectTransaksi();
    }

    private void getAllTransaksi(){
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
                        JSONArray array  = result.getJSONArray("transaksi");

                        for (int i = 0; i < 1 ; i++) {
                            JSONObject catObj = array.getJSONObject(i);
                            String id_transaksi = catObj.getString("id_transaksi");
                            String alamat = catObj.getString("alamat");
                            String jadwal_order = catObj.getString("jadwal_order");
                            String nama_penjual = catObj.getString("nama_penjual");
                            String no_telp = catObj.getString("no_telp");

                            Transaksi loak = new Transaksi(id_transaksi, alamat, jadwal_order, nama_penjual, no_telp);
                            listTransaksi.add(loak);
                        }

                        adapter = new ListAdapterAllTransaksi(DaftarTransaksiActivity.this,
                                R.layout.list_view_daftar_transaksi,
                                listTransaksi);
                        list.setAdapter(adapter);
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };

        HashMap<String, String> user = db.getUserDetails();
        String uid = user.get("uid");

        DaftarTransaksiRequest transRequest = new DaftarTransaksiRequest(uid, responseListener);
        RequestQueue queue = Volley.newRequestQueue(DaftarTransaksiActivity.this);
        queue.add(transRequest);
    }

    private void selectTransaksi(){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id_transaksi = String.valueOf(listTransaksi.get(i).getId_transaksi());
                Bundle bundle = new Bundle();
                bundle.putString("id_transaksi", id_transaksi);
                Intent intent = new Intent(DaftarTransaksiActivity.this, HomePengepulActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
