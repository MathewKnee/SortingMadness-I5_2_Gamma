package pl.put.poznan.sortingmadness.model;


/**
 * Sorting parameters wrapper.
 *
 * @author Aleksandra Kiel
 * @version 1.0
 */
public class SortingParameters {
    /**
     * Sorting algorithm to be used (available options: bubblesort, insertionsort, quicksort, cocktailsort, heapsort, mergesort).
     */
    private String sortingAlgorithm;
    /**
     * Iteration limit for the given algorithm(if lesser than or equal to 0 then iteration limit isn't taken into account).
     */
    private Integer maxIterations;
    /**
     * Sorting direction for the given algorithm(ASC - ascending, DESC - descending).
     */
    private String direction;

    public String getSortingAlgorithm() {
        return sortingAlgorithm;
    }

    public void setSortingAlgorithm(String sortingAlgorithm) {
        this.sortingAlgorithm = sortingAlgorithm;
    }

    public Integer getMaxIterations() {
        return maxIterations;
    }

    public void setMaxIterations(Integer maxIterations) {
        this.maxIterations = maxIterations;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
