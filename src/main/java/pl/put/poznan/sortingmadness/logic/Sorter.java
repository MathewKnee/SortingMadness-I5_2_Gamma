package pl.put.poznan.sortingmadness.logic;

import org.json.JSONObject;

import java.util.List;

public interface Sorter {
    <E extends Comparable<E>> void sort(List<E> unsorted_list, int max_it, boolean ascending);
    void sort(List<JSONObject> unsorted_list, int max_it, boolean ascending, JSONComparator comparator);
}
