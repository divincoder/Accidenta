package com.ofoegbuvgmail.accidentreport.model;

public class User {

    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String PHONENO = "phoneNo";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String OCCUPATION = "occupation";

    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private String gender;
    private String occupation;

    public User() {
    }

    public User(String firstName, String lastName, String phoneNo, String email, String gender, String occupation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.gender = gender;
        this.occupation = occupation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
