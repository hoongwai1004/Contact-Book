package com.example.contacts;

import java.io.Serializable;

/**
 * Created by 정예린 on 11/15/2017.
 */

public class Contact implements Serializable {

    private String id, name, phone, email, address;

    public Contact(String id, String name, String phone, String email, String address){
        this.id= id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public String getEmail(){
        return email;
    }

    public String getAddress(){
        return address;
    }
}
