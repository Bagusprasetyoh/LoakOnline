package com.example.huthut.loakonline.Class;

/**
 * Created by Huthut on 1/8/2018.
 */

public class Penjual {
    String id_penjual;
    String nama_penjual;
    String alamat;
    String no_telp;

    public Penjual(String id_penjual, String nama_penjual, String alamat, String no_telp){
        this.id_penjual = id_penjual;
        this.nama_penjual = nama_penjual;
        this.alamat = alamat;
        this.no_telp = no_telp;
    }

    public String getIdPenjual(){
        return id_penjual;
    }

    public String getNamaPenjual(){
        return nama_penjual;
    }

    public String getAlamat(){
        return alamat;
    }

    public String getNoTelp(){
        return no_telp;
    }

}
