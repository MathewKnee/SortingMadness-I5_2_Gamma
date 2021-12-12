package pl.put.poznan.transformer.logic;

import java.util.Collections;
import java.util.List;

public class HeapSorter implements Sorter{
    private <E extends Comparable<E>> boolean compare(E el1, E el2, boolean ascending){
        int res = el1 instanceof String ? ((String)el1).compareToIgnoreCase((String)el2) : el1.compareTo(el2);
        return (res > 0 && ascending) || (res < 0 && !ascending);
    }

    private <E extends Comparable<E>> void BuildHeap(List<E> list, int size, int startNode, boolean ascending){
        int node = startNode;
        for (;;)
        {
            int ind = node;
            int l = 2 * node + 1;
            int r = l + 1;
            if (l < size && compare(list.get(l), list.get(ind), ascending))
                ind = l;
            if (r < size && compare(list.get(r), list.get(ind), ascending))
                ind = r;
            if (ind == node)
                break;
            Collections.swap(list, node, ind);
            node = ind;
        }

    }

    public <E extends Comparable<E>> void sort(List<E> unsorted_list, int max_it, boolean ascending){
        int max_it_copy;
        if (max_it <= 0)
            max_it_copy = unsorted_list.size();
        else
            max_it_copy = max_it;
        for (int i = unsorted_list.size() / 2 - 1; i >= 0; i--)
            BuildHeap(unsorted_list, unsorted_list.size(), i, ascending);
        for (int i = unsorted_list.size() - 1; i >= 0 && unsorted_list.size() - 1 - i < max_it_copy; i--){
            Collections.swap(unsorted_list, 0, i);
            BuildHeap(unsorted_list, i, 0, ascending);
        }
    }
}
