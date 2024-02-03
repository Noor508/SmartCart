package com.example.devilapplication.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//@Entity(tableName = "users")
//public class Users implements Serializable {
//
//    @PrimaryKey(autoGenerate = true)
//    private int id;
//
//    @ColumnInfo(name = "name")
//    private String name;
//
//    @ColumnInfo(name = "price")
//    private String price;
//
//    public Users(int id, String name,String price){
//        this.id=id;
//        this.name= name;
//        this.price=price;
//    }
//
//    @Ignore
//    Users(String name){
//        this.name= name;
//        this.price=price;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//}



@Entity(tableName = "users")
public class Users implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public Users(int id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @ColumnInfo(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @ColumnInfo(name = "price")
    private String price;

    // Constructors, getters, setters, etc.

}

