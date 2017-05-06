package com.example.lauciano.dinheiroviajante.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lauciano on 5/5/2017.
 */

public class Transaction implements Serializable{

    //Attributes
    private Long id;
    private boolean income;
    private double value;
    private Date date;
    private String info;

    //Constructor
    public Transaction() {
        income = false;
        value = 0.0;
        date = new Date();
        info = "";
    }

    //Other methods
    public String getDateAsString(String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public Double getSignedValue() {
        return income?value:-value;
    }

    public void setDateFromString(String date, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            this.date = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "R$ " + this.getValue();
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isIncome() {
        return income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
