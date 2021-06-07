package com.example.cuke;

import com.example.freq.DataRecord;
import com.example.freq.DataType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TestDataRecord implements DataRecord<String> {
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
