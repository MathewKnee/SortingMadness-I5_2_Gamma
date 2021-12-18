package pl.put.poznan.sortingmadness.rest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sortingmadness.logic.*;
import pl.put.poznan.sortingmadness.model.SortingMadnessResponse;


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

    SortingMadnessHelper sortingMadnessHelper = new SortingMadnessHelper();
    @PostMapping("/sortingMadness")
    public String postObjectSorting(@RequestBody String sortingMadnessRequest){
        return sortingMadnessHelper.getResponseObject(sortingMadnessRequest);
    }

    @PostMapping("/sortingMadnessSimple")
    public String postSimpleSorting(@RequestBody String sortingMadnessRequest){

        return sortingMadnessHelper.getResponseSimple(sortingMadnessRequest);
    }
}
