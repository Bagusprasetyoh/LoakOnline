package com.example.huthut.loakonline.Class;

/**
 * Created by Huthut on 1/2/2018.
 */

public class DetailTransaksi {
    private String id_Detail_transaksi;
    private String id_transaksi;
    private String alamat;
    private String detail_alamat;
    private String nama_penjual;
    private String no_telp;

    private String jadwal_order;
    private String nama_produk;
    private int berat;
    private int harga_standar;
    private int harga;

    public DetailTransaksi(){}

    public DetailTransaksi(String nama_produk, int berat, int harga_standar, int harga){
        // this.id_Detail_transaksi = id_Detail_transaksi;

        this.nama_produk = nama_produk;
        this.berat = berat;
        this.harga_standar = harga_standar;
        this.harga = harga;

    }

    public DetailTransaksi(String id_Detail_transaksi, String nama_produk, int berat, int harga_standar, int harga){
        this.id_Detail_transaksi = id_Detail_transaksi;
        this.nama_produk = nama_produk;
        this.berat = berat;
        this.harga_standar = harga_standar;
        this.harga = harga;

    }

    public String getId_Detail_transaksi(){
        return id_Detail_transaksi;
    }

    public String getNama_produk(){
        return nama_produk;
    }

    public Integer getHarga_standar(){return harga_standar;}

    public Integer get_harga(){return harga;}

    public Integer get_berat(){return berat;}

}
