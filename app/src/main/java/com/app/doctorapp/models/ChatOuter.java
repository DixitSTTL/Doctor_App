package com.app.doctorapp.models;

import java.util.Date;

public class ChatOuter {

    Date time;
    String patient_uid;
    String patient_name;
    String patient_image;


    String doctor_uid;
    String doctor_name;
    String doctor_category;
    String doctor_image;
    String last_message;

    public ChatOuter() {
    }

    public ChatOuter(Date time, String patient_uid, String patient_name, String patient_image, String doctor_uid, String doctor_name, String doctor_category, String doctor_image, String last_message) {
        this.time = time;
        this.patient_uid = patient_uid;
        this.patient_name = patient_name;
        this.patient_image = patient_image;
        this.doctor_uid = doctor_uid;
        this.doctor_name = doctor_name;
        this.doctor_category = doctor_category;
        this.doctor_image = doctor_image;
        this.last_message = last_message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPatient_uid() {
        return patient_uid;
    }

    public void setPatient_uid(String patient_uid) {
        this.patient_uid = patient_uid;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_image() {
        return patient_image;
    }

    public void setPatient_image(String patient_image) {
        this.patient_image = patient_image;
    }

    public String getDoctor_uid() {
        return doctor_uid;
    }

    public void setDoctor_uid(String doctor_uid) {
        this.doctor_uid = doctor_uid;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_category() {
        return doctor_category;
    }

    public void setDoctor_category(String doctor_category) {
        this.doctor_category = doctor_category;
    }

    public String getDoctor_image() {
        return doctor_image;
    }

    public void setDoctor_image(String doctor_image) {
        this.doctor_image = doctor_image;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }
}
