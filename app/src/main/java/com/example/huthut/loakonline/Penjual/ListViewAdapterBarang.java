package com.example.huthut.loakonline.Penjual;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.huthut.loakonline.Class.Barang_loak;
import com.example.huthut.loakonline.R;

import java.util.List;

import static java.lang.Boolean.FALSE;

/**
 * Created by Huthut on 12/17/2017.
 */

public class ListViewAdapterBarang extends ArrayAdapter<Barang_loak> {

    Context mContext;
    int layoutResourceId;
    private List<Barang_loak> data;
    private DaftarBarangActivity daftar;

    public ListViewAdapterBarang(Context mContext, int layoutResourceId, List<Barang_loak> data) {

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
        final Barang_loak loak = data.get(position);

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView textViewItem = (TextView) convertView.findViewById(R.id.textViewItem);
        textViewItem.setText(loak.getNamaProduk());
        textViewItem.setTag(loak.getId());

        TextView harga = (TextView) convertView.findViewById(R.id.harga_textview);
        harga.setText(String.valueOf(loak.getHargaStandar()));

        final Button hapusButton = (Button) convertView.findViewById(R.id.hapus_button);
        hapusButton.setTag(loak.getId());

        hapusButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mContext instanceof DaftarBarangActivity){
                    boolean stat = ((DaftarBarangActivity)mContext).hapusBarang(String.valueOf(v.getTag()));

                    if( stat == FALSE ) {
                        data.remove(position);
                        notifyDataSetChanged();//refresh adapter
                    }
                }
            }
        });

        return convertView;

    }

}
