package com.example.cuke;

public class StepDefsUtils {
    public static int toInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new StepDefsException(e);
        }
    }
}
