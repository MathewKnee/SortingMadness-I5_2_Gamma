package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class MergeSorter implements Sorter{
    private <E extends Comparable<E>> boolean compare(E el1, E el2, boolean ascending){
        int res = el1 instanceof String ? ((String)el1).compareToIgnoreCase((String)el2) : el1.compareTo(el2);
        return (res > 0 && ascending) || (res < 0 && !ascending);
    }

    private <E extends Comparable<E>> void merge(List<E> unsorted_list, int left, int half, int right, boolean ascending) {
        int mid = half;
        ArrayList<E> partition = new ArrayList<>();
        while(left < half && mid < right) {
            if(!compare(unsorted_list.get(left), unsorted_list.get(mid), ascending)) {
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

    @Override
    public <E extends Comparable<E>> void sort(List<E> unsorted_list, int max_it, boolean ascending) {
        int left, half, right;
        for(int size=1; size < unsorted_list.size(); size *= 2) {
            for(int bound = 0; bound < unsorted_list.size(); bound += 2 * size) {
                left = bound;
                half = min(bound + size, unsorted_list.size());
                right = min(bound + 2 * size, unsorted_list.size());

                merge(unsorted_list, left, half, right, ascending);
            }
            if(--max_it == 0)
                return;
        }
    }
}
