package com.example.dave.davecollgeproexample;

import android.location.Location;

/**
 * Created by Dave on 3/12/2015.
 */
public class Client {
    String name;
    String phoneNumber;
    String emailAddress;
    String notes;
    String address;
    String latitude;
    String longitude;

    public Client(String name, String address, String email, String notes, String phone) {
        setAddress(address);
        setName(name);
        setEmailAddress(email);
        setNotes(notes);
        setPhoneNumber(phone);
    }

    public void setLocation(Location mLastLocation) {
        setLatitude(String.valueOf(mLastLocation.getLatitude()));
        setLongitude(String.valueOf(mLastLocation.getLongitude()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
