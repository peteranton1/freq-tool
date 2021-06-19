package com.example.freq;

import java.util.List;

public interface ReportWriter<T> {
    String writeReport(List<Counter<T>> results,
                       ReportConfig reportConfig);
}
