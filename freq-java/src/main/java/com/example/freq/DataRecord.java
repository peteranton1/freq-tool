package com.example.freq;

public interface DataRecord<T> {
    DataType type();
    T value();
}
