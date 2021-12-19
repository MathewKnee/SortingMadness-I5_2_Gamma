package pl.put.poznan.sortingmadness.logic;
/**
 * Exception thrown when user does not provide sorting algorithm.
 * @author Mateusz Kolano
 * @version 1.0
 */
public class NoSortingAlgorithmProvidedException extends Exception{
    public NoSortingAlgorithmProvidedException(String msg) {super(msg);}
}
