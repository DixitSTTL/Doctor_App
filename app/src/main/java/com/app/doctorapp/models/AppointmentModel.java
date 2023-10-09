package com.app.doctorapp.models;

import java.util.Date;

public class AppointmentModel {

    Date created;
    String doctor_name;
    String doctor_uid;
    String patient_name;
    String patient_uid;
    String cTime;
    DateModel cDate;

//    public AppointmentModel() {
//    }

    public AppointmentModel(Date created, String doctor_name, String doctor_uid, String patient_name, String patient_uid, String cTime, DateModel cDate) {
        this.created = created;
        this.doctor_name = doctor_name;
        this.doctor_uid = doctor_uid;
        this.patient_name = patient_name;
        this.patient_uid = patient_uid;
        this.cTime = cTime;
        this.cDate = cDate;
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

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public DateModel getcDate() {
        return cDate;
    }

    public void setcDate(DateModel cDate) {
        this.cDate = cDate;
    }
}
