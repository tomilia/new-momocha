package com.example.tommylee.myapplication.main_frag;

public class MemberStruct {
    int id;
    String first_name;
    String username;
    String email;
    int mcash;
    String phone_num;

    public MemberStruct(int id, boolean lgin, String first_name, String username, String email, int mcash, String phone_num) {
        this.id = id;
        this.first_name = first_name;
        this.username = username;
        this.email = email;
        this.mcash = mcash;
        this.phone_num = phone_num;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getUsername() {

            return username;

    }

    public String getEmail() {
        return email;
    }

    public int getMcash() {
        return mcash;
    }

    public String getPhone_num() {
        return phone_num;
    }
}
