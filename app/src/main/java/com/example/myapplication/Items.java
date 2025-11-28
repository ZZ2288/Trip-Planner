package com.example.myapplication;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Items {

    String date;
    String type;
    String name;
    String Location;
    String country;

    public Items(String name, String date, String location, String type, String country) {
        this.name = name;
            this.date = date;


        this.country = country
        ;
        this.Location = location;
        this.type = type;


    }



}
