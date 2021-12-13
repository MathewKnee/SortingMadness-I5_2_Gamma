package pl.put.poznan.transformer.logic;

import java.util.List;

public class InsertionSorter implements Sorter {


    @Override
    public <E extends Comparable<E>> void sort(List<E> unsorted_list, int max_it, boolean ascending) {
        int sizeOfList = unsorted_list.size();
        int direction_switch = ascending ? 1 : -1;
        for (int i = 1; i <= sizeOfList; i++) {
            if (i < max_it) {
                E key = unsorted_list.get(i);
                int j = i - 1;
                while (j >= 0 && (direction_switch * unsorted_list.get(j).compareTo(key) > 0)) {
                    unsorted_list.set(j + 1, unsorted_list.get(j));
                    --j;
                }
                unsorted_list.set(j + 1, key);
            } else {
                break;
            }

        }
    }
}
