package com.example.tommylee.myapplication.detail.tabbed.cmSection;

public class cmModel {
    String author;
    String desc;
    String created_date;
    String image;

    public cmModel(String author, String desc, String created_date, String image) {
        this.author = author;
        this.desc = desc;
        this.created_date = created_date;
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public String getDesc() {
        return desc;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getImage() {
        return image;
    }
}
