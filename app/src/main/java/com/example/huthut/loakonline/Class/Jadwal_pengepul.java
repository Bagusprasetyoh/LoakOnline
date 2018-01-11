package com.example.huthut.loakonline.Class;

/**
 * Created by Huthut on 1/9/2018.
 */

public class Jadwal_pengepul {
    String id_list_jadwal;
    String id_jadwal_pengepul;
    String id_pengepul;

    Jadwal_pengepul(String id_list_jadwal, String id_jadwal_pengepul, String id_pengepul){
        this.id_list_jadwal = id_list_jadwal;
        this.id_jadwal_pengepul = id_jadwal_pengepul;
        this.id_pengepul = id_pengepul;
    }

    public String getIdListJadwal(){
        return id_list_jadwal;
    }

    public String getIdJadwalPengepul(){
        return id_jadwal_pengepul;
    }

    public String getIdPengepul(){
        return id_pengepul;
    }
}
