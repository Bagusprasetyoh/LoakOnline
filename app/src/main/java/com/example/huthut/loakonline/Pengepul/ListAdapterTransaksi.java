package com.example.huthut.loakonline.Pengepul;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.huthut.loakonline.Class.Transaksi;
import com.example.huthut.loakonline.R;

import java.util.List;

/**
 * Created by Huthut on 1/2/2018.
 */

public class ListAdapterTransaksi extends ArrayAdapter<Transaksi> {

    Context mContext;
    int layoutResourceId;
    private List<Transaksi> data;
    //private DaftarBarangActivity daftar;

    public ListAdapterTransaksi(Context mContext, int layoutResourceId, List<Transaksi> data) {
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
        final Transaksi loak = data.get(position);

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView txtTransaksi = (TextView) convertView.findViewById(R.id.id_transaksi);
        txtTransaksi.setText(loak.getId_transaksi());
        // textView.setTag(loak.getId_Detail_Transaksi());

        TextView txtNama = (TextView) convertView.findViewById(R.id.nama_penjual);
        txtNama.setText(loak.getNama_penjual());

        TextView txtAlamat = (TextView) convertView.findViewById(R.id.alamat);
        txtAlamat.setText(loak.getAlamat());

        TextView txtOrder = (TextView) convertView.findViewById(R.id.waktu_order);
        txtOrder.setText(loak.getWaktuorder());

        TextView txtTelp = (TextView) convertView.findViewById(R.id.no_telp);
        txtTelp.setText(loak.getNo_telp());

        TextView txtJam = (TextView) convertView.findViewById(R.id.jam);
        txtTelp.setText(loak.getJambuka()+" - "+loak.getJamtutup());


        return convertView;

    }
}
