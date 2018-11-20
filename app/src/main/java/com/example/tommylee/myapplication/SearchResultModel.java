package com.example.tommylee.myapplication;

import java.util.ArrayList;

public class SearchResultModel {
    private int id ;
    private String CHtitle;
    private String ENGtitle;
    private String full_address;
    private String phone_num;
    private double _lat;
    private double _long;
    private String district;
    private String street;
    private String imageUrl;
    private ArrayList<Integer> imageU;
    public SearchResultModel(int id, String CHtitle, String ENGtitle, String full_address, String phone_num, double _lat, double _long, String district, String street,String imageUrl) {
        this.id = id;
        this.CHtitle = CHtitle;
        this.ENGtitle = ENGtitle;
        this.full_address = full_address;
        this.phone_num = phone_num;
        this._lat = _lat;
        this._long = _long;
        this.district = district;
        this.street = street;
        this.imageUrl = imageUrl;
    }
    public SearchResultModel(int id, String CHtitle, String ENGtitle, String full_address, String phone_num, double _lat, double _long, String district, String street,ArrayList<Integer> imageU) {
        this.id = id;
        this.CHtitle = CHtitle;
        this.ENGtitle = ENGtitle;
        this.full_address = full_address;
        this.phone_num = phone_num;
        this._lat = _lat;
        this._long = _long;
        this.district = district;
        this.street = street;
        this.imageU = imageU;
    }
    public int getId() {
        return id;
    }

    public String getCHtitle() {
        return CHtitle;
    }

    public String getENGtitle() {
        return ENGtitle;
    }

    public String getFull_address() {
        return full_address;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public double get_lat() {
        return _lat;
    }

    public double get_long() {
        return _long;
    }

    public String getDistrict() {
        return district;
    }

    public String getStreet() {
        return street;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public ArrayList<Integer> getImageU(){
        return imageU;
    }
}
