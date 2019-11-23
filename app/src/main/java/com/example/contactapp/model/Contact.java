package com.example.contactapp.model;

public class Contact {

    private int id;
    private String contactName;
    private String phoneNumber;

    public Contact(){}

    public Contact(String contactName, String phoneNumber){

        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }

    public Contact(int id, String contactName, String phoneNumber) {
        this.id = id;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
