package pl.put.poznan.sortingmadness.logic;

import org.json.JSONObject;

import java.util.List;

/**
 * Interface used for sorting lists of elements.
 * Sorter strategy.
 * @author Mateusz Kolano
 * @version 1.1
 */
public interface Sorter {
    /**
     * Sorting 1D list of Comparable objects.
     * @param unsorted_list unsorted_list list of Comparable objects to be sorted.
     * @param max_it specifies maximum number of iterations for the cocktail sort (if max_it lesser than or equal to 0 then inf).
     * @param ascending specifies direction of sorting if true then ascending else false.
     * @param <E> self Comparable object.
     */
    <E extends Comparable<E>> void sort(List<E> unsorted_list, int max_it, boolean ascending);

    /**
     * Sorting 1D list of JSONObjects.
     * @param unsorted_list unsorted_list list of JSONObjects to be sorted.
     * @param max_it specifies maximum number of iterations for the cocktail sort (if max_it lesser than or equal to 0 then inf).
     * @param ascending specifies direction of sorting if true then ascending else false.
     * @param comparator comparator used for comparing of JSONObjects.
     */
    void sort(List<JSONObject> unsorted_list, int max_it, boolean ascending, JSONComparator comparator);
}
