package pl.put.poznan.sortingmadness.logic;

import java.util.List;
import org.json.JSONObject;

/**
 * Context class for {@link Sorter} strategy.
 * Sets appropriate strategy and executes sorting.
 * @author Wiktor Koropczuk
 * @version 1.0
 */
public class ChooseSorter {
    /**
     * Exception thrown when list to sort is empty.
     */
    public static class EmptyInputException extends Exception{
        public EmptyInputException(){
            super("Input list is empty.");
        }
    }

    /**
     * Exception thrown when sorting is executed without setting sorter.
     */
    public static class NoSorterProvided extends Exception{
        public NoSorterProvided(){
            super("Did not provide sorter.");
        }
    }

    /**
     * Sorter used to sort the list.
     */
    private Sorter sorter;
    public void setSorter(Sorter sorter){
        this.sorter = sorter;
    }

    /**
     * Method executing sorting for Comparable objects and measuring elapsed time in nanoseconds.
     * @param unsorted_list list to be sorted.
     * @param max_it maximum number of iterations.
     * @param ascending specifies direction of sorting if true then ascending else descending.
     * @param <E> self Comparable object.
     * @return elapsed time in nanoseconds.
     * @throws EmptyInputException thrown when unsorted_list is empty.
     * @throws NoSorterProvided thrown when Sorter wasn't set before executing this method.
     */
    public <E extends Comparable<E>> long executeSort(List<E> unsorted_list, int max_it, boolean ascending) throws EmptyInputException, NoSorterProvided {
        if (unsorted_list.size() == 0)
            throw new EmptyInputException();
        if (sorter == null)
            throw new NoSorterProvided();
        long start = System.nanoTime();
        sorter.sort(unsorted_list, max_it, ascending);
        return System.nanoTime() - start;
    }

    /**
     * Method executing sorting for Comparable objects and measuring elapsed time in nanoseconds.
     * @param unsorted_list list to be sorted.
     * @param max_it maximum number of iterations.
     * @param ascending specifies direction of sorting if true then ascending else descending.
     * @param comparator comparator used for comparing of JSONObjects.
     * @return elapsed time in nanoseconds.
     * @throws EmptyInputException thrown when unsorted_list is empty.
     * @throws NoSorterProvided thrown when Sorter wasn't set before executing this method.
     */
    public long executeSort(List<JSONObject> unsorted_list, int max_it, boolean ascending, JSONComparator comparator) throws EmptyInputException, NoSorterProvided {
        if (unsorted_list.size() == 0)
            throw new EmptyInputException();
        if (sorter == null)
            throw new NoSorterProvided();
        long start = System.nanoTime();
        sorter.sort(unsorted_list, max_it, ascending, comparator);
        return System.nanoTime() - start;
    }
}
