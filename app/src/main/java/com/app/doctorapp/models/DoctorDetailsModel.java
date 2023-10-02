package com.app.doctorapp.models;

public class DoctorDetailsModel {

    double doctor_fee;
    int doctor_experience;
    int doctor_patients;
    String about_doctor;
    String doctor_location;

    public DoctorDetailsModel() {
    }

    public DoctorDetailsModel(double doctor_fee, int doctor_experience, int doctor_patients, String about_doctor, String doctor_location) {
        this.doctor_fee = doctor_fee;
        this.doctor_experience = doctor_experience;
        this.doctor_patients = doctor_patients;
        this.about_doctor = about_doctor;
        this.doctor_location = doctor_location;
    }

    public double getDoctor_fee() {
        return doctor_fee;
    }

    public void setDoctor_fee(double doctor_fee) {
        this.doctor_fee = doctor_fee;
    }

    public int getDoctor_experience() {
        return doctor_experience;
    }

    public void setDoctor_experience(int doctor_experience) {
        this.doctor_experience = doctor_experience;
    }

    public int getDoctor_patients() {
        return doctor_patients;
    }

    public void setDoctor_patients(int doctor_patients) {
        this.doctor_patients = doctor_patients;
    }

    public String getAbout_doctor() {
        return about_doctor;
    }

    public void setAbout_doctor(String about_doctor) {
        this.about_doctor = about_doctor;
    }

    public String getDoctor_location() {
        return doctor_location;
    }

    public void setDoctor_location(String doctor_location) {
        this.doctor_location = doctor_location;
    }
}
