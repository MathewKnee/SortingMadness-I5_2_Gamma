package pl.put.poznan.sortingmadness.logic;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

/**
 * Concrete strategy of Sorter. Used to sort lists using heap sort algorithm.
 * @author Wiktor Koropczuk
 * @version 1.0
 */
public class HeapSorter implements Sorter{
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
     * Constructs heap from provided list.
     *
     * @param list input and output data to construct heap
     * @param size index of list element which will be last heap's leaf
     * @param startNode index of list element which will be heap's root
     * @param ascending if true, method will construct max heap, otherwise it will construct min heap
     * @param comparator JSON comparator
     * @param <E> any comparable datatype or JSONObject
     */
    private <E> void BuildHeap(List<E> list, int size, int startNode, boolean ascending, JSONComparator comparator){
        int node = startNode;
        for (;;)
        {
            int ind = node;
            int l = 2 * node + 1;
            int r = l + 1;
            if (l < size && compare(list.get(l), list.get(ind), ascending, comparator))
                ind = l;
            if (r < size && compare(list.get(r), list.get(ind), ascending, comparator))
                ind = r;
            if (ind == node)
                break;
            Collections.swap(list, node, ind);
            node = ind;
        }

    }

    /**
     * Sorts the given list using heap sort algorithm.
     * One iteration means one process of getting the minimal/maximal element and restoring heap.
     *
     * @param unsorted_list list of JSONObjects to be sorted
     * @param max_it specifies maximum number of iterations for the heap sort (if max_it lesser than or equal to 0 then inf)
     * @param ascending specifies direction of sorting and type of constructed heap if true then ascending and max heap else false and min heap
     * @param comparator JSONComparator that compares those two objects based on given keys
     */
    public void sort(List<JSONObject> unsorted_list, int max_it, boolean ascending, JSONComparator comparator){
        int max_it_copy;
        if (max_it <= 0)
            max_it_copy = unsorted_list.size();
        else
            max_it_copy = max_it;
        for (int i = unsorted_list.size() / 2 - 1; i >= 0; i--)
            BuildHeap(unsorted_list, unsorted_list.size(), i, ascending, comparator);
        for (int i = unsorted_list.size() - 1; i >= 0 && unsorted_list.size() - 1 - i < max_it_copy; i--){
            Collections.swap(unsorted_list, 0, i);
            BuildHeap(unsorted_list, i, 0, ascending, comparator);
        }
    }

    /**
     * Sorts the given list using heap sort algorithm.
     * One iteration means one process of getting the minimal/maximal element and restoring heap.
     * 
     * @param unsorted_list list of Comparable objects to be sorted
     * @param max_it specifies maximum number of iterations for the heap sort (if max_it lesser than or equal to 0 then inf)
     * @param ascending specifies direction of sorting and type of constructed heap if true then ascending and max heap else false and min heap
     * @param <E> self Comparable object
     */
    @Override
    public <E extends Comparable<E>> void sort(List<E> unsorted_list, int max_it, boolean ascending){
        int max_it_copy;
        if (max_it <= 0)
            max_it_copy = unsorted_list.size();
        else
            max_it_copy = max_it;
        for (int i = unsorted_list.size() / 2 - 1; i >= 0; i--)
            BuildHeap(unsorted_list, unsorted_list.size(), i, ascending, null);
        for (int i = unsorted_list.size() - 1; i >= 0 && unsorted_list.size() - 1 - i < max_it_copy; i--){
            Collections.swap(unsorted_list, 0, i);
            BuildHeap(unsorted_list, i, 0, ascending, null);
        }
    }
}
