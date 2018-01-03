package com.example.huthut.loakonline.Pengepul;

import android.content.Context;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.huthut.loakonline.Class.DetailTransaksi;
import com.example.huthut.loakonline.Class.Transaksi;
import com.example.huthut.loakonline.Penjual.ListViewAdapter;
import com.example.huthut.loakonline.R;
import com.example.huthut.loakonline.Class.Status;
import com.example.huthut.loakonline.helper.SQLiteHandler;
import com.example.huthut.loakonline.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HomePengepulActivity extends AppCompatActivity {
    private ArrayList<DetailTransaksi> ArraylistDetailTransaksi;
    private ArrayList<Transaksi> ArraylistTransaksi;
    private ListView list;
    private ListView list2;
    private ListAdapterDetailTransaksi adapter;
    private ListAdapterTransaksi adapter2;
    private SQLiteHandler db;
    private String id_transaksi;
    private SessionManager session;
    private Button btnbeli;
    private Intent intent;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pengepul);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArraylistDetailTransaksi = new ArrayList<>();
        list = (ListView) findViewById(R.id.listDetailTransaksi);

        ArraylistTransaksi = new ArrayList<>();
        list2= (ListView) findViewById(R.id.listTransaksi);
        db = new SQLiteHandler(getApplicationContext());

        btnbeli = (Button) findViewById(R.id.terima);
        btnbeli.setOnClickListener(terima);

        Bundle bundle = getIntent().getExtras();
        id_transaksi = bundle.getString("id_transaksi");

        //GetUser();
        GetTransaksi();
        GetDetailTransaksi();

        // terimaDetailTransaksi();
    }

    private void GetDetailTransaksi(){
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                JSONObject jsonResponse = null;
                boolean error = false;
                try {
                    jsonResponse = new JSONObject(response);
                    error = jsonResponse.getBoolean("error");

                    if (!error) {
                        JSONObject result = new JSONObject(response);
                        JSONArray array = result.getJSONArray("detail_transaksi");

            //            Toast.makeText(context, String.valueOf(array.length()), Toast.LENGTH_SHORT).show();

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject catObj = array.getJSONObject(i);

                            String nama_produk = catObj.getString("nama_produk");
                            int berat = Integer.parseInt(catObj.getString("berat"));
                            int harga_standar = Integer.parseInt(catObj.getString("harga_standar"));
                            int harga = Integer.parseInt(catObj.getString("harga"));
                            //Toast.makeText(context, nama_produk, Toast.LENGTH_LONG).show();

                            DetailTransaksi loak = new DetailTransaksi(nama_produk, berat,harga_standar,harga);
                            ArraylistDetailTransaksi.add(loak);
                        }

                        adapter = new ListAdapterDetailTransaksi(HomePengepulActivity.this,
                                R.layout.list_view_home_pengepul,
                                ArraylistDetailTransaksi);
                        list.setAdapter(adapter);

                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };

        HomePengepulRequest homeRequest = new HomePengepulRequest(id_transaksi, responseListener);
        RequestQueue queue = Volley.newRequestQueue(HomePengepulActivity.this);
        queue.add(homeRequest);
    }


    private void GetTransaksi(){
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                JSONObject jsonResponse = null;
                boolean error = false;
                try {
                    jsonResponse = new JSONObject(response);
                    error = jsonResponse.getBoolean("error");

                    if (!error) {
                        Log.i("tagconvertstr", "["+response+"]");
                        JSONObject result = new JSONObject(response);

                        JSONArray array = result.getJSONArray("transaksi");

                        //JSONObject result2 = new JSONObject(response);

                        for (int i = 0; i < 1 ; i++) {
                            JSONObject catObj = array.getJSONObject(i);
                            // String id = catObj.getString("id_detail_transaksi");
                            String id_transaksi = catObj.getString("id_transaksi");
                            String alamat = catObj.getString("alamat");
                            String waktu_order = catObj.getString("waktu_order");
                            String nama_penjual = catObj.getString("nama_penjual");
                            String no_telp = catObj.getString("no_telp");
                            String jam_buka = catObj.getString("jam_buka");
                            String jam_tutup = catObj.getString("jam_tutup");
                            String total_harga = catObj.getString("total_harga");

                            Transaksi loak2 = new Transaksi(id_transaksi, alamat, waktu_order, nama_penjual, no_telp,jam_buka, jam_tutup );
                            ArraylistTransaksi.add(loak2);
                        }


                        adapter2 = new ListAdapterTransaksi(HomePengepulActivity.this,
                                R.layout.list_view_transaksi,
                                ArraylistTransaksi);
                        list2.setAdapter(adapter2);

                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }
        };

        TransaksiRequest tranRequest = new TransaksiRequest(id_transaksi, responseListener);
        RequestQueue queue = Volley.newRequestQueue(HomePengepulActivity.this);
        queue.add(tranRequest);
    }

    private View.OnClickListener terima = new View.OnClickListener(){
        public void onClick(View v){


            Response.Listener<String> responseListener = new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    JSONObject jsonResponse = null;
                    boolean error = false;
                    try {
                        jsonResponse = new JSONObject(response);
                        error = jsonResponse.getBoolean("error");

                        if (!error) {
                            Bundle bundle = new Bundle();
                            bundle.putString("id_transaksi", id_transaksi);
                            Intent intent = new Intent(HomePengepulActivity.this, EditBeratActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }

                }
            };

            HashMap<String, String> user = db.getUserDetails();
            String uid = user.get("uid");

            PenjualRequest penjualRequest = new PenjualRequest(uid, id_transaksi, responseListener);
            RequestQueue queue = Volley.newRequestQueue(HomePengepulActivity.this);
            queue.add(penjualRequest);
        }

    };


}
