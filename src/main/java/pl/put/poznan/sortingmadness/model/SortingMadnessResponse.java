package pl.put.poznan.sortingmadness.model;

import org.json.JSONObject;

import java.util.List;

/**
 * Wrapper for REST response for object sorting.
 *
 * @author Aleksandra Kiel
 * @version 1.0
 */
public class SortingMadnessResponse {
    /**
     * Elapsed time in nanoseconds for given algorithm.
     */
    private long elapsed_time;
    /**
     * Used sorting algorithm.
     */
    private String sorting_algorithm;
    /**
     * Sorted list of JSONObjects to be sent.
     */
    private List<JSONObject> sorted_list;

    public void setElapsed_time(long elapsed_time) {
        this.elapsed_time = elapsed_time;
    }

    public void setSorting_algorithm(String sorting_algorithm) {
        this.sorting_algorithm = sorting_algorithm;
    }


    public void setSorted_list(List<JSONObject> sorted_list) {
        this.sorted_list = sorted_list;
    }

    public long getElapsed_time() {
        return elapsed_time;
    }

    public String getSorting_algorithm() {
        return sorting_algorithm;
    }

    public List<JSONObject> getSorted_list() {
        return sorted_list;
    }

    public SortingMadnessResponse(long elapsed_time, String sorting_algorithm, String direction, Integer max_it, List<JSONObject> sorted_list) {
        this.elapsed_time = elapsed_time;
        this.sorting_algorithm = sorting_algorithm;
        this.sorted_list = sorted_list;
    }
}
