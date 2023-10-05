package com.app.doctorapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class UserDoctorModel implements Parcelable {

    String user_uid;
    String user_name;
    String user_email;
    String user_birthdate;
    String user_gender;
    String user_type;
    String user_mobile;
    String user_image;
    float user_Rating;
    int user_review_count;
    String user_category;
    Date user_join_date;

    public UserDoctorModel() {
    }

    public UserDoctorModel(String user_uid) {
    }

    public UserDoctorModel(String user_uid, String user_name, String user_email, String user_birthdate, String user_gender, String user_type, String user_mobile, String user_image, float user_Rating, int user_review_count, String user_category, Date user_join_date) {
        this.user_uid = user_uid;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_birthdate = user_birthdate;
        this.user_gender = user_gender;
        this.user_type = user_type;
        this.user_mobile = user_mobile;
        this.user_image = user_image;
        this.user_Rating = user_Rating;
        this.user_review_count = user_review_count;
        this.user_category = user_category;
        this.user_join_date = user_join_date;
    }

    protected UserDoctorModel(Parcel in) {
        user_uid = in.readString();
        user_name = in.readString();
        user_email = in.readString();
        user_birthdate = in.readString();
        user_gender = in.readString();
        user_type = in.readString();
        user_mobile = in.readString();
        user_image = in.readString();
        user_Rating = in.readFloat();
        user_review_count = in.readInt();
        user_category = in.readString();
    }

    public static final Creator<UserDoctorModel> CREATOR = new Creator<UserDoctorModel>() {
        @Override
        public UserDoctorModel createFromParcel(Parcel in) {
            return new UserDoctorModel(in);
        }

        @Override
        public UserDoctorModel[] newArray(int size) {
            return new UserDoctorModel[size];
        }
    };

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_birthdate() {
        return user_birthdate;
    }

    public void setUser_birthdate(String user_birthdate) {
        this.user_birthdate = user_birthdate;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public float getUser_Rating() {
        return user_Rating;
    }

    public void setUser_Rating(float user_Rating) {
        this.user_Rating = user_Rating;
    }

    public int getUser_review_count() {
        return user_review_count;
    }

    public void setUser_review_count(int user_review_count) {
        this.user_review_count = user_review_count;
    }

    public String getUser_category() {
        return user_category;
    }

    public void setUser_category(String user_category) {
        this.user_category = user_category;
    }

    public Date getUser_join_date() {
        return user_join_date;
    }

    public void setUser_join_date(Date user_join_date) {
        this.user_join_date = user_join_date;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(user_uid);
        dest.writeString(user_name);
        dest.writeString(user_email);
        dest.writeString(user_birthdate);
        dest.writeString(user_gender);
        dest.writeString(user_type);
        dest.writeString(user_mobile);
        dest.writeString(user_image);
        dest.writeFloat(user_Rating);
        dest.writeInt(user_review_count);
        dest.writeString(user_category);
    }
}
