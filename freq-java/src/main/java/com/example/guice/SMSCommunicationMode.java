package com.example.guice;

import com.google.inject.Inject;
import java.util.logging.Logger;

public class SMSCommunicationMode implements CommunicationMode {

    @Inject
    private Logger logger;

    @Override
    public CommunicationModel getMode() {
        return CommunicationModel.SMS;
    }

    @Override
    @MessageSentLoggable
    public boolean sendMessage(String message) {
        logger.info("SMS message sent");
        return true;
    }

}