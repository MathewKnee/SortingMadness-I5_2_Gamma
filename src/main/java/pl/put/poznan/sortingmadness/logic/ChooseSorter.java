package pl.put.poznan.sortingmadness.logic;

import java.util.List;
import org.json.JSONObject;

/**
 * TODO
 *
 * @author Wiktor Koropczuk
 * @version 1.0
 */
public class ChooseSorter {
    /**
     * TODO
     */
    public static class EmptyInputException extends Exception{
        public EmptyInputException(){
            super("Input list is empty.");
        }
    }

    /**
     * TODO
     */
    public static class NoSorterProvided extends Exception{
        public NoSorterProvided(){
            super("Did not provide sorter.");
        }
    }

    /**
     * TODO
     */
    private Sorter sorter;
    public void setSorter(Sorter sorter){
        this.sorter = sorter;
    }

    /**
     * TODO
     * @param unsorted_list
     * @param max_it
     * @param ascending
     * @param <E>
     * @return
     * @throws EmptyInputException
     * @throws NoSorterProvided
     */
    public <E extends Comparable<E>> long executeSort(List<E> unsorted_list, int max_it, boolean ascending) throws EmptyInputException, NoSorterProvided {
        if (unsorted_list.size() == 0)
            throw new EmptyInputException();
        if (sorter == null)
            throw new NoSorterProvided();
        long start = System.currentTimeMillis();
        sorter.sort(unsorted_list, max_it, ascending);
        return System.currentTimeMillis() - start;
    }

    /**
     * TODO
     * @param unsorted_list
     * @param max_it
     * @param ascending
     * @param comparator
     * @return
     * @throws EmptyInputException
     * @throws NoSorterProvided
     */
    public long executeSort(List<JSONObject> unsorted_list, int max_it, boolean ascending, JSONComparator comparator) throws EmptyInputException, NoSorterProvided {
        if (unsorted_list.size() == 0)
            throw new EmptyInputException();
        if (sorter == null)
            throw new NoSorterProvided();
        long start = System.currentTimeMillis();
        sorter.sort(unsorted_list, max_it, ascending, comparator);
        return System.currentTimeMillis() - start;
    }
}
