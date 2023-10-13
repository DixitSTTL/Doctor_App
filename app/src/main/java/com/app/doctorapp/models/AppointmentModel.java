package com.app.doctorapp.models;

import java.util.Date;
import java.util.List;

public class AppointmentModel {

    Date created;
    String doctor_name;
    String doctor_uid;
    String doctor_image;
    String patient_name;
    String patient_uid;
    String patient_image;
    String c_time;

    List<PrescripeModel> prescribed;


    DateModel cDate;

    public AppointmentModel() {
    }



    public AppointmentModel(Date created, String doctor_name, String doctor_uid, String doctor_image, String patient_name, String patient_uid, String patient_image, String c_time, DateModel cDate) {
        this.created = created;
        this.doctor_name = doctor_name;
        this.doctor_uid = doctor_uid;
        this.doctor_image = doctor_image;
        this.patient_name = patient_name;
        this.patient_uid = patient_uid;
        this.patient_image = patient_image;
        this.c_time = c_time;
        this.cDate = cDate;
    }

    public AppointmentModel(Date created, String doctor_name, String doctor_uid, String doctor_image, String patient_name, String patient_uid, String patient_image, String c_time, List<PrescripeModel> prescribed, DateModel cDate) {
        this.created = created;
        this.doctor_name = doctor_name;
        this.doctor_uid = doctor_uid;
        this.doctor_image = doctor_image;
        this.patient_name = patient_name;
        this.patient_uid = patient_uid;
        this.patient_image = patient_image;
        this.c_time = c_time;
        this.prescribed = prescribed;
        this.cDate = cDate;
    }

    public List<PrescripeModel> getPrescribed() {
        return prescribed;
    }

    public void setPrescribed(List<PrescripeModel> prescribed) {
        this.prescribed = prescribed;
    }

    public String getCtime() {
        return c_time;
    }

    public void setCtime(String ctime) {
        this.c_time = ctime;
    }

    public String getDoctor_image() {
        return doctor_image;
    }

    public void setDoctor_image(String doctor_image) {
        this.doctor_image = doctor_image;
    }

    public String getPatient_image() {
        return patient_image;
    }

    public void setPatient_image(String patient_image) {
        this.patient_image = patient_image;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_uid() {
        return doctor_uid;
    }

    public void setDoctor_uid(String doctor_uid) {
        this.doctor_uid = doctor_uid;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_uid() {
        return patient_uid;
    }

    public void setPatient_uid(String patient_uid) {
        this.patient_uid = patient_uid;
    }


    public DateModel getcDate() {
        return cDate;
    }

    public void setcDate(DateModel cDate) {
        this.cDate = cDate;
    }

}
