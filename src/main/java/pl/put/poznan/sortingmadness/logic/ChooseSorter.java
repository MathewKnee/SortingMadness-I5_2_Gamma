package pl.put.poznan.sortingmadness.logic;

import java.util.List;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * Logger to log chosen algorithm.
     */
    private static final Logger logger = LoggerFactory.getLogger(ChooseSorter.class);
    /**
     * Sorter used to sort the list.
     */
    private Sorter sorter;
    public void setSorter(Sorter sorter){
        this.sorter = sorter;
    }

    /**
     * Method automatically setting Sorter based on how much the data is sorted for SimpleSorting.
     * @param unsorted_list list to be sorted.
     * @param ascending specifies direction of sorting if true then ascending else descending.
     * @param <E> self Comparable object.
     */
    public <E extends Comparable<E>> void setAuto(List<E> unsorted_list, boolean ascending){
        int number_of_misplacements = 0;
        int direction_switch = ascending ? 1 : -1;
        for(int i = 1; i< unsorted_list.size(); i++){
            if(unsorted_list.get(i) instanceof String){
                if(direction_switch * ((String) unsorted_list.get(i)).compareToIgnoreCase(
                        (String)unsorted_list.get(i-1)) < 0) number_of_misplacements++;
            }else{
                if(direction_switch * unsorted_list.get(i).compareTo(
                        unsorted_list.get(i-1)) < 0) number_of_misplacements++;
            }
        }

        double sorted_coeff = (double)number_of_misplacements/(double)(unsorted_list.size()-1);
        logger.debug("Num of misplacements: "+number_of_misplacements+" Sorted coeff: "+sorted_coeff);
        if(sorted_coeff <= 0.2){
            logger.info("Insertion sort was automatically chosen");
            setSorter(new InsertionSorter());
        }else if(sorted_coeff <=0.8){
            logger.info("Quick sort was automatically chosen");
            setSorter(new QuickSorter());
        }else{
            logger.info("Heap sort was automatically chosen");
            setSorter(new HeapSorter());
        }

    }
    /**
     * Method automatically setting Sorter based on how much the data is sorted for ObjectSorting.
     * @param unsorted_list list to be sorted.
     * @param ascending specifies direction of sorting if true then ascending else descending.
     * @param comparator comparator used for comparing of JSONObjects.
     */
    public void setAuto(List<JSONObject> unsorted_list, JSONComparator comparator, boolean ascending){
        int number_of_misplacements = 0;
        int direction_switch = ascending ? 1 : -1;
        for(int i = 1; i< unsorted_list.size(); i++){

            if(direction_switch * comparator.compare(unsorted_list.get(i),
                    unsorted_list.get(i-1)) < 0) number_of_misplacements++;

        }
        double sorted_coeff = (double)number_of_misplacements/(double)(unsorted_list.size()-1);
        logger.debug("Num of misplacements: "+number_of_misplacements+" Sorted coeff: "+sorted_coeff);
        if(sorted_coeff <= 0.2){
            logger.info("Insertion sort was automatically chosen");
            setSorter(new InsertionSorter());
        }else if(sorted_coeff <=0.8){
            logger.info("Quick sort was automatically chosen");
            setSorter(new QuickSorter());
        }else{
            logger.info("Heap sort was automatically chosen");
            setSorter(new HeapSorter());
        }
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
