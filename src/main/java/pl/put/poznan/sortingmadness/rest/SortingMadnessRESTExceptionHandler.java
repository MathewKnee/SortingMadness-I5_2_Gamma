package pl.put.poznan.sortingmadness.rest;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.put.poznan.sortingmadness.logic.*;
import pl.put.poznan.sortingmadness.model.SortingMadnessErrorResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Exception handler for {@link SortingMadnessController}
 *
 * @author Mateusz Kolano
 * @version 1.0
 */
@ControllerAdvice
public class SortingMadnessRESTExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Logger to log what exception was caught.
     */
    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessHelper.class);
    /**
     * General handler for RuntimeException.
     * @param ex thrown exception.
     * @param request request that caused it.
     * @return REST response that describes the error.
     */
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request){
        logger.debug("RuntimeException was caught. Caused by request "+request.toString());
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        SortingMadnessErrorResponse error = new SortingMadnessErrorResponse("Internal Server Error", details);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /**
     * Handler for {@link JSONException}.
     * @param ex thrown exception.
     * @param request request that caused it.
     * @return REST response that describes the error.
     */
    @ExceptionHandler(JSONException.class)
    public final ResponseEntity<Object> handleJSONException(JSONException ex, WebRequest request){
        logger.debug("JSONException was caught. Caused by request "+request.toString());
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        SortingMadnessErrorResponse error = new SortingMadnessErrorResponse("JSON parsing error", details);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    /**
     * Handler for {@link ChooseSorter.EmptyInputException}.
     * @param ex thrown exception.
     * @param request request that caused it.
     * @return REST response that describes the error.
     */
    @ExceptionHandler(ChooseSorter.EmptyInputException.class)
    public final ResponseEntity<Object> handleEmptyInputException(ChooseSorter.EmptyInputException ex, WebRequest request){
        logger.debug("EmptyInputException was caught. Caused by request "+request.toString());
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        SortingMadnessErrorResponse error = new SortingMadnessErrorResponse("Empty list error", details);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    /**
     * Handler for {@link ChooseSorter.NoSorterProvided}.
     * @param ex thrown exception.
     * @param request request that caused it.
     * @return REST response that describes the error.
     */
    @ExceptionHandler(ChooseSorter.NoSorterProvided.class)
    public final ResponseEntity<Object> handleNoSorterProvidedException(ChooseSorter.NoSorterProvided ex, WebRequest request){
        logger.debug("NoSorterProvided was caught. Caused by request "+request.toString());
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        SortingMadnessErrorResponse error = new SortingMadnessErrorResponse("No sorter chosen error", details);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /**
     * Handler for {@link InvalidKeysException}.
     * @param ex thrown exception.
     * @param request request that caused it.
     * @return REST response that describes the error.
     */
    @ExceptionHandler(InvalidKeysException.class)
    public final ResponseEntity<Object> handleInvalidKeysException(InvalidKeysException ex, WebRequest request){
        logger.debug("InvalidKeysException was caught. Caused by request "+request.toString());
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        SortingMadnessErrorResponse error = new SortingMadnessErrorResponse("Invalid keys to sort objects error", details);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /**
     * Handler for {@link InvalidSorterException}.
     * @param ex thrown exception.
     * @param request request that caused it.
     * @return REST response that describes the error.
     */
    @ExceptionHandler(InvalidSorterException.class)
    public final ResponseEntity<Object> handleInvalidSorterException(InvalidSorterException ex, WebRequest request){
        logger.debug("InvalidSorterException was caught. Caused by request "+request.toString());
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        SortingMadnessErrorResponse error = new SortingMadnessErrorResponse("Invalid sorter error", details);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handler for {@link NoSortingAlgorithmProvidedException}.
     * @param ex thrown exception.
     * @param request request that caused it.
     * @return REST response that describes the error.
     */
    @ExceptionHandler(NoSortingAlgorithmProvidedException.class)
    public final ResponseEntity<Object> handleNoSortingAlgorithmProvidedException(NoSortingAlgorithmProvidedException ex, WebRequest request){
        logger.debug("NoSortingAlgorithmProvidedException was caught. Caused by request "+request.toString());
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        SortingMadnessErrorResponse error = new SortingMadnessErrorResponse("No sorting algorithm provided error", details);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
