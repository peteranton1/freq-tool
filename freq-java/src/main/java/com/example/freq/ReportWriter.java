package com.example.freq;

public interface ReportWriter<T> {
    String writeReport(Counter<T> results);
}
