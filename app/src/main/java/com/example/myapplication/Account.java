package com.example.myapplication;

import java.util.ArrayList;

public class Account {
    User user;
    ArrayList<Items> items;

    public Account(User user, ArrayList items) {
        this.user = user;
        this.items = items;
    }
    public void updatear(ArrayList<Items> arr){
        this.items=arr;
    }

}
