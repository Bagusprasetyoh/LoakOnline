package com.example.huthut.loakonline.Class;

/**
 * Created by Huthut on 1/4/2018.
 */

public class Pengepul {
    private String nama;
    private String alamat;
    private String no_telp;

    public Pengepul(String nama, String alamat, String no_telp){
        this.no_telp = no_telp;
        this.alamat = alamat;
        this.nama = nama;
    }

    public String getNamaPengepul(){
        return nama;
    }

    public String getNo_telp(){
        return no_telp;
    }

    public String getAlamat(){
        return alamat;
    }

}
