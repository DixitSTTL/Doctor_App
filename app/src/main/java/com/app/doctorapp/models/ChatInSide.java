package com.app.doctorapp.models;

import java.util.Date;

public class ChatInSide {


    Date time;
    String sender_uid;

    String message;


    public ChatInSide() {
    }

    public ChatInSide(Date time, String sender_uid, String message) {
        this.time = time;
        this.sender_uid = sender_uid;
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSender_uid() {
        return sender_uid;
    }

    public void setSender_uid(String sender_uid) {
        this.sender_uid = sender_uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
