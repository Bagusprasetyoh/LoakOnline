package com.example.huthut.loakonline.Class;

/**
 * Created by Huthut on 12/3/2017.
 */

public class Status {

    private int id;
    private String status;

    public Status(){}

    public Status(int id, String status){
        this.id = id;
        this.status = status;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String status){
        this.status = status;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.status;
    }

}
