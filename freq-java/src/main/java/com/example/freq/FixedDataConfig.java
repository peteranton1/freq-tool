package com.example.freq;

import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class FixedDataConfig implements DataConfig<String> {

    private final List<List<Integer>> cols = new ArrayList<>();

    public void addCols(int startCol, int finishCol) {
        cols.add(ImmutableList.of(startCol, finishCol));
    }
}
