package com.ofoegbuvgmail.accidentreport.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "alert")
public class AlertEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String typeOfAlert;
    private String descriptionOfAccident;
    private String location;
    private String imageUrl;
    private String timeOfAlert;
    private String dateOfAlert;

    public AlertEntry() {
    }

    @Ignore
    public AlertEntry(int id, String typeOfAlert, String descriptionOfAccident, String location, String imageUrl, String timeOfAlert, String dateOfAlert) {
        this.id = id;
        this.typeOfAlert = typeOfAlert;
        this.descriptionOfAccident = descriptionOfAccident;
        this.location = location;
        this.imageUrl = imageUrl;
        this.timeOfAlert = timeOfAlert;
        this.dateOfAlert = dateOfAlert;
    }

    public AlertEntry(String typeOfAlert, String descriptionOfAccident, String location, String imageUrl, String timeOfAlert, String dateOfAlert) {
        this.typeOfAlert = typeOfAlert;
        this.descriptionOfAccident = descriptionOfAccident;
        this.location = location;
        this.imageUrl = imageUrl;
        this.timeOfAlert = timeOfAlert;
        this.dateOfAlert = dateOfAlert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfAlert() {
        return typeOfAlert;
    }

    public void setTypeOfAlert(String typeOfAlert) {
        this.typeOfAlert = typeOfAlert;
    }

    public String getDescriptionOfAccident() {
        return descriptionOfAccident;
    }

    public void setDescriptionOfAccident(String descriptionOfAccident) {
        this.descriptionOfAccident = descriptionOfAccident;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTimeOfAlert() {
        return timeOfAlert;
    }

    public void setTimeOfAlert(String timeOfAlert) {
        this.timeOfAlert = timeOfAlert;
    }

    public String getDateOfAlert() {
        return dateOfAlert;
    }

    public void setDateOfAlert(String dateOfAlert) {
        this.dateOfAlert = dateOfAlert;
    }
}
