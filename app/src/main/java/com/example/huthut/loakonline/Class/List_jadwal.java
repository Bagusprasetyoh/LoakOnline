package com.example.huthut.loakonline.Class;

/**
 * Created by Huthut on 12/31/2017.
 */

public class List_jadwal {
    private String id_jadwal;
    private String hari;
    private String jam_buka;
    private String jam_tutup;

    public List_jadwal(){}

    public List_jadwal(String id_jadwal, String hari, String jam_buka, String jam_tutup){
        this.id_jadwal = id_jadwal;
        this.hari = hari;
        this.jam_buka = jam_buka;
        this.jam_tutup =jam_tutup;
    }

    public String getId(){
        return this.id_jadwal;
    }

    public String getHari(){
        return hari;
    }

    public String getJamBuka(){
        return jam_buka;
    }

    public String getJamTutup(){
        return jam_tutup;
    }
}
