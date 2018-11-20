package com.example.tommylee.myapplication.detail;

import android.os.Parcel;
import android.os.Parcelable;

public class CouponModel implements Parcelable {
    private String name;
    private int timelimit;
    private int originPrice;
    private int newPrice;
    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(timelimit);
        out.writeInt(originPrice);
        out.writeInt(newPrice);
    }

    public CouponModel(String name, int timelimit, int originPrice, int newPrice) {
        this.name = name;
        this.timelimit = timelimit;
        this.originPrice = originPrice;
        this.newPrice = newPrice;
    }

    public String getName() {
        return name;
    }

    public int getTimelimit() {
        return timelimit;
    }

    public int getOriginPrice() {
        return originPrice;
    }

    public int getNewPrice() {
        return newPrice;
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<CouponModel> CREATOR = new Parcelable.Creator<CouponModel>() {
        public CouponModel createFromParcel(Parcel in) {
            return new CouponModel(in);
        }

        public CouponModel[] newArray(int size) {
            return new CouponModel[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private CouponModel(Parcel in) {
        name = in.readString();
        timelimit = in.readInt();
        originPrice = in.readInt();
        newPrice = in.readInt();
    }
}
