package com.example.guice;

public interface CommunicationMode {

    public CommunicationModel getMode();

    public boolean sendMessage(String message);

}