package pl.put.poznan.sortingmadness.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class SortingMadnessRequest {

    private String keyToSort;

    private List<SortingParameters> sortingParameters;

    private List<JSONObject> data;

    private List<Comparable> simpleData;

    public List<Comparable> getSimpleData() {
        return simpleData;
    }

    public void setSimpleData(List<Comparable> simpleData) {
        this.simpleData = simpleData;
    }

    public String getKeyToSort() {
        return keyToSort;
    }

    public void setKeyToSort(String keyToSort) {
        this.keyToSort = keyToSort;
    }

    public List<SortingParameters> getSortingParameters() {
        return sortingParameters;
    }

    public void setSortingParameters(List<SortingParameters> sortingParameters) {
        this.sortingParameters = sortingParameters;
    }

    public List<JSONObject> getData() {
        return data;
    }

    public void setData(List<JSONObject> data) {
        this.data = data;
    }
}
