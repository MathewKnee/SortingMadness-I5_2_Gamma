package pl.put.poznan.sortingmadness.logic;

import java.util.Collections;
import java.util.List;

public class QuickSorter implements Sorter{

    private <E extends Comparable<E>> int partition(List<E> unsorted_list, int begin, int end, boolean ascending){
        E pivot = unsorted_list.get(end);
        int partition_index = begin - 1;
        int cmp_result;

        for(int i = begin; i < end; i++){
            if(pivot instanceof String){
                cmp_result = ((String)unsorted_list.get(i)).compareToIgnoreCase((String)pivot);
            } else{
                cmp_result = unsorted_list.get(i).compareTo(pivot);
            }

            if((ascending && cmp_result < 0) || (!ascending && cmp_result > 0)){
                Collections.swap(unsorted_list, ++partition_index, i);
            }
        }
        Collections.swap(unsorted_list, ++partition_index, end);
        return partition_index;
    }

    @Override
    public <E extends Comparable<E>> void sort(List<E> unsorted_list, int max_it, boolean ascending) {
        int[] stack_indexes = new int[unsorted_list.size() + unsorted_list.size() % 2];
        int top = -1, iteration_nr = 1;
        stack_indexes[++top] = 0;
        stack_indexes[++top] = unsorted_list.size() - 1;

        int left_index, right_index;
        while(top > -1 && iteration_nr <= max_it){
            right_index = stack_indexes[top--];
            left_index = stack_indexes[top--];

            int pivot_index = partition(unsorted_list, left_index, right_index, ascending);
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
