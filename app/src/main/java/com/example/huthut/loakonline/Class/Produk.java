package com.example.huthut.loakonline.Class;

/**
 * Created by Huthut on 12/17/2017.
 */

public class Produk {
    private String id_detail;
    private String nama_produk;
    private int hargaStandar;

    public Produk(String id_detail, String nama_produk, int hargaStandar){
        this.id_detail = id_detail;
        this.nama_produk = nama_produk;
        this.hargaStandar = hargaStandar;
    }

    public String getId(){
        return id_detail;
    }

    public String getNamaProduk(){
        return nama_produk;
    }

    public int getHargaStandar(){
        return hargaStandar;
    }
}
