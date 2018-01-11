package com.example.huthut.loakonline.Penjual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huthut.loakonline.R;

public class DetailPengepulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengepul);

        Bundle bundle = getIntent().getExtras();
        String nama = bundle.getString("nama");
        String alamat = bundle.getString("alamat");
        String telp = bundle.getString("no_telp");

        TextView nam = (TextView) findViewById(R.id.textnama2);
        nam.setText(nama);

        TextView ala = (TextView) findViewById(R.id.textalamat2);
        ala.setText(alamat);

        TextView tel = (TextView) findViewById(R.id.textno2);
        tel.setText(telp);

        Button okey = (Button) findViewById(R.id.okey);

        okey.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPengepulActivity.this, DaftarBarangActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
