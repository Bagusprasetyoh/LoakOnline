package com.example.huthut.loakonline.Pengepul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.example.huthut.loakonline.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnTransaksi = (Button) findViewById(R.id.btnTransaksi);
        Button btnJadwal = (Button) findViewById(R.id.btnJadwal);
        Button btnStok = (Button) findViewById(R.id.btnStok);

        btnTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DaftarTransaksiActivity.class);
                startActivity(intent);
            }
        });

        btnJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, TambahJadwalActivity.class);
                startActivity(intent);
            }
        });

        btnStok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, StokActivity.class);
                startActivity(intent);
            }
        });
    }
}
