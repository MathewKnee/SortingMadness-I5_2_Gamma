package pl.put.poznan.sortingmadness.rest;

import org.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Exception handler for {@link SortingMadnessController}
 *
 * @author -
 * @version 1.0
 */
@ControllerAdvice
public class SortingMadnessRESTExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Handler for {@link JSONException}
     * @param ex - thrown exception
     * @param request - request that caused it
     * @return REST response that describes the error
     */
    @ExceptionHandler(JSONException.class)
    public final ResponseEntity<Object> handleJSONException(JSONException ex, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        SortingMadnessErrorResponse error = new SortingMadnessErrorResponse("JSON parsing error", details);
        ResponseEntity<Object> response = new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
