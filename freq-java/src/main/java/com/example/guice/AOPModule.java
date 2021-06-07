package com.example.guice;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class AOPModule extends AbstractModule {

    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(MessageSentLoggable.class),
                new MessageLogger()
        );
    }

}