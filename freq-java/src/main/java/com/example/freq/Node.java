package com.example.freq;

import lombok.Getter;

@Getter
public class Node<T> {
    private final T value;
    private int counter;
    Node<T> left;
    Node<T> right;

    public Node(T value) {
        this.value = value;
        this.counter = 0;
        right = null;
        left = null;
    }

    public void incrementCounter() {
        counter++;
    }
}
