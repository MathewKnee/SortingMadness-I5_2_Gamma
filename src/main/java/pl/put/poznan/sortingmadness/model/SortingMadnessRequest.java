package pl.put.poznan.sortingmadness.model;


import org.json.JSONObject;

import java.util.List;

/**
 * Request wrapper for SortingMadness application.
 *
 * @author Aleksandra Kiel
 * @version 1.0
 */
public class SortingMadnessRequest {
    /**
     * Keys to sort by if the list is an object list.
     */
    private List<String> keysToSort;
    /**
     * Sorting parameters. For details see {@link SortingParameters}
     */
    private List<SortingParameters> sortingParameters;
    /**
     * List of JSONObjects to be sorted. In case of object sorting.
     */
    private List<JSONObject> data;
    /**
     * List of Comparable objects to be sorted. In case of simple sorting.
     */
    private List<Comparable> simpleData;

    public List<Comparable> getSimpleData() {
        return simpleData;
    }

    public void setSimpleData(List<Comparable> simpleData) {
        this.simpleData = simpleData;
    }

    public List<String> getKeysToSort() {
        return keysToSort;
    }

    public void setKeysToSort(List<String> keyToSort) {
        this.keysToSort = keyToSort;
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
