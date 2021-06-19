package com.example.freq;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ReportConfig {
    private final String title ;
    private final List<String> fields ;
}
