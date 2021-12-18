package pl.put.poznan.sortingmadness.logic;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.put.poznan.sortingmadness.model.SortingMadnessRequest;
import pl.put.poznan.sortingmadness.model.SortingMadnessResponse;
import pl.put.poznan.sortingmadness.model.SortingMadnessSimpleResponse;
import pl.put.poznan.sortingmadness.model.SortingParameters;

import java.util.ArrayList;
import java.util.List;


@Service
public class SortingMadnessHelper {


    public static final String ASC = "ASC";

    /**
     * @param sortingMadnessRequest
     * @param dataList
     * @return
     */
    public List<String> getParametersToCompare(SortingMadnessRequest sortingMadnessRequest, List<JSONObject> dataList) {
        List<String> paramsToCompare = new ArrayList<>();

        paramsToCompare.add(sortingMadnessRequest.getKeyToSort());
        return paramsToCompare;
    }

    /**
     * @param chooseSorter
     * @param sortingAlg
     */
    private void fillSorter(ChooseSorter chooseSorter, String sortingAlg) {
        if (sortingAlg.equals("BubbleSorter")) {
            BubbleSorter bubbleSorter = new BubbleSorter();
            chooseSorter.setSorter(bubbleSorter);
        }
        if (sortingAlg.equals("CocktailSorter")) {
            CocktailSorter cocktailSorter = new CocktailSorter();
            chooseSorter.setSorter(cocktailSorter);
        }
        if (sortingAlg.equals("HeapSorter")) {
            HeapSorter heapSorter = new HeapSorter();
            chooseSorter.setSorter(heapSorter);
        }
        if (sortingAlg.equals("InsertionSorter")) {
            InsertionSorter insertionSorter = new InsertionSorter();
            chooseSorter.setSorter(insertionSorter);
        }
        if (sortingAlg.equals("MergeSorter")) {
            MergeSorter mergeSorter = new MergeSorter();
            chooseSorter.setSorter(mergeSorter);
        }
        if (sortingAlg.equals("QuickSorter")) {
            QuickSorter quickSorter = new QuickSorter();
            chooseSorter.setSorter(quickSorter);
        }

    }

    private Boolean getBooleanValueOfDirection(String directions) {
        return directions.equals(ASC) ? Boolean.TRUE : Boolean.FALSE;
    }


    private List<JSONObject> converter(JSONArray list) {
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (int i = 0; i < list.length(); i++) {
            jsonObjectList.add(list.getJSONObject(i));
        }
        return jsonObjectList;
    }

    private List<Comparable> converterSimple(JSONArray list) {
        List<Comparable> objectList = new ArrayList<>();
        for (int i = 0; i < list.length(); i++) {
            objectList.add((Comparable) list.get(i));
        }
        return objectList;
    }


    private SortingMadnessRequest getData(String request) {

        JSONObject jsonObject = new JSONObject(request);
        SortingMadnessRequest sortingMadnessRequest = new SortingMadnessRequest();

        sortingMadnessRequest.setKeyToSort(jsonObject.getString("keyToSort"));
        JSONArray list = (JSONArray) jsonObject.get("sortingParameters");
        List<SortingParameters> sortingParametersList = new ArrayList<>();
        for (int i = 0; i < list.length(); i++) {
            SortingParameters sortingParameters = new SortingParameters();
            sortingParameters.setSortingAlgorithms(list.getJSONObject(i).getString("sortingAlgorithms"));
            sortingParameters.setMaxIterations(list.getJSONObject(i).getInt("maxIterations"));
            sortingParameters.setDirections(list.getJSONObject(i).getString("directions"));
            sortingParametersList.add(sortingParameters);
        }
        sortingMadnessRequest.setData(converter(jsonObject.getJSONArray("data")));
        sortingMadnessRequest.setSortingParameters(sortingParametersList);
        return sortingMadnessRequest;
    }

    private SortingMadnessRequest getDataSimple(String request) {
        JSONObject jsonObject = new JSONObject(request);
        SortingMadnessRequest sortingMadnessRequest = new SortingMadnessRequest();
        JSONArray list = (JSONArray) jsonObject.get("sortingParameters");
        List<SortingParameters> sortingParametersList = new ArrayList<>();
        for (int i = 0; i < list.length(); i++) {
            SortingParameters sortingParameters = new SortingParameters();
            sortingParameters.setSortingAlgorithms(list.getJSONObject(i).getString("sortingAlgorithms"));
            sortingParameters.setMaxIterations(list.getJSONObject(i).getInt("maxIterations"));
            sortingParameters.setDirections(list.getJSONObject(i).getString("directions"));
            sortingParametersList.add(sortingParameters);
        }
        sortingMadnessRequest.setSimpleData(converterSimple(jsonObject.getJSONArray("data")));
        sortingMadnessRequest.setSortingParameters(sortingParametersList);
        return sortingMadnessRequest;
    }


