package pl.put.poznan.sortingmadness.logic;
/**
 * Exception thrown when provided sorting algorithm name is invalid.
 * @author Mateusz Kolano
 * @version 1.0
 */
public class InvalidSorterException extends Exception{
    public InvalidSorterException(String msg) {super(msg);}
}
