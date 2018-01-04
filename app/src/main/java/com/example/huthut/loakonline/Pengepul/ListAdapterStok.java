package com.example.huthut.loakonline.Pengepul;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.huthut.loakonline.Class.Stok;
import com.example.huthut.loakonline.Class.Transaksi;
import com.example.huthut.loakonline.R;

import java.util.List;

/**
 * Created by Huthut on 1/4/2018.
 */

public class ListAdapterStok extends ArrayAdapter<Stok> {

    Context mContext;
    int layoutResourceId;
    private List<Stok> data;

    public ListAdapterStok(Context mContext, int layoutResourceId, List<Stok> data) {
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
        if (convertView == null) {
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        final Stok gudang = data.get(position);

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView txtNama = (TextView) convertView.findViewById(R.id.textViewItem);
        txtNama.setText(gudang.getNamaProduk());

        TextView txtNo = (TextView) convertView.findViewById(R.id.txtStok);
        txtNo.setText(String.valueOf(gudang.getStok()));

        return convertView;
    }
}
