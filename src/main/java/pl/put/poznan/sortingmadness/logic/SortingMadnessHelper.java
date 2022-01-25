package pl.put.poznan.sortingmadness.logic;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.put.poznan.sortingmadness.model.SortingMadnessRequest;
import pl.put.poznan.sortingmadness.model.SortingMadnessResponse;
import pl.put.poznan.sortingmadness.model.SortingMadnessSimpleResponse;
import pl.put.poznan.sortingmadness.model.SortingParameters;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Logic class that parses JSON input, delegates sorting and then composes JSON output.
 *
 * @author Aleksandra Kiel
 * @version 1.0
 */
@Service
public class SortingMadnessHelper {
    /**
     * Logger to log every major step of processing.
     */
    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessHelper.class);
    /**
     * Static string for ascending direction of sorting. Used for comparing in {@link SortingMadnessHelper#getBooleanValueOfDirection(String)}.
     */
    public static final String ASC = "ASC";
    /**
     * Static string for descending direction of sorting. Used for comparing in {@link SortingMadnessHelper#getBooleanValueOfDirection(String)}.
     */
    public static final String DESC = "DESC";



    /**
     * Chooses sorter for SimpleSorting based on input string value.
     * @param chooseSorter ChooseSorter object for which the sorting algorithm will be set.
     * @param sortingAlg name of the algorithm.
     * @param data data to sort(used for automatic sorting).
     * @param ascending direction of sort.
     * @param <E> self Comparable object.
     * @throws InvalidSorterException thrown when chosen sorting algorithm does not exist.
     */
    private <E extends Comparable<E>> void fillSorter(ChooseSorter chooseSorter, String sortingAlg, List<E> data, boolean ascending) throws InvalidSorterException{
        if (sortingAlg.equals("bubblesort")) {
            logger.debug("Chosen algorithm: bubble sort");
            BubbleSorter bubbleSorter = new BubbleSorter();
            chooseSorter.setSorter(bubbleSorter);
        }
        else if (sortingAlg.equals("cocktailsort")) {
            logger.debug("Chosen algorithm: cocktail sort");
            CocktailSorter cocktailSorter = new CocktailSorter();
            chooseSorter.setSorter(cocktailSorter);
        }
        else if (sortingAlg.equals("heapsort")) {
            logger.debug("Chosen algorithm: heap sort");
            HeapSorter heapSorter = new HeapSorter();
            chooseSorter.setSorter(heapSorter);
        }
        else if (sortingAlg.equals("insertionsort")) {
            logger.debug("Chosen algorithm: insertion sort");
            InsertionSorter insertionSorter = new InsertionSorter();
            chooseSorter.setSorter(insertionSorter);
        }
        else if (sortingAlg.equals("mergesort")) {
            logger.debug("Chosen algorithm: merge sort");
            MergeSorter mergeSorter = new MergeSorter();
            chooseSorter.setSorter(mergeSorter);
        }
        else if (sortingAlg.equals("quicksort")) {
            logger.debug("Chosen algorithm: quick sort");
            QuickSorter quickSorter = new QuickSorter();
            chooseSorter.setSorter(quickSorter);
        }else if (sortingAlg.equals("auto")){
            logger.debug("Chosen algorithm: auto");
            chooseSorter.setAuto(data,ascending);
        }
        else{
            logger.debug("No matching algorithm");
            throw new InvalidSorterException("There is no sorting algorithm with name: "+sortingAlg);
        }
    }
    /**
     * Chooses sorter for ObjectSorting based on input string value.
     * @param chooseSorter ChooseSorter object for which the sorting algorithm will be set.
     * @param sortingAlg name of the algorithm.
     * @param data data to sort(used for automatic sorting).
     * @param ascending direction of sort.
     * @param comparator comparator used to compare JSONObjects
     * @throws InvalidSorterException thrown when chosen sorting algorithm does not exist.
     */
    private void fillSorter(ChooseSorter chooseSorter, String sortingAlg, List<JSONObject> data, boolean ascending, JSONComparator comparator) throws InvalidSorterException{
        if (sortingAlg.equals("bubblesort")) {
            logger.debug("Chosen algorithm: bubble sort");
            BubbleSorter bubbleSorter = new BubbleSorter();
            chooseSorter.setSorter(bubbleSorter);
        }
        else if (sortingAlg.equals("cocktailsort")) {
            logger.debug("Chosen algorithm: cocktail sort");
            CocktailSorter cocktailSorter = new CocktailSorter();
            chooseSorter.setSorter(cocktailSorter);
        }
        else if (sortingAlg.equals("heapsort")) {
            logger.debug("Chosen algorithm: heap sort");
            HeapSorter heapSorter = new HeapSorter();
            chooseSorter.setSorter(heapSorter);
        }
        else if (sortingAlg.equals("insertionsort")) {
            logger.debug("Chosen algorithm: insertion sort");
            InsertionSorter insertionSorter = new InsertionSorter();
            chooseSorter.setSorter(insertionSorter);
        }
        else if (sortingAlg.equals("mergesort")) {
            logger.debug("Chosen algorithm: merge sort");
            MergeSorter mergeSorter = new MergeSorter();
            chooseSorter.setSorter(mergeSorter);
        }
        else if (sortingAlg.equals("quicksort")) {
            logger.debug("Chosen algorithm: quick sort");
            QuickSorter quickSorter = new QuickSorter();
            chooseSorter.setSorter(quickSorter);
        }else if (sortingAlg.equals("auto")){
            logger.debug("Chosen algorithm: auto");
            chooseSorter.setAuto(data,comparator,ascending);
        }
        else{
            logger.debug("No matching algorithm");
            throw new InvalidSorterException("There is no sorting algorithm with name: "+sortingAlg);
        }
    }

    /**
     * Translates String representation of direction into Boolean.
     * ASC = true
     * DESC = false
     * If input string doesn't match the two given options it defaults to ASC.
     * @param directions String representation of direction.
     * @return Boolean representation of direction.
     */
    private Boolean getBooleanValueOfDirection(String directions) {
        return directions.equals(ASC) ? Boolean.TRUE : directions.equals(DESC) ? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     * Translates JSONArray into List of JSONObjects,
     * @param list JSONArray to be parsed.
     * @return Parsed list of JSONObjects.
     */
    private List<JSONObject> converter(JSONArray list) {
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (int i = list.length() - 1; i >= 0; i--) {
            jsonObjectList.add(list.getJSONObject(i));
        }
        return jsonObjectList;
    }

    /**
     * Translates JSONArray into List of Comparable objects,
     * @param list JSONArray to be parsed.
     * @return Parsed list of Comparable objects.
     */
    private List<Comparable> converterSimple(JSONArray list) {
        List<Comparable> objectList = new ArrayList<>();
        for (int i = 0; i < list.length(); i++) {
            objectList.add((Comparable) list.get(i));
        }
        return objectList;
    }

    /**
     * Parses input String to {@link SortingMadnessRequest}. Used in case of Object sorting.
     * @param request input to be parsed.
     * @return parsed input.
     */
    private SortingMadnessRequest getData(String request) {
        logger.debug("Parsing request");
        JSONObject jsonObject = new JSONObject(request);
        SortingMadnessRequest sortingMadnessRequest = new SortingMadnessRequest();
        List<String> keys = new ArrayList<>();
        JSONArray jsonKeys = jsonObject.getJSONArray("keysToSort");
        for(Object jsonKey: jsonKeys){
            keys.add((String)jsonKey);
        }
        sortingMadnessRequest.setKeysToSort(keys);
        List<SortingParameters> sortingParametersList = parseSortingParameters(jsonObject);
        sortingMadnessRequest.setData(converter(jsonObject.getJSONArray("data")));
        sortingMadnessRequest.setSortingParameters(sortingParametersList);
        return sortingMadnessRequest;
    }

    /**
     * Parses input String to {@link SortingMadnessRequest}. Used in case of Simple sorting.
     * @param request input to be parsed.
     * @return parsed input.
     */
    private SortingMadnessRequest getDataSimple(String request) {
        logger.debug("Parsing request");
        JSONObject jsonObject = new JSONObject(request);
        SortingMadnessRequest sortingMadnessRequest = new SortingMadnessRequest();
        List<SortingParameters> sortingParametersList = parseSortingParameters(jsonObject);
        sortingMadnessRequest.setSimpleData(converterSimple(jsonObject.getJSONArray("data")));
        sortingMadnessRequest.setSortingParameters(sortingParametersList);
        return sortingMadnessRequest;
    }

    /**
     * Parses input JSONObject to {@link SortingParameters}.
     * @param jsonObject input to be parsed.
     * @return parsed input.
     */
    private List<SortingParameters> parseSortingParameters(JSONObject jsonObject) {
        JSONArray list = (JSONArray) jsonObject.get("sortingParameters");
        List<SortingParameters> sortingParametersList = new ArrayList<>();
        for (int i = 0; i < list.length(); i++) {
            SortingParameters sortingParameters = new SortingParameters();
            sortingParameters.setSortingAlgorithm(list.getJSONObject(i).getString("sortingAlgorithms"));
            sortingParameters.setMaxIterations(list.getJSONObject(i).getInt("maxIterations"));
            sortingParameters.setDirection(list.getJSONObject(i).getString("directions"));
            sortingParametersList.add(sortingParameters);
        }
        return sortingParametersList;
    }

    /**
     * Logs sorting parameters.
     * @param param {@link SortingParameters} to be loged.
     */
    private void logParameters(SortingParameters param){
        ArrayList<String> response =  new ArrayList<>();
        response.add("Sorting algorithm: "+param.getSortingAlgorithm());
        response.add("Max iterations: " +param.getMaxIterations().toString());
        response.add("Direction: "+param.getDirection());
        logger.debug(Arrays.toString(response.toArray()));
    }

    /**
     * Parses List of {@link SortingMadnessResponse} to String.
     * @param responseList response list to be parsed.
     * @return parsed response list.
     */
    private String getResponseToString(List<SortingMadnessResponse> responseList) {
        logger.info("Parsing response");
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
        return jsonObjectResult.toString();
    }

    /**
     * Main processing function for Object sorting
     * @param request REST request to be processed.
     * @return parsed response.
     * @throws ChooseSorter.NoSorterProvided see {@link ChooseSorter.NoSorterProvided}
     * @throws ChooseSorter.EmptyInputException see {@link ChooseSorter.EmptyInputException}
     * @throws InvalidKeysException see {@link InvalidKeysException}
     * @throws InvalidSorterException see {@link InvalidSorterException}
     * @throws NoSortingAlgorithmProvidedException see {@link NoSortingAlgorithmProvidedException}
     */
    public String getResponseObject(String request) throws ChooseSorter.NoSorterProvided, ChooseSorter.EmptyInputException,
            InvalidSorterException, NoSortingAlgorithmProvidedException{
        SortingMadnessRequest sortingMadnessRequest = getData(request);
        if (sortingMadnessRequest.getKeysToSort() == null) throw new InvalidKeysException("No key list provided");
        if (sortingMadnessRequest.getKeysToSort().isEmpty()) throw new InvalidKeysException("Key list is empty");
        List<SortingMadnessResponse> responseList = new ArrayList<>();
        ChooseSorter chooseSorter = new ChooseSorter();
        logger.info("Beginning sorting");
        if(sortingMadnessRequest.getSortingParameters() == null) throw new NoSortingAlgorithmProvidedException("No sorting algorithm provided");
        if(sortingMadnessRequest.getSortingParameters().isEmpty()) throw new NoSortingAlgorithmProvidedException("No sorting algorithm provided");
        for (SortingParameters param : sortingMadnessRequest.getSortingParameters()) {
            List<JSONObject> dataList = new ArrayList<>(sortingMadnessRequest.getData());
            logParameters(param);
            fillSorter(chooseSorter, param.getSortingAlgorithm(),dataList, getBooleanValueOfDirection(param.getDirection()), new JSONComparator(sortingMadnessRequest.getKeysToSort()));
            logger.info("Starting computation");
            long timeOfSorting = chooseSorter.executeSort(dataList, param.getMaxIterations(), getBooleanValueOfDirection(param.getDirection()),
                    new JSONComparator(sortingMadnessRequest.getKeysToSort()));
            logger.info("Computation finished");
            List<JSONObject> tempList = new ArrayList<>(dataList);
            logger.info("Composing response");
            SortingMadnessResponse sortingMadnessResponse = new SortingMadnessResponse(timeOfSorting, param.getSortingAlgorithm(), param.getDirection(), param.getMaxIterations(), tempList);
            responseList.add(sortingMadnessResponse);
        }
        return getResponseToString(responseList);
    }

    /**
     * Parses List of {@link SortingMadnessSimpleResponse} to String.
     * @param responseList response list to be parsed.
     * @return parsed response list.
     */
    private String getSimpleResponseToString(List<SortingMadnessSimpleResponse> responseList){
        logger.info("Parsing response");
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
        return jsonObjectResult.toString();
    }

    /**
     * Main processing function for Simple sorting
     * @param request REST request to be processed.
     * @return parsed response.
     * @throws ChooseSorter.NoSorterProvided see {@link ChooseSorter.NoSorterProvided}
     * @throws ChooseSorter.EmptyInputException see {@link ChooseSorter.EmptyInputException}
     * @throws InvalidSorterException see {@link InvalidSorterException}
     * @throws NoSortingAlgorithmProvidedException see {@link NoSortingAlgorithmProvidedException}
     */
    public String getResponseSimple(String request) throws ChooseSorter.NoSorterProvided, ChooseSorter.EmptyInputException,
            InvalidSorterException, NoSortingAlgorithmProvidedException{
        logger.debug("Parsing request");
        SortingMadnessRequest sortingMadnessRequest = getDataSimple(request);
        List<SortingMadnessSimpleResponse> responseList = new ArrayList<>();
        ChooseSorter chooseSorter = new ChooseSorter();
        logger.info("Beginning sorting");
        if(sortingMadnessRequest.getSortingParameters() == null) throw new NoSortingAlgorithmProvidedException("No sorting algorithm provided");
        if(sortingMadnessRequest.getSortingParameters().isEmpty()) throw new NoSortingAlgorithmProvidedException("No sorting algorithm provided");
        for (SortingParameters param : sortingMadnessRequest.getSortingParameters()) {
            List<Comparable> dataList = new ArrayList<>(sortingMadnessRequest.getSimpleData());
            logParameters(param);
            fillSorter(chooseSorter, param.getSortingAlgorithm(),dataList, getBooleanValueOfDirection(param.getDirection()));
            logger.info("Starting computation");
            long timeOfSorting = chooseSorter.executeSort(dataList, param.getMaxIterations(), getBooleanValueOfDirection(param.getDirection()));
            logger.info("Computation finished");
            List<Comparable> tempList = new ArrayList<>(dataList);
            logger.info("Composing response");
            SortingMadnessSimpleResponse sortingMadnessSimpleResponse = new SortingMadnessSimpleResponse(timeOfSorting, param.getSortingAlgorithm(), tempList);
            responseList.add(sortingMadnessSimpleResponse);
        }
        return getSimpleResponseToString(responseList);
    }
}

