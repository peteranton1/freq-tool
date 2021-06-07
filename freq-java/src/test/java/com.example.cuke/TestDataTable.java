package com.example.cuke;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@ToString
public class TestDataTable {
    private final List<Map<String, String>> testData;
}
