package pl.put.poznan.sortingmadness.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class used to start Spring Application
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sortingmadness.rest"})
public class SortingMadnessApplication {
    /**
     * Starts the Spring Application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SortingMadnessApplication.class, args);
    }
}
