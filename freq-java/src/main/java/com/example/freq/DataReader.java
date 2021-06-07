package com.example.freq;

public interface DataReader<T> {

    DataRecord<T> next();

    boolean hasNext();
}
