package com.app.doctorapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DoctorDetailsModel implements Parcelable {

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

    protected DoctorDetailsModel(Parcel in) {
        doctor_fee = in.readDouble();
        doctor_experience = in.readInt();
        doctor_patients = in.readInt();
        about_doctor = in.readString();
        doctor_location = in.readString();
    }

    public static final Creator<DoctorDetailsModel> CREATOR = new Creator<DoctorDetailsModel>() {
        @Override
        public DoctorDetailsModel createFromParcel(Parcel in) {
            return new DoctorDetailsModel(in);
        }

        @Override
        public DoctorDetailsModel[] newArray(int size) {
            return new DoctorDetailsModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeDouble(doctor_fee);
        dest.writeInt(doctor_experience);
        dest.writeInt(doctor_patients);
        dest.writeString(about_doctor);
        dest.writeString(doctor_location);
    }
}
