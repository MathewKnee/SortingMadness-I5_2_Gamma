package pl.put.poznan.sortingmadness.logic;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;


/**
 * Class to sort list of elements using merge sort algorithm.
 * Concrete strategy of {@link Sorter}.
 * @author Szymon Kowalski
 * @version 1.0
 */
public class MergeSorter implements Sorter{

    /**
     * Compares two objects that are not instance of JSON object.
     *
     * @param <E> any comparable datatype
     * @param o1 first object to compare
     * @param o2 second object to compare
     * @return result of compareTo method
     */
    private <E> int cmp(E o1, E o2){
        if(o1 instanceof Integer){
            return ((Integer)o1).compareTo((Integer)o2);
        }else if(o1 instanceof Long){
            return ((Long)o1).compareTo((Long)o2);
        }else if(o1 instanceof Float){
            return ((Float)o1).compareTo((Float)o2);
        }else if(o1 instanceof Double){
            return ((Double)o1).compareTo((Double)o2);
        }else if(o1 instanceof String){
            return ((String)o1).compareToIgnoreCase((String)o2);
        }else{
            return 0;
        }
    }

    /**
     * Compares two objects depends on if it is or not instance of JSON object.
     *
     * @param <E> any comparable datatype
     * @param o1 first object to compare
     * @param o2 second object to compare
     * @param ascending sort direction (ascending if true, descending otherwise)
     * @param comparator JSON comparator
     * @return result of compareToIgnoreCase method and sort direction
     */
    private <E> boolean compare(E o1, E o2, boolean ascending, JSONComparator comparator){
        int res;
        if(comparator != null)
            res = comparator.compare((JSONObject)o1, (JSONObject)o2);
        else
            res = cmp(o1, o2);
        return (res > 0 && ascending) || (res < 0 && !ascending);
    }

    /**
     * Merges two sublists.
     *
     * @param <E> any comparable datatype
     * @param unsorted_list List with JSON objects to sort
     * @param left index of the beginning of the left sublist
     * @param half infex of the end of the left sublist and beginning of the right sublist
     * @param right index of the end of the right sublist
     * @param ascending sort direction (ascending if true, descending otherwise)
     * @param comparator JSON comparator
     */
    private <E> void merge(List<E> unsorted_list,
                       int left,
                       int half,
                       int right,
                       boolean ascending,
                       JSONComparator comparator) {
        int mid = half;
        ArrayList<E> partition = new ArrayList<>();
        while(left < half && mid < right) {
            if(!compare(unsorted_list.get(left), unsorted_list.get(mid), ascending, comparator)) {
                partition.add(unsorted_list.get(left++));
            }
            else {
                partition.add(unsorted_list.get(mid++));
            }
        }

        while(left < half) {
            partition.add(unsorted_list.get(left++));
        }
        while(mid < right) {
            partition.add(unsorted_list.get(mid++));
        }

        while(partition.size() > 0) {
            right--;
            unsorted_list.set(right, partition.get(partition.size() - 1));
            partition.remove(partition.size() - 1);
        }
    }

    /**
     * Sorts list given in argument.
     *
     * @param unsorted_list List with JSON objects to sort
     * @param max_it maximal number of iterations
     * @param ascending sort direction (ascending if true, descending otherwise)
     * @param comparator JSON comparator
     */
    public void sort(List<JSONObject> unsorted_list, int max_it, boolean ascending, JSONComparator comparator) {
        int left, half, right;
        for(int size=1; size < unsorted_list.size(); size *= 2) {
            for(int bound = 0; bound < unsorted_list.size(); bound += 2 * size) {
                left = bound;
                half = min(bound + size, unsorted_list.size());
                right = min(bound + 2 * size, unsorted_list.size());

                merge(unsorted_list, left, half, right, ascending, comparator);
            }
            if(--max_it == 0)
                return;
        }
    }

    /**
     * Sorts list given in argument.
     *
     * @param <E> any comparable datatype
     * @param unsorted_list List with JSON objects to sort
     * @param max_it maximal number of iterations
     * @param ascending sort direction (ascending if true, descending otherwise)
     */
    @Override
    public <E extends Comparable<E>> void sort(List<E> unsorted_list, int max_it, boolean ascending) {
        int left, half, right;
        for(int size=1; size < unsorted_list.size(); size *= 2) {
            for(int bound = 0; bound < unsorted_list.size(); bound += 2 * size) {
                left = bound;
                half = min(bound + size, unsorted_list.size());
                right = min(bound + 2 * size, unsorted_list.size());

                merge(unsorted_list, left, half, right, ascending, null);

                if(--max_it == 0)
                    return;
            }
        }
    }
}
