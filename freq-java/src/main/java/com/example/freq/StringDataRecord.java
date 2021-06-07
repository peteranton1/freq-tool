package com.example.freq;

public class StringDataRecord implements DataRecord<String> {
    @Override
    public DataType type() {
        return DataType.FIXED;
    }

    @Override
    public String value() {
        return null;
    }
}
