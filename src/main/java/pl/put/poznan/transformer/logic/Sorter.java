package pl.put.poznan.transformer.logic;

import java.util.List;

public interface Sorter {
    <E extends Comparable<E>> void sort(List<E> unsorted_list, int max_it, boolean ascending);
}