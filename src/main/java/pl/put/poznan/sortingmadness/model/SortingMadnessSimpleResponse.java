package pl.put.poznan.sortingmadness.model;

import org.json.JSONObject;

import java.util.List;
/**
 * Wrapper for REST response for simple sorting.
 *
 * @author Aleksandra Kiel
 * @version 1.0
 */
public class SortingMadnessSimpleResponse {
    /**
     * Elapsed time in nanoseconds for given algorithm.
     */
    private long elapsedTime;
    /**
     * Used sorting algorithm.
     */
    private String sortingAlgorithm;
    /**
     * Sorted list of Comparable objects to be sent.
     */
    private List<Comparable> sortedList;

    public SortingMadnessSimpleResponse(long elapsedTime, String sortingAlgorithm, List<Comparable> sortedList) {
        this.elapsedTime = elapsedTime;
        this.sortingAlgorithm = sortingAlgorithm;
        this.sortedList = sortedList;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getSortingAlgorithm() {
        return sortingAlgorithm;
    }

    public void setSortingAlgorithm(String sortingAlgorithm) {
        this.sortingAlgorithm = sortingAlgorithm;
    }

    public List<Comparable> getSortedList() {
        return sortedList;
    }

    public void setSortedList(List<Comparable> sortedList) {
        this.sortedList = sortedList;
    }
}
