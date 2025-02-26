package com.app.doctorapp.models;

import java.util.Date;

public class UserPatientModel {
    String user_uid;
    String user_name;
    String user_email;
    String user_image;
    String user_birthdate;
    String user_gender;
    String user_type;
    String user_mobile;
    Date user_join_date;

    public UserPatientModel() {
    }

    public String getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }

    public UserPatientModel(String user_uid, String user_name, String user_email, String user_image, String user_birthdate, String user_gender, String user_type, String user_mobile, Date user_join_date) {
        this.user_uid = user_uid;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_image = user_image;
        this.user_birthdate = user_birthdate;
        this.user_gender = user_gender;
        this.user_type = user_type;
        this.user_mobile = user_mobile;
        this.user_join_date = user_join_date;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

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

    public Date getUser_join_date() {
        return user_join_date;
    }

    public void setUser_join_date(Date user_join_date) {
        this.user_join_date = user_join_date;
    }
}
