package com.example.patry.androidproject.Data;

public class MailItem {
    private String address;
    private String subject;
    private String message;

    public MailItem(String address, String subject, String message) {
        this.address = address;
        this.subject = subject;
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
