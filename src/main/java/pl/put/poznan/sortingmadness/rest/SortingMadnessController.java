package pl.put.poznan.sortingmadness.rest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sortingmadness.logic.CocktailSorter;
import pl.put.poznan.sortingmadness.logic.JSONComparator;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * REST Controller
 *
 * @author -
 * @version 0.1
 */
@RestController
public class SortingMadnessController {


    @PostMapping("/test")
    public ResponseEntity<List<SortingMadnessResponse>> testObjectListResponse(
            @RequestBody String payload,
            @RequestParam(defaultValue = "a") List<String> sorting_param,
            @RequestParam List<String> sorting_algorithms) {
        JSONObject request = new JSONObject(payload);
        JSONArray list = (JSONArray) request.get("objects");
        List<JSONObject> jsonObject_list = new ArrayList<>();
        for(int i = 0;i < list.length();i++){
            jsonObject_list.add(list.getJSONObject(i));
        }


        List<SortingMadnessResponse> response = new ArrayList<>();
        CocktailSorter sorter = new CocktailSorter();

        sorter.sort(jsonObject_list,0,true,new JSONComparator(sorting_param));
        List<Map<String,Object>> ret = new ArrayList<>();
        for(int i = 0; i < jsonObject_list.size(); i++){
            ret.add(jsonObject_list.get(i).toMap());
        }
        SortingMadnessResponse res = new SortingMadnessResponse(1,"cocktailsorter",
                "ascending", 0, Collections.singletonList(ret));
        response.add(res);
        return new ResponseEntity<List<SortingMadnessResponse>>(response, HttpStatus.OK);
    }

    /**
     * Simple Sorting - Handles REST GET request, delegates the sorting to the sorting logic and then composes a response.
     *
     *
     * @param sorting_algorithms - list of sorting algorithms to be used
     * @param max_iterations - list of maximum number of iterations used by each algorithm (size must equal be equal to
     *                       the size of sorting_algorithms)
     * @param directions - list of sorting direction modifiers for each algorithm (accepts either "asc" or "desc"
     *                              size must equal be equal to the size of sorting_algorithms)
     * @param list - list of Comparable objects to be sorted by specified algorithms
     * @return list of SortingMadnessResponse containing elapsed time by each algorithm and sorted list
     */
    @GetMapping("/sortingmadness")
    public ResponseEntity<List<SortingMadnessResponse>> getSimpleSorting(
            @RequestParam(defaultValue = "quicksort") List<String> sorting_algorithms,
            @RequestParam(defaultValue = "0" ) List<Integer> max_iterations,
            @RequestParam(defaultValue = "asc") List<String> directions,
            @RequestBody String list){
        List<SortingMadnessResponse> responses = new ArrayList<>();

        return new ResponseEntity<List<SortingMadnessResponse>>(responses, HttpStatus.OK);
    }
    /**
     * Simple Sorting - Handles REST POST request, 
     * see {@link SortingMadnessController#getSimpleSorting(List, List, List, String)}
     */
    @PostMapping("/sortingmadness")
    public ResponseEntity<List<SortingMadnessResponse>> postSimpleSorting(
            @RequestParam(defaultValue = "quicksort") List<String> sorting_algorithms,
            @RequestParam(defaultValue = "0" ) List<Integer> max_iterations,
            @RequestParam(defaultValue = "asc") List<String> directions,
            @RequestBody String list){
        List<SortingMadnessResponse> responses = new ArrayList<>();

        return new ResponseEntity<List<SortingMadnessResponse>>(responses, HttpStatus.OK);
    }
    /**
     * Simple Sorting - Handles REST GET request, delegates the sorting to the sorting logic and then composes a response.
     *
     *
     * @param sorting_algorithms - list of sorting algorithms to be used
     * @param max_iterations - list of maximum number of iterations used by each algorithm (size must equal be equal to
     *                       the size of sorting_algorithms)
     * @param directions - list of sorting direction modifiers for each algorithm (accepts either "asc" or "desc"
     *                              size must equal be equal to the size of sorting_algorithms)
     * @param sorting_keys - list of keys by which sorting happens
     * @param list - list of JSONObjects to be sorted by specified algorithms
     * @return list of SortingMadnessResponse containing elapsed time by each algorithm and sorted list
     */
    @GetMapping("/sortingmadness/objects")
    public ResponseEntity<List<SortingMadnessResponse>> getObjectSorting(
            @RequestParam(defaultValue = "quicksort") List<String> sorting_algorithms,
            @RequestParam(defaultValue = "0" ) List<Integer> max_iterations,
            @RequestParam(defaultValue = "asc") List<String> directions,
            @RequestParam List<String> sorting_keys,
            @RequestBody String list){
        List<SortingMadnessResponse> responses = new ArrayList<>();

        return new ResponseEntity<List<SortingMadnessResponse>>(responses, HttpStatus.OK);
    }
    /**
     * Object Sorting - Handles REST POST request, 
     * see {@link SortingMadnessController#getObjectSorting(List, List, List, List, String)}
     */
    @PostMapping("/sortingmadness/objects")
    public ResponseEntity<List<SortingMadnessResponse>> postObjectSorting(
            @RequestParam(defaultValue = "quicksort") List<String> sorting_algorithms,
            @RequestParam(defaultValue = "0" ) List<Integer> max_iterations,
            @RequestParam(defaultValue = "asc") List<String> directions,
            @RequestParam List<String> sorting_keys,
            @RequestBody String list){
        List<SortingMadnessResponse> responses = new ArrayList<>();

        return new ResponseEntity<List<SortingMadnessResponse>>(responses, HttpStatus.OK);
    }
}
