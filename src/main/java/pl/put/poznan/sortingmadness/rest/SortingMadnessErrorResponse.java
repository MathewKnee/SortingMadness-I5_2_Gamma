package pl.put.poznan.sortingmadness.rest;

import pl.put.poznan.sortingmadness.model.SortingMadnessResponse;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Error response wrapper for {@link SortingMadnessResponse}
 *
 * @author -
 * @version 1.0
 */
public class SortingMadnessErrorResponse {
    private String message;
    private List<String> details;
    public SortingMadnessErrorResponse(String message, List<String> details){
        super();
        this.message = message;
        this.details = details;
    }

    public List<String> getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }
}
