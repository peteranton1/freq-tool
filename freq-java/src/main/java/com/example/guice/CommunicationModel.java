package com.example.guice;

public enum CommunicationModel {

    EMAIL("Email"), SMS("SMS"), IM("IM"), PHONE("Phone");

    final String name;

    CommunicationModel(String name) {
        this.name = name;
    }
}