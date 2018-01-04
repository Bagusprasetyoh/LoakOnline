package com.example.huthut.loakonline.Class;

/**
 * Created by Huthut on 1/4/2018.
 */

public class Stok {
    private String id;
    private String nama_produk;
    private int stok;

    public Stok(String id, String nama_produk, int stok){
        this.id = id;
        this.nama_produk = nama_produk;
        this.stok = stok;
    }

    public String getId(){
        return id;
    }

    public String getNamaProduk(){
        return nama_produk;
    }

    public int getStok(){
        return stok;
    }
}
