package com.example.freq;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Counter<T>{

    private final T value;
    private final int counter;
}
