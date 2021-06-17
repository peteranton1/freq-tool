package com.example.freq;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class StringDataReader implements DataReader<String> {

    private final List<String> lines;
    private final DataType dataType;
    private int currentPosition = 0;

    @Override
    public DataRecord<String> next() {
        String line = nextLineFromData(currentPosition++);
        return new StringDataRecord(line, dataType);
    }

    private String nextLineFromData(int pos) {
        if (pos > lines.size()) {
            throw new RuntimeException(String.format(
                    "Data lines insufficient: %d/%d",
                    pos, lines.size()));
        }
        return lines.get(pos);
    }

    @Override
    public boolean hasNext() {
        return (currentPosition < lines.size());
    }
}
