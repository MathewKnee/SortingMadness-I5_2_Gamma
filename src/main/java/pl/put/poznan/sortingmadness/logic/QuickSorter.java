package pl.put.poznan.sortingmadness.logic;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

/**
 * Class to sort list of elements using quick sort algorithm.
 * Concrete strategy of {@link Sorter}.
 * @author Agnieszka Klimek
 * @version 1.0
 */
public class QuickSorter implements Sorter{

    /**
     * Compares two objects
     *
     * @param o1 first object to compare
     * @param o2 second object to compare
     * @param <E> any comparable datatype except String
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
        } else{
            return 0;
        }
    }

    /**
     * Partitions given list around the pivot.
     *
     * @param unsorted_list List with elements to sort
     * @param begin starting index
     * @param end ending index
     * @param ascending sort direction (ascending if true, descending otherwise)
     * @param comparator JSON comparator (null if list contains elements other type)
     * @param <E> any comparable datatype or JSONObject
     * @return partitioning index (pivot index)
     */
    private <E> int partition(List<E> unsorted_list,
                              int begin, int end,
                              boolean ascending,
                              JSONComparator comparator){
        E pivot = unsorted_list.get(end);
        int partition_index = begin - 1;
        int cmp_result;

        for(int i = begin; i < end; i++){
            if(comparator != null){
                cmp_result = comparator.compare((JSONObject) unsorted_list.get(i), (JSONObject) pivot);
            }
            else if(pivot instanceof String){
                cmp_result = ((String)unsorted_list.get(i)).compareToIgnoreCase((String)pivot);
            } else{
                cmp_result = cmp(unsorted_list.get(i), pivot);
            }

            if((ascending && cmp_result < 0) || (!ascending && cmp_result > 0)){
                Collections.swap(unsorted_list, ++partition_index, i);
            }
        }
        Collections.swap(unsorted_list, ++partition_index, end);
        return partition_index;
    }

    /**
     * Sorts list given in argument.
     *
     * @param unsorted_list List with elements to sort
     * @param max_it maximal number of iterations
     * @param ascending sort direction (ascending if true, descending otherwise)
     * @param <E> any comparable datatype
     */
    @Override
    public <E extends Comparable<E>> void sort(List<E> unsorted_list, int max_it, boolean ascending) {
        if(unsorted_list.size() == 0) return;
        max_it = max_it <= 0 ? unsorted_list.size() : max_it;
        int[] stack_indexes = new int[unsorted_list.size() + unsorted_list.size() % 2];
        int top = -1, iteration_nr = 1;
        stack_indexes[++top] = 0;
        stack_indexes[++top] = unsorted_list.size() - 1;

        int left_index, right_index;
        while(top > -1 && iteration_nr <= max_it){
            right_index = stack_indexes[top--];
            left_index = stack_indexes[top--];

            int pivot_index = partition(unsorted_list, left_index, right_index, ascending, null);
            if(pivot_index > left_index){
                stack_indexes[++top] = left_index;
                stack_indexes[++top] = pivot_index - 1;
            }

            if(pivot_index < right_index){
                stack_indexes[++top] = pivot_index + 1;
                stack_indexes[++top] = right_index;
            }
            ++iteration_nr;
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
        if(unsorted_list.size() == 0) return;
        max_it = max_it <= 0 ? unsorted_list.size() : max_it;
        int[] stack_indexes = new int[unsorted_list.size() + unsorted_list.size() % 2];
        int top = -1, iteration_nr = 1;
        stack_indexes[++top] = 0;
        stack_indexes[++top] = unsorted_list.size() - 1;

        int left_index, right_index;
        while(top > -1 && iteration_nr <= max_it){
            right_index = stack_indexes[top--];
            left_index = stack_indexes[top--];

            int pivot_index = partition(unsorted_list, left_index, right_index, ascending, comparator);
            if(pivot_index > left_index){
                stack_indexes[++top] = left_index;
                stack_indexes[++top] = pivot_index - 1;
            }

            if(pivot_index < right_index){
                stack_indexes[++top] = pivot_index + 1;
                stack_indexes[++top] = right_index;
            }
            ++iteration_nr;
        }
    }
}
