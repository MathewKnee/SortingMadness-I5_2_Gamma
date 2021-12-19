package pl.put.poznan.sortingmadness.logic;

import org.json.JSONObject;

import java.util.List;
/**
 * Class used to sort lists using insertion sort algorithm.
 * Concrete strategy of {@link Sorter}.
 * @author Aleksandra Kiel
 * @version 1.0
 */
public class InsertionSorter implements Sorter {

    /**
     * Sorts the given list using insertion sort algorithm.
     * One iteration means one loop for j = i - 1 to 0 through the array.
     *
     * @param unsorted_list list of Comparable objects to be sorted
     * @param max_it specifies maximum number of iterations for the cocktail sort (if max_it lesser than or equal to 0 then inf)
     * @param ascending specifies direction of sorting if true then ascending else descending
     * @param <E> self Comparable object
     */
    @Override
    public <E extends Comparable<E>> void sort(List<E> unsorted_list, int max_it, boolean ascending) {
        int sizeOfList = unsorted_list.size();
        int direction_switch = ascending ? 1 : -1;
        int it_max = max_it <= 0 ? sizeOfList: max_it;
        for (int i = 1; i < sizeOfList; i++) {
            if (i < it_max) {
                E key = unsorted_list.get(i);
                int j = i - 1;
                if (key instanceof String) {
                    while (j >= 0 && (direction_switch * ((String) unsorted_list.get(j)).compareToIgnoreCase((String) key) > 0)) {
                        unsorted_list.set(j + 1, unsorted_list.get(j));
                        --j;
                    }
                } else {
                    while (j >= 0 && (direction_switch * unsorted_list.get(j).compareTo(key) > 0)) {
                        unsorted_list.set(j + 1, unsorted_list.get(j));
                        --j;
                    }
                }
                unsorted_list.set(j + 1, key);
            } else {
                break;
            }
        }
    }

    /**
     * Sorts the given list using insertion sort algorithm.
     * One iteration means one loop for j = i - 1 to 0 through the array.
     *
     * @param unsorted_list list of JSONObjects to be sorted
     * @param max_it specifies maximum number of iterations for the cocktail sort (if max_it lesser than or equal to 0 then inf)
     * @param ascending specifies direction of sorting if true then ascending else descending
     * @param comparator JSONComparator that compares those two objects based on given keys
     */
    public void sort(List<JSONObject> unsorted_list,
                     int max_it, boolean ascending,
                     JSONComparator comparator) {
        int sizeOfList = unsorted_list.size();
        int direction_switch = ascending ? 1 : -1;
        for (int i = 1; i < sizeOfList && i < max_it; i++) {
            JSONObject key = unsorted_list.get(i);
            int j = i - 1;
            while (j >= 0 && (direction_switch * comparator.compare(unsorted_list.get(j), key) > 0)) {
                unsorted_list.set(j + 1, unsorted_list.get(j));
                --j;
            }
            unsorted_list.set(j + 1, key);
        }
    }
}
