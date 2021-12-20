package pl.put.poznan.sortingmadness.logic;

/**
 * RuntimeException thrown when provided keys are invalid.
 * @author Mateusz Kolano
 * @version 1.0
 */
public class InvalidKeysException extends RuntimeException{
    public InvalidKeysException(String msg){
        super(msg);
    }
}
