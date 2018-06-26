package com.ofoegbuvgmail.accidentreport.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "accident")
public class AccidentEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String route;
    private String causeOfAccident;
    private String NarrativeDescriptionOfAccident;
    private String timeOfAccident;
    private String dateOfAccident;
    private String imageUrl;
    private String natureOfInjury;
    private String vehiclePlateNo;
    private String vehicleModel;
    private String damageToVehicle;
    private String reportersName;
    private String phoneNumber;

    public AccidentEntry() {
    }

    @Ignore
    public AccidentEntry(int id, String route, String causeOfAccident, String narrativeDescriptionOfAccident, String timeOfAccident, String dateOfAccident, String imageUrl, String natureOfInjury, String vehiclePlateNo, String vehicleModel, String damageToVehicle, String reportersName, String phoneNumber) {
        this.id = id;
        this.route = route;
        this.causeOfAccident = causeOfAccident;
        NarrativeDescriptionOfAccident = narrativeDescriptionOfAccident;
        this.timeOfAccident = timeOfAccident;
        this.dateOfAccident = dateOfAccident;
        this.imageUrl = imageUrl;
        this.natureOfInjury = natureOfInjury;
        this.vehiclePlateNo = vehiclePlateNo;
        this.vehicleModel = vehicleModel;
        this.damageToVehicle = damageToVehicle;
        this.reportersName = reportersName;
        this.phoneNumber = phoneNumber;
    }

    public AccidentEntry(String route, String causeOfAccident, String narrativeDescriptionOfAccident, String timeOfAccident, String dateOfAccident, String imageUrl, String natureOfInjury, String vehiclePlateNo, String vehicleModel, String damageToVehicle, String reportersName, String phoneNumber) {
        this.route = route;
        this.causeOfAccident = causeOfAccident;
        NarrativeDescriptionOfAccident = narrativeDescriptionOfAccident;
        this.timeOfAccident = timeOfAccident;
        this.dateOfAccident = dateOfAccident;
        this.imageUrl = imageUrl;
        this.natureOfInjury = natureOfInjury;
        this.vehiclePlateNo = vehiclePlateNo;
        this.vehicleModel = vehicleModel;
        this.damageToVehicle = damageToVehicle;
        this.reportersName = reportersName;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getCauseOfAccident() {
        return causeOfAccident;
    }

    public void setCauseOfAccident(String causeOfAccident) {
        this.causeOfAccident = causeOfAccident;
    }

    public String getNarrativeDescriptionOfAccident() {
        return NarrativeDescriptionOfAccident;
    }

    public void setNarrativeDescriptionOfAccident(String narrativeDescriptionOfAccident) {
        NarrativeDescriptionOfAccident = narrativeDescriptionOfAccident;
    }

    public String getTimeOfAccident() {
        return timeOfAccident;
    }

    public void setTimeOfAccident(String timeOfAccident) {
        this.timeOfAccident = timeOfAccident;
    }

    public String getDateOfAccident() {
        return dateOfAccident;
    }

    public void setDateOfAccident(String dateOfAccident) {
        this.dateOfAccident = dateOfAccident;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNatureOfInjury() {
        return natureOfInjury;
    }

    public void setNatureOfInjury(String natureOfInjury) {
        this.natureOfInjury = natureOfInjury;
    }

    public String getVehiclePlateNo() {
        return vehiclePlateNo;
    }

    public void setVehiclePlateNo(String vehiclePlateNo) {
        this.vehiclePlateNo = vehiclePlateNo;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getDamageToVehicle() {
        return damageToVehicle;
    }

    public void setDamageToVehicle(String damageToVehicle) {
        this.damageToVehicle = damageToVehicle;
    }

    public String getReportersName() {
        return reportersName;
    }

    public void setReportersName(String reportersName) {
        this.reportersName = reportersName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
