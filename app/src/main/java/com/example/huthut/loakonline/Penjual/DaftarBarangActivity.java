package com.example.huthut.loakonline.Penjual;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.huthut.loakonline.Class.Barang_loak;
import com.example.huthut.loakonline.R;
import com.example.huthut.loakonline.helper.SQLiteHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class DaftarBarangActivity extends AppCompatActivity {
    private ArrayList<Barang_loak> listDaftarBarang;
    private ListView list;
    private ListViewAdapterBarang adapter;
    private SQLiteHandler db;
    private Button cari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_barang);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listDaftarBarang = new ArrayList<>();
        list = (ListView) findViewById(R.id.daftarBarang);
        cari = (Button) findViewById(R.id.btn_cari);
        db = new SQLiteHandler(getApplicationContext());

        getDaftar();
        btnCari();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.tambah_barang);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarBarangActivity.this, KategoriActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void btnCari(){
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarBarangActivity.this, PilihJadwalActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getDaftar(){
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
                        JSONArray array  = result.getJSONArray("barang");

                        for(int i=0;i<array.length();i++) {
                            JSONObject catObj = array.getJSONObject(i);
                            String id = catObj.getString("id_detail_transaksi");
                            String produk = catObj.getString("nama_produk");
                            int harga = Integer.parseInt(catObj.getString("harga_standar"));
                            Barang_loak loak = new Barang_loak(id, produk, harga);
                            listDaftarBarang.add(loak);
                        }

                        adapter = new ListViewAdapterBarang(DaftarBarangActivity.this,
                                R.layout.list_view_barang,
                                listDaftarBarang);
                        list.setAdapter(adapter);
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };

        HashMap<String, String> user = db.getUserDetails();
        String uid = user.get("uid");

        DaftarBarangRequest barangRequest = new DaftarBarangRequest(uid, responseListener);
        RequestQueue queue = Volley.newRequestQueue(DaftarBarangActivity.this);
        queue.add(barangRequest);
    }

    public boolean hapusBarang(String id){
        final boolean[] respon = {FALSE};
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                boolean error = false;
                try {
                    JSONObject json = new JSONObject(response);
                    error = json.getBoolean("error");

                    if (!error) {
                        respon[0] = FALSE;
                    } else {
                        respon[0] = TRUE;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        HapusBarangRequest BarangRequest = new HapusBarangRequest(id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(DaftarBarangActivity.this);
        queue.add(BarangRequest);

        return respon[0];
    }
}
