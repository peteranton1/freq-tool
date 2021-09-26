package com.example.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/"
        },
        glue = {
                "com/example/cuke"
        },
        plugin = {
                "junit:build/cucumber-results.xml"
        },
        monochrome = true,
        tags = "not @Ignore"
)
public class TestRunner {
    private TestRunner() {
    }
}
