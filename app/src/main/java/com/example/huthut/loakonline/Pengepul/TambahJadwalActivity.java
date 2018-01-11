package com.example.huthut.loakonline.Pengepul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.huthut.loakonline.Class.List_jadwal;
import com.example.huthut.loakonline.Penjual.JadwalAdapter;
import com.example.huthut.loakonline.Penjual.JadwalRequest;
import com.example.huthut.loakonline.R;
import com.example.huthut.loakonline.helper.SQLiteHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TambahJadwalActivity extends AppCompatActivity {
    private Spinner spnHari;
    private Spinner spnWaktu;
    private Button btnOK;
    private ArrayList<List_jadwal> listListjadwal;
    private JadwalAdapter adapter;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_jadwal);

        spnHari = (Spinner) findViewById(R.id.spnHari);
        spnWaktu = (Spinner) findViewById(R.id.spnWaktu);
        btnOK = (Button) findViewById(R.id.btnOK);
        listListjadwal = new ArrayList<>();
        db = new SQLiteHandler(getApplicationContext());

        spinnerHari();
        spinnerWaktu();
        buttonCari();
    }

    private void buttonCari(){
        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final List_jadwal listjadwal = (List_jadwal) spnWaktu.getSelectedItem();
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse = null;
                        boolean error = false;
                        try {
                            jsonResponse = new JSONObject(response);
                            error = jsonResponse.getBoolean("error");

                            if (!error){
                                Intent intent = new Intent(TambahJadwalActivity.this, MenuActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                };

                HashMap<String, String> user = db.getUserDetails();
                String uid = user.get("uid");

                TambahJadwalRequest jdwRequest = new TambahJadwalRequest(uid, listjadwal.getId(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(TambahJadwalActivity.this);
                queue.add(jdwRequest);
            }
        });
    }

    private void spinnerHari(){
        ArrayList<String> lables = new ArrayList<String>();
        lables.add("Minggu");
        lables.add("Senin");
        lables.add("Selasa");
        lables.add("Rabu");
        lables.add("Kamis");
        lables.add("Jumat");
        lables.add("Sabtu");
        spnHari.setAdapter(new ArrayAdapter<String>(TambahJadwalActivity.this, android.R.layout.simple_spinner_dropdown_item, lables));

        spnHari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listListjadwal.clear();
                List_jadwal jad = new List_jadwal("0", "", "", "Pilih List_jadwal - ");
                listListjadwal.add(jad);
                spnWaktu.setSelection(0);
                spinnerWaktu();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });
    }

    private void spinnerWaktu(){
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
                        JSONArray array  = result.getJSONArray("jadwal");

                        listListjadwal.clear();
                        List_jadwal jad = new List_jadwal("0", "", "", "Pilih List_jadwal - ");
                        listListjadwal.add(jad);

                        for(int i=0;i<array.length();i++) {
                            JSONObject catObj = array.getJSONObject(i);
                            String id = catObj.getString("id_jadwal");
                            String hari2 = catObj.getString("hari");
                            String jamB = catObj.getString("jam_buka");
                            String jamT = catObj.getString("jam_tutup");
                            List_jadwal jdw = new List_jadwal(id, hari2, jamB, jamT);
                            listListjadwal.add(jdw);
                        }

                        adapter = new JadwalAdapter(TambahJadwalActivity.this,
                                R.layout.support_simple_spinner_dropdown_item,
                                listListjadwal);
                        spnWaktu.setAdapter(adapter);
                        spnWaktu.setSelection(0);
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };

        JadwalRequest jdwRequest = new JadwalRequest(spnHari.getSelectedItem().toString(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(TambahJadwalActivity.this);
        queue.add(jdwRequest);
    }
}
