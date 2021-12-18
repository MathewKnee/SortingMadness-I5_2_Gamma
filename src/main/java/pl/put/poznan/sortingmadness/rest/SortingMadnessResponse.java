package pl.put.poznan.sortingmadness.rest;

import java.util.List;

/**
 * Wrapper for REST response.
 *
 * @author Matuesz Kolano
 * @version 1.0
 */
public class SortingMadnessResponse {
    /**
     * Describes how much time did the algorithm take to complete.
     */
    private double elapsed_time;
    /**
     * Sorting algorithm that was used.
     */
    private String sorting_algorithm;
    /**
     * Sorting direction for the given algorithm
     */
    private String direction;
    /**
     * Maximum number of iterations (specified by the client) used by the algorithm.
     */
    private Integer max_it;
    /**
     * Sorted or partially sorted list
     */
    private List<Object> sorted_list;

    public void setElapsed_time(double elapsed_time) {
        this.elapsed_time = elapsed_time;
    }

    public void setSorting_algorithm(String sorting_algorithm) {
        this.sorting_algorithm = sorting_algorithm;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setMax_it(Integer max_it) {
        this.max_it = max_it;
    }

    public void setSorted_list(List<Object> sorted_list) {
        this.sorted_list = sorted_list;
    }

    public double getElapsed_time() {
        return elapsed_time;
    }

    public String getSorting_algorithm() {
        return sorting_algorithm;
    }

    public String getDirection() {
        return direction;
    }

    public Integer getMax_it() {
        return max_it;
    }

    public List<Object> getSorted_list() {
        return sorted_list;
    }

    public SortingMadnessResponse(double elapsed_time, String sorting_algorithm, String direction, Integer max_it, List<Object> sorted_list) {
        this.elapsed_time = elapsed_time;
        this.sorting_algorithm = sorting_algorithm;
        this.direction = direction;
        this.max_it = max_it;
        this.sorted_list = sorted_list;
    }
}