    private String getResponseToString(List<SortingMadnessResponse> responseList) {
        new String();
        JSONObject jsonObjectResult = new JSONObject();
        List<JSONObject> tempList = new ArrayList<>();
        for (SortingMadnessResponse s : responseList) {
            JSONObject temp = new JSONObject();
            temp.put("sortedList", s.getSorted_list());
            temp.put("elapsedTime", s.getElapsed_time());
            temp.put("sortingAlgorithm", s.getSorting_algorithm());
            tempList.add(temp);
        }
        jsonObjectResult.put("result", tempList);
        String stringResponse = jsonObjectResult.toString();
        return stringResponse;
    }


    public String getResponseObject(String request) {
        SortingMadnessRequest sortingMadnessRequest = getData(request);
        List<SortingMadnessResponse> responseList = new ArrayList<>();
        ChooseSorter chooseSorter = new ChooseSorter();
        List<JSONObject> dataList = sortingMadnessRequest.getData();
        for (SortingParameters param : sortingMadnessRequest.getSortingParameters()) {
            fillSorter(chooseSorter, param.getSortingAlgorithms());
            try {
                Long timeOfSorting = chooseSorter.executeSort(dataList, param.getMaxIterations(), getBooleanValueOfDirection(param.getDirections()),
                        new JSONComparator(getParametersToCompare(sortingMadnessRequest, dataList)));
                List<JSONObject> tempList = new ArrayList<>(dataList);
                SortingMadnessResponse sortingMadnessResponse = new SortingMadnessResponse(timeOfSorting, param.getSortingAlgorithms(), param.getDirections(), param.getMaxIterations(), tempList);
                responseList.add(sortingMadnessResponse);
            } catch (ChooseSorter.EmptyInputException e) {
                e.printStackTrace();
            } catch (ChooseSorter.NoSorterProvided noSorterProvided) {
                noSorterProvided.printStackTrace();
            }
        }
        return getResponseToString(responseList);
    }


    private String getSimpleResponseToString(List<SortingMadnessSimpleResponse> responseList) {
        new String();
        JSONObject jsonObjectResult = new JSONObject();
        List<JSONObject> tempList = new ArrayList<>();
        for (SortingMadnessSimpleResponse s : responseList) {
            JSONObject temp = new JSONObject();
            temp.put("sortedList", s.getSortedList());
            temp.put("elapsedTime", s.getElapsedTime());
            temp.put("sortingAlgorithm", s.getSortingAlgorithm());
            tempList.add(temp);
        }
        jsonObjectResult.put("result", tempList);
        String stringResponse = jsonObjectResult.toString();
        return stringResponse;
    }


    public String getResponseSimple(String request) {
        SortingMadnessRequest sortingMadnessRequest = getDataSimple(request);
        List<SortingMadnessSimpleResponse> responseList = new ArrayList<>();
        ChooseSorter chooseSorter = new ChooseSorter();

        for (SortingParameters param : sortingMadnessRequest.getSortingParameters()) {
            List<Comparable> dataList = sortingMadnessRequest.getSimpleData();
            fillSorter(chooseSorter, param.getSortingAlgorithms());
            try {
                Long timeOfSorting = chooseSorter.executeSort(dataList, param.getMaxIterations(), getBooleanValueOfDirection(param.getDirections()));
                List<Comparable> tempList = new ArrayList<>(dataList);
                SortingMadnessSimpleResponse sortingMadnessSimpleResponse = new SortingMadnessSimpleResponse(timeOfSorting, param.getSortingAlgorithms(), tempList);
                responseList.add(sortingMadnessSimpleResponse);
            } catch (ChooseSorter.EmptyInputException e) {
                e.printStackTrace();
            } catch (ChooseSorter.NoSorterProvided noSorterProvided) {
                noSorterProvided.printStackTrace();
            }
        }
        return getSimpleResponseToString(responseList);
    }
}

