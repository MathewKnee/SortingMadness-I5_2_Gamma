package pl.put.poznan.sortingmadness.rest;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Error response wrapper for {@link SortingMadnessResponse}
 *
 * @author -
 * @version 1.0
 */
@XmlRootElement(name = "error")
public class SortingMadnessErrorResponse {
    /**
     * Error message to be sent
     */
    private String message;
    /**
     * Exception details
     */
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
