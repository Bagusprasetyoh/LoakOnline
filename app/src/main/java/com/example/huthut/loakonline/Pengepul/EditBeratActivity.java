package com.example.huthut.loakonline.Pengepul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.huthut.loakonline.Class.DetailTransaksi;
import com.example.huthut.loakonline.Penjual.DaftarBarangActivity;
import com.example.huthut.loakonline.Penjual.HapusBarangRequest;
import com.example.huthut.loakonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class EditBeratActivity extends AppCompatActivity {
    private Button submit;
    private String id_transaksi;
    private ListView listBerat;
    private ListAdapterEditDetail adapter;
    private ArrayList<DetailTransaksi> ArraylistDetailTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_berat);

        ArraylistDetailTransaksi = new ArrayList<>();
        listBerat = (ListView) findViewById(R.id.listEditBerat);

        submit = (Button) findViewById(R.id.submit);

        Bundle bundle = getIntent().getExtras();
        id_transaksi = bundle.getString("id_transaksi");

        GetDetailTransaksi();
        transaksiDone();
    }

    private void transaksiDone(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse = null;
                        boolean error = false;
                        try {
                            jsonResponse = new JSONObject(response);
                            error = jsonResponse.getBoolean("error");

                            if (!error) {
                                Intent intent = new Intent(EditBeratActivity.this, DaftarTransaksiActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                };

                TransaksiDoneRequest homeRequest = new TransaksiDoneRequest(id_transaksi, responseListener);
                RequestQueue queue = Volley.newRequestQueue(EditBeratActivity.this);
                queue.add(homeRequest);
            }
        });
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

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject catObj = array.getJSONObject(i);

                            String id_detail = catObj.getString("id_detail_transaksi");
                            String nama_produk = catObj.getString("nama_produk");
                            int berat = Integer.parseInt(catObj.getString("berat"));
                            int harga_standar = Integer.parseInt(catObj.getString("harga_standar"));
                            int harga = Integer.parseInt(catObj.getString("harga"));

                            DetailTransaksi loak = new DetailTransaksi(id_detail, nama_produk, berat,harga_standar,harga);
                            ArraylistDetailTransaksi.add(loak);
                        }

                        adapter = new ListAdapterEditDetail(EditBeratActivity.this,
                                R.layout.list_view_edit_berat,
                                ArraylistDetailTransaksi);
                        listBerat.setAdapter(adapter);

                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };

        HomePengepulRequest homeRequest = new HomePengepulRequest(id_transaksi, responseListener);
        RequestQueue queue = Volley.newRequestQueue(EditBeratActivity.this);
        queue.add(homeRequest);
    }

    public boolean editBarang(String id_detail, String berat){
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

        EditBeratRequest BarangRequest = new EditBeratRequest (id_detail, berat, responseListener);
        RequestQueue queue = Volley.newRequestQueue(EditBeratActivity.this);
        queue.add(BarangRequest);

        return respon[0];
    }
}
