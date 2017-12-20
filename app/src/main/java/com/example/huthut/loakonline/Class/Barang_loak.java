package com.example.huthut.loakonline.Class;

/**
 * Created by Huthut on 12/17/2017.
 */

public class Barang_loak {
    private String id_detail;
    private String id_transaksi;
    private String id_produk;
    private String nama_produk;
    private int hargaStandar;
    private int berat;
    private int hargaTotal;

    public Barang_loak(String id_detail, String nama_produk, int hargaStandar){
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
