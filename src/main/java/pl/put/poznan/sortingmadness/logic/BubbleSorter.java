package pl.put.poznan.sortingmadness.logic;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class BubbleSorter implements Sorter {


    @Override
    public <E extends Comparable<E>> void sort(List<E> unsorted_list, int max_it, boolean ascending) {
        int sizeOfList = unsorted_list.size();
        int direction_switch = ascending ? 1 : -1;
        for (int i = 0; i < sizeOfList; i++) {
            if (i < max_it) {
                for (int j = 1; j < (sizeOfList - i); j++) {
                    if (unsorted_list.get(j - 1) instanceof String) {
                        if (direction_switch * ((String) unsorted_list.get(j - 1)).compareToIgnoreCase((String) unsorted_list.get(j)) > 0) {
                            Collections.swap(unsorted_list, j - 1, j);

                        }
                    } else {
                        if (direction_switch * unsorted_list.get(j - 1).compareTo(unsorted_list.get(j)) > 0) {
                                Collections.swap(unsorted_list, j - 1, j);
                        }
                    }
                }
            } else {
                break;
            }

        }
    }
    public  void sort(List<JSONObject> unsorted_list, int max_it, boolean ascending, JSONComparator comparator) {
        int sizeOfList = unsorted_list.size();
        int direction_switch = ascending ? 1 : -1;
        for (int i = 0; i < sizeOfList; i++) {
            if (i < max_it) {
                for (int j = 1; j < (sizeOfList - i); j++) {
                    if (direction_switch * comparator.compare(unsorted_list.get(j - 1),unsorted_list.get(j)) > 0) {
                        Collections.swap(unsorted_list, j - 1, j);
                    }
                }
            } else {
                break;
            }

        }
    }
}
