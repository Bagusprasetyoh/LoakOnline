package com.example.huthut.loakonline.Pengepul;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huthut.loakonline.Class.DetailTransaksi;
import com.example.huthut.loakonline.Penjual.DaftarBarangActivity;
import com.example.huthut.loakonline.R;

import java.util.List;

import static java.lang.Boolean.FALSE;

/**
 * Created by Huthut on 1/3/2018.
 */

public class ListAdapterEditDetail extends ArrayAdapter<DetailTransaksi> {

    Context mContext;
    int layoutResourceId;
    private List<DetailTransaksi> data;
    //private DaftarBarangActivity daftar;

    public ListAdapterEditDetail(Context mContext, int layoutResourceId, List<DetailTransaksi> data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        /*
         * The convertView argument is essentially a "ScrapView" as described is Lucas post
         * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
         * It will have a non-null value when ListView is asking you recycle the row layout.
         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
         */
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        final DetailTransaksi loak = data.get(position);

        TextView txtnama = (TextView) convertView.findViewById(R.id.nama_produk);
        txtnama.setText(loak.getNama_produk());

        final EditText edtBerat = (EditText) convertView.findViewById(R.id.berat);
        edtBerat.setText(String.valueOf(loak.get_berat()));

        Button oke = (Button) convertView.findViewById(R.id.ok);
        oke.setTag(loak.getId_Detail_transaksi());

        oke.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mContext instanceof EditBeratActivity){
                    boolean stat = ((EditBeratActivity)mContext).editBarang(String.valueOf(v.getTag()), String.valueOf(edtBerat.getText()));

                    if ( stat == FALSE ) {
                        Toast.makeText(mContext, "Berhasil", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return convertView;
    }

}

