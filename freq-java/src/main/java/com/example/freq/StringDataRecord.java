package com.example.freq;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StringDataRecord implements DataRecord<String> {
    private final String dataValue;

    @Override
    public DataType type() {
        return DataType.FIXED;
    }

    @Override
    public String value() {
        return dataValue;
    }
}
