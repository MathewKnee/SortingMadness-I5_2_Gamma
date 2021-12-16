package pl.put.poznan.sortingmadness.rest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sortingmadness.logic.CocktailSorter;
import pl.put.poznan.sortingmadness.logic.JSONComparator;
import pl.put.poznan.sortingmadness.logic.Sorter;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SortingMadnessController {


    @GetMapping("/test")
    public List<String> testObjectListResponse(@RequestBody String payload, @RequestParam String sorting_param) {
        JSONObject request = new JSONObject(payload);
        JSONArray list = (JSONArray) request.get("objects");
        List<JSONObject> jsonObject_list = new ArrayList<>();
        for(int i = 0;i < list.length();i++){
            jsonObject_list.add(list.getJSONObject(i));
        }
        String param = sorting_param;
        List<String> response = new ArrayList<>();
        CocktailSorter sorter = new CocktailSorter();
        sorter.sort(jsonObject_list,0,true,new JSONComparator(param));
        for(int i = 0; i< list.length();i++){
            JSONObject o = jsonObject_list.get(i);
            response.add(o.toString());
        }
        return response;
    }
}
