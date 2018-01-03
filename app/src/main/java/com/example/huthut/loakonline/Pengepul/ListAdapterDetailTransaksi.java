package com.example.huthut.loakonline.Pengepul;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.huthut.loakonline.Class.DetailTransaksi;
import com.example.huthut.loakonline.R;

import java.util.List;

/**
 * Created by Huthut on 1/2/2018.
 */

public class ListAdapterDetailTransaksi extends ArrayAdapter<DetailTransaksi> {

    Context mContext;
    int layoutResourceId;
    private List<DetailTransaksi> data;
    //private DaftarBarangActivity daftar;

    public ListAdapterDetailTransaksi(Context mContext, int layoutResourceId, List<DetailTransaksi> data) {
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

        TextView textView2 = (TextView) convertView.findViewById(R.id.nama_produk);
        textView2.setText(loak.getNama_produk());

        TextView textView4 = (TextView) convertView.findViewById(R.id.harga);
        textView4.setText(Integer.toString(loak.getHarga_standar()));


        return convertView;

    }

}
