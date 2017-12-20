package com.example.huthut.loakonline.Class;

/**
 * Created by Huthut on 12/10/2017.
 */

public class Kategori {
    private String id;
    private String kategori;

    public Kategori(){}

    public Kategori(String id, String kategori){
        this.id = id;
        this.kategori = kategori;
    }

    public String getId(){
        return id;
    }

    public String getNamaKategori(){
        return kategori;
    }
}
