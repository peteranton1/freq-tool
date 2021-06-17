package com.example.freq;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StringDataRecord implements DataRecord<String> {
    private final String dataValue;
    private final DataType dataType;

    @Override
    public DataType type() {
        return dataType;
    }

    @Override
    public String value() {
        return dataValue;
    }
}
