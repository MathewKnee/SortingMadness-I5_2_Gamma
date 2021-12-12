package pl.put.poznan.transformer.logic;

import java.util.Collections;
import java.util.List;

public class CocktailSorter implements Sorter{

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
                if(direction_switch * unsorted_list.get(i).compareTo(unsorted_list.get(i+1)) >0){
                    Collections.swap(unsorted_list,i,i+1);
                    swapped = true;
                }
            }
            if(!swapped) break;
            end = end - 1;
            for (int i = end - 1; i >= start; i--){
                if(direction_switch * unsorted_list.get(i).compareTo(unsorted_list.get(i+1)) >0){
                    Collections.swap(unsorted_list,i,i+1);
                    swapped = true;
                }
            }
            start += 1;
            it_count += 1;
        }
    }
}
