package pl.put.poznan.sortingmadness.logic;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

/**
 * Concrete strategy of Sorter. Used to sort lists using cocktail sort algorithm.
 * @author Mateusz Kolano
 * @version 1.1
 */
public class CocktailSorter implements Sorter{
    /**
     * Sorts the given list using cocktail sort algorithm.
     * One iteration means one loop left to right and one loop right to left.
     *
     * @param unsorted_list list of Comparable objects to be sorted
     * @param max_it specifies maximum number of iterations for the cocktail sort (if max_it lesser than or equal to 0 then inf)
     * @param ascending specifies direction of sorting if true then ascending else false
     * @param <E> self Comparable object
     */
    public <E extends Comparable<E>> void sort(List<E> unsorted_list, int max_it, boolean ascending){
        boolean swapped = true;
        int direction_switch = ascending ? 1:-1;
        int start = 0;
        int end = unsorted_list.size();
        int it_count = 0;
        int it_max = max_it <= 0 ? end*end: max_it;

        while(swapped){
            if(it_count >= it_max) break;
            swapped = false;
            for(int i = start; i < end - 1; ++i){
                if(direction_switch * (unsorted_list.get(i) instanceof String ?
                        ((String)unsorted_list.get(i)).compareToIgnoreCase((String)unsorted_list.get(i+1)):
                        unsorted_list.get(i).compareTo(unsorted_list.get(i+1))) > 0){
                    Collections.swap(unsorted_list,i,i+1);
                    swapped = true;
                }
            }
            if(!swapped) break;
            end = end - 1;
            for (int i = end - 1; i >= start; i--){
                if(direction_switch * (unsorted_list.get(i) instanceof String ?
                                ((String)unsorted_list.get(i)).compareToIgnoreCase((String)unsorted_list.get(i+1)):
                                unsorted_list.get(i).compareTo(unsorted_list.get(i+1))) > 0){
                    Collections.swap(unsorted_list,i,i+1);
                }
            }
            start += 1;
            it_count += 1;
        }
    }
    /**
     * Sorts the given list using cocktail sort algorithm.
     * One iteration means one loop left to right and one loop right to left.
     *
     * @param unsorted_list list of JSONObjects to be sorted
     * @param max_it specifies maximum number of iterations for the cocktail sort (if max_it lesser than or equal to 0 then inf)
     * @param ascending specifies direction of sorting if true then ascending else false
     * @param comparator JSONComparator that compares those two objects based on given keys
     */
    public void sort(List<JSONObject> unsorted_list, int max_it, boolean ascending, JSONComparator comparator){
        boolean swapped = true;
        int direction_switch = ascending ? 1:-1;
        int start = 0;
        int end = unsorted_list.size();
        int it_count = 0;
        int it_max = max_it <= 0 ? end*end: max_it;

        while(swapped){
            if(it_count >= it_max) break;
            swapped = false;
            for(int i = start; i < end - 1; ++i){
                if(direction_switch * comparator.compare(unsorted_list.get(i),unsorted_list.get(i+1)) >0){
                    Collections.swap(unsorted_list,i,i+1);
                    swapped = true;
                }
            }
            if(!swapped) break;
            end = end - 1;
            for (int i = end - 1; i >= start; i--){
                if(direction_switch * comparator.compare(unsorted_list.get(i),unsorted_list.get(i+1)) >0){
                    Collections.swap(unsorted_list,i,i+1);

                }
            }
            start += 1;
            it_count += 1;
        }
    }
}
