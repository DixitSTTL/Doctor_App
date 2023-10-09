package com.app.doctorapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DateModel implements Parcelable {
    String day;
    String month;
    String year;

    public DateModel(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    protected DateModel(Parcel in) {
        day = in.readString();
        month = in.readString();
        year = in.readString();
    }

    public static final Creator<DateModel> CREATOR = new Creator<DateModel>() {
        @Override
        public DateModel createFromParcel(Parcel in) {
            return new DateModel(in);
        }

        @Override
        public DateModel[] newArray(int size) {
            return new DateModel[size];
        }
    };

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(day);
        dest.writeString(month);
        dest.writeString(year);
    }
}
