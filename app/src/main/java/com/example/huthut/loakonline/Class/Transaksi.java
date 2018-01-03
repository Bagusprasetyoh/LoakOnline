package com.example.huthut.loakonline.Class;

/**
 * Created by Huthut on 1/2/2018.
 */

public class Transaksi {
    private String id_transaksi;
    private String alamat;
    private String detail_alamat;
    private String jadwal_order;
    private String waktu_order;
    private String nama_penjual;
    private String no_telp;
    private String jam_buka;
    private String jam_tutup;

    public  Transaksi(String id_transaksi,  String alamat, String detail_alamat, String jadwal_order, String nama_penjual, String no_telp){
        this.id_transaksi = id_transaksi;
        this.alamat = alamat;
        this.detail_alamat = detail_alamat;
        this.jadwal_order = jadwal_order;
        this.nama_penjual = nama_penjual;
        this.no_telp = no_telp;
    }

    public  Transaksi(String id_transaksi,  String alamat, String jadwal_order, String nama_penjual, String no_telp){
        this.id_transaksi = id_transaksi;
        this.alamat = alamat;
        this.jadwal_order = jadwal_order;
        this.nama_penjual = nama_penjual;
        this.no_telp = no_telp;
    }

    public  Transaksi(String id_transaksi,  String alamat, String waktu_order, String nama_penjual, String no_telp, String jam_buka, String jam_tutup){
        this.id_transaksi = id_transaksi;
        this.alamat = alamat;
        this.waktu_order = waktu_order;
        this.nama_penjual = nama_penjual;
        this.no_telp = no_telp;
        this.jam_buka = jam_buka;
        this.jam_tutup = jam_tutup;
    }

    public String getId_transaksi(){
        return id_transaksi;
    }

    public String getNama_penjual(){
        return nama_penjual;
    }

    public String getNo_telp(){
        return no_telp;
    }

    public String getAlamat(){
        return alamat;
    }

    public String getDetail_alamat(){
        return detail_alamat;
    }

    public String getJadwal_order(){return jadwal_order;}

    public String getJambuka(){
        return jam_buka;
    }

    public String getJamtutup(){
        return jam_tutup;
    }

    public String getWaktuorder(){
        return waktu_order;
    }
}
