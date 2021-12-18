package pl.put.poznan.sortingmadness.model;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class SortingParameters {
        private String sortingAlgorithms;
        private Integer maxIterations;
        private String directions;

    public String getSortingAlgorithms() {
        return sortingAlgorithms;
    }

    public void setSortingAlgorithms(String sortingAlgorithms) {
        this.sortingAlgorithms = sortingAlgorithms;
    }

    public Integer getMaxIterations() {
        return maxIterations;
    }

    public void setMaxIterations(Integer maxIterations) {
        this.maxIterations = maxIterations;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }
}
