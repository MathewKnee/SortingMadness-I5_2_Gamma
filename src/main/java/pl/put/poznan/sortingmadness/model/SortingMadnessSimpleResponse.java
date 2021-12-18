package pl.put.poznan.sortingmadness.model;

import org.json.JSONObject;

import java.util.List;

public class SortingMadnessSimpleResponse {
    private long elapsedTime;
    private String sortingAlgorithm;
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
