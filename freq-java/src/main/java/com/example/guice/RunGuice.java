package com.example.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class RunGuice {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(
                new BasicModule(), new AOPModule());
        Communication comms = injector.getInstance(Communication.class);
    }
}
