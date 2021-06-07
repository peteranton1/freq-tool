package com.example.freq;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BTree<T extends Comparable<T>> {

    private Node<T> root;

    public void add(T value) {
        if(Objects.isNull(root)){
            root = new Node<T>(value);
        }
        root = addRecursive(root, value);
    }

    private Node<T> addRecursive(Node<T> current,
                                 T value) {
        if (current == null) {
            Node<T> node = new Node<>(value);
            node.incrementCounter();
            return node;
        }

        int compare = value.compareTo(current.getValue());
        if (compare > 0) {
            current.left = addRecursive(current.left, value);
        } else if (compare < 0) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            current.incrementCounter();
            return current;
        }

        return current;
    }

    public List<Counter<T>> resultsInOrder() {
        List<Counter<T>> values = new ArrayList<>();
        traverseInOrder(root,values);
        values.sort((c1, c2) -> c2.getCounter() - c1.getCounter());
        return values;
    }

    private void traverseInOrder(Node<T> node,
                                List<Counter<T>> values) {
        if (node != null) {
            traverseInOrder(node.left, values);
            values.add(new Counter<>(node.getValue(), node.getCounter()));
            traverseInOrder(node.right, values);
        }
    }
}
