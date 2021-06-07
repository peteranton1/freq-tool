package com.example.freq;

public interface DataProcessor<T extends Comparable<T>> {

    BTree<T> process(DataReader<T> dataReader,
                     DataConfig<T> dataConfig);
}
