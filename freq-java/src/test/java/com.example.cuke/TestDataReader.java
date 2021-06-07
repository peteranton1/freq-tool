package com.example.cuke;

import com.example.freq.DataReader;
import com.example.freq.DataRecord;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class TestDataReader implements DataReader<String> {

    private final List<Map<String,String>> testData;
    private int currentPosition =0;

    public void resetPosition() {
        currentPosition = 0;
    }

    @Override
    public DataRecord<String> next() {
        String line = nextLineFromData(currentPosition++);
        return new TestDataRecord(line);
    }

    private String nextLineFromData(int pos) {
        if(pos > testData.size()) {
            throw new RuntimeException(String.format(
                    "TestData insufficient: %d/%d",
                    pos, testData.size()));
        }
        String line = testData.get(pos).get("line");
        if(line == null) {
            throw new RuntimeException(String.format(
                    "TestData line missing: %d/%d",
                    pos, testData.size()));
        }
        return line;
    }

    @Override
    public boolean hasNext() {
        return (currentPosition < testData.size());
    }
}
