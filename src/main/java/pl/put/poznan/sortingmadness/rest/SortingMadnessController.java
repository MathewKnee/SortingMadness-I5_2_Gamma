package pl.put.poznan.sortingmadness.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sortingmadness.logic.*;





/**
 * REST Controller
 *
 * @author Aleksandra Kiel
 * @version 1.0
 */
@RestController
public class SortingMadnessController {
    /**
     * Logger to log receiving requests.
     */
    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);
    /**
     * Logic class that process the request and generates response
     */
    SortingMadnessHelper sortingMadnessHelper = new SortingMadnessHelper();

    /**
     * Object Sorting - Handles REST POST request, delegates the sorting to the sorting logic.
     * @param sortingMadnessRequest String representation of request. For details see {@link pl.put.poznan.sortingmadness.model.SortingMadnessRequest}.
     * @return response object generated by {@link SortingMadnessHelper}. For details see {@link pl.put.poznan.sortingmadness.model.SortingMadnessResponse}.
     * @throws ChooseSorter.EmptyInputException see {@link ChooseSorter.EmptyInputException}
     * @throws ChooseSorter.NoSorterProvided see {@link ChooseSorter.NoSorterProvided}
     * @throws InvalidSorterException see {@link InvalidSorterException}
     * @throws NoSortingAlgorithmProvidedException see {@link NoSortingAlgorithmProvidedException}
     */
    @PostMapping(value = "/sortingMadness",produces = "application/json")
    public String postObjectSorting(@RequestBody String sortingMadnessRequest) throws ChooseSorter.EmptyInputException,
            ChooseSorter.NoSorterProvided, InvalidSorterException, NoSortingAlgorithmProvidedException{
        logger.info("POST request for object sorting received.");
        return sortingMadnessHelper.getResponseObject(sortingMadnessRequest);
    }

    /**
     * Simple Sorting - Handles REST POST request, delegates the sorting to the sorting logic.
     * @param sortingMadnessRequest String representation of request. For details see {@link pl.put.poznan.sortingmadness.model.SortingMadnessRequest}.
     * @return response object generated by {@link SortingMadnessHelper}. For details see {@link pl.put.poznan.sortingmadness.model.SortingMadnessSimpleResponse}.
     * @throws ChooseSorter.EmptyInputException see {@link ChooseSorter.EmptyInputException}
     * @throws ChooseSorter.NoSorterProvided see {@link ChooseSorter.NoSorterProvided}
     * @throws InvalidSorterException see {@link InvalidSorterException}
     * @throws NoSortingAlgorithmProvidedException see {@link NoSortingAlgorithmProvidedException}
     */
    @PostMapping(value = "/sortingMadnessSimple",produces = "application/json")
    public String postSimpleSorting(@RequestBody String sortingMadnessRequest) throws ChooseSorter.EmptyInputException,
            ChooseSorter.NoSorterProvided, InvalidSorterException, NoSortingAlgorithmProvidedException{
        logger.info("POST request for simple sorting received.");
        return sortingMadnessHelper.getResponseSimple(sortingMadnessRequest);
    }

    /**
     * Object Sorting - Handles REST GET request, delegates the sorting to the sorting logic.
     * @param sortingMadnessRequest String representation of request. For details see {@link pl.put.poznan.sortingmadness.model.SortingMadnessRequest}.
     * @return response object generated by {@link SortingMadnessHelper}. For details see {@link pl.put.poznan.sortingmadness.model.SortingMadnessResponse}.
     * @throws ChooseSorter.EmptyInputException see {@link ChooseSorter.EmptyInputException}
     * @throws ChooseSorter.NoSorterProvided see {@link ChooseSorter.NoSorterProvided}
     * @throws InvalidSorterException see {@link InvalidSorterException}
     * @throws NoSortingAlgorithmProvidedException see {@link NoSortingAlgorithmProvidedException}
     */
    @GetMapping(value = "/sortingMadness",produces = "application/json")
    public String getObjectSorting(@RequestBody String sortingMadnessRequest) throws ChooseSorter.EmptyInputException,
            ChooseSorter.NoSorterProvided, InvalidSorterException, NoSortingAlgorithmProvidedException{
        logger.info("GET request for object sorting received.");
        return sortingMadnessHelper.getResponseObject(sortingMadnessRequest);
    }

    /**
     * Object Sorting - Handles REST GET request, delegates the sorting to the sorting logic.
     * @param sortingMadnessRequest String representation of request. For details see {@link pl.put.poznan.sortingmadness.model.SortingMadnessRequest}.
     * @return response object generated by {@link SortingMadnessHelper}. For details see {@link pl.put.poznan.sortingmadness.model.SortingMadnessResponse}.
     * @throws ChooseSorter.EmptyInputException see {@link ChooseSorter.EmptyInputException}
     * @throws ChooseSorter.NoSorterProvided see {@link ChooseSorter.NoSorterProvided}
     * @throws InvalidSorterException see {@link InvalidSorterException}
     * @throws NoSortingAlgorithmProvidedException see {@link NoSortingAlgorithmProvidedException}
     */
    @GetMapping(value = "/sortingMadnessSimple",produces = "application/json")
    public String getSimpleSorting(@RequestBody String sortingMadnessRequest) throws ChooseSorter.EmptyInputException,
            ChooseSorter.NoSorterProvided, InvalidSorterException, NoSortingAlgorithmProvidedException{
        logger.info("GET request for simple sorting received.");
        return sortingMadnessHelper.getResponseSimple(sortingMadnessRequest);
    }

    /**
     * Object Sorting - Handles REST GET request, delegates the testing to the testing logic.
     * @return response object generated by {@link SortingMadnessHelper}.
     */
    @GetMapping(value = "/sortingMadnessTest",produces = "application/json")
    public String getTest(){
        logger.info("GET request for test received.");
        return sortingMadnessHelper.getTestResults();
    }

    /**
     * Object Sorting - Handles REST GET request, delegates the testing to the testing logic.
     * @return response object generated by {@link SortingMadnessHelper}.
     */
    @PostMapping(value = "/sortingMadnessTest",produces = "application/json")
    public String postTest(){
        logger.info("POST request for test received.");
        return sortingMadnessHelper.getTestResults();
    }
}
