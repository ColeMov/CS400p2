// --== CS400 Fall 2023 File Header Information ==--
// Name: Cole Movsessian
// Email: movsessian@wisc.edu
// Group: G37
// TA: Grant Waldow
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the Backend Developer's implementation of the Backend interface.
 * These tests verify the functionality of methods related to reading and managing meteorite data.
 */

public class BackendDeveloperTests {
    private Backend backend;

    /**
     * Tests that the readData method returns true upon success
     */
    @Test
    public void readFileDataTest(){
        DijkstraGraph graph = new DijkstraGraph<String, Double>(new PlaceholderMap<>());
        Backend backend = new Backend(graph);

        try{
            Assertions.assertTrue(backend.readData("campus.dot"));
        }catch(FileNotFoundException e){
            Assertions.fail("File Not Found");
        }catch(Exception e){
            Assertions.fail(e.getMessage());
        }
    }

    /**
     * Tests that the readData method returns NoFileFoundException upon failure
     */
    @Test
    public void readFileDataNoFileFoundTest(){
        DijkstraGraph graph = new DijkstraGraph<String, Double>(new PlaceholderMap<>());
        Backend backend = new Backend(graph);

        try{
            backend.readData("fakeFile.txt");
        }catch(FileNotFoundException e){
            Assertions.assertTrue(true);
        }catch(Exception e){
            Assertions.fail("Other error");
        }
    }

    /**
     * Tests getShortestPath method returns a ShortestPath object
     */
    @Test
    public void shortestPathBackendTest(){
        DijkstraGraph graph = new DijkstraGraph<String, Double>(new PlaceholderMap<>());
        Backend backend = new Backend(graph);
        try{
            backend.readData("campus.dot");
        }catch(Exception e){
            Assertions.fail("File read error");
        }
        ShortestPath shortestPath = backend.getShortestPath("Memorial Union","Science Hall");

        if(shortestPath == null){
            Assertions.fail("No shortest path returned");
        }else{
            Assertions.assertTrue(true);
        }
    }

    /**
     * Tests statistics method returns double list with shortest path statistics
     */
    @Test
    public void getStatisticsTest(){
        DijkstraGraph graph = new DijkstraGraph<String, Double>(new PlaceholderMap<>());
        Backend backend = new Backend(graph);

        try{
            backend.readData("campus.dot");
        }catch(Exception e){
            Assertions.fail(e.getMessage());
        }

        Double[] pathStats = backend.getStatistics();

        if(pathStats[0] != 160.0 || pathStats[1] != 508.0 || pathStats[2] != 76605.00000000001){
            Assertions.fail("No shortest path statistics");
        }else{
            Assertions.assertTrue(true);
        }
    }

    /**
     * Tests the ShortestPathImplementation class and its internal methods
     */
    @Test
    public void shortestPathImplementationTest(){
        DijkstraGraph graph = new DijkstraGraph<String, Double>(new PlaceholderMap<>());
        Backend backend = new Backend(graph);
        try{
            backend.readData("campus.dot");
        }catch(Exception e){
            Assertions.fail("File read error");
        }
        ShortestPath shortestPath = backend.getShortestPath("Memorial Union","Mack House");

        if(shortestPath.getPathSegments() == null || shortestPath.getWalkingTime() == null || shortestPath.getTotalPathCost() == 0.0){
            Assertions.fail("No shortest path returned");
        }else{
            Assertions.assertTrue(true);
        }
    }

    /**
     * Tests that the frontend can properly access and read files from the backend
     */
    @Test
    public void readFileIntegrationTest(){
        TextUITester tester = new TextUITester("1\n4");
        Scanner scnr = new Scanner(System.in);
        Backend backend = new Backend(new DijkstraGraph<>(new PlaceholderMap<>()));
        Frontend frontend = new Frontend(backend, scnr);

        frontend.startMainMenu();
        String output = tester.checkOutput();
        Assertions.assertTrue(output.contains("File loaded successfully!"));
    }

    /**
     * Tests that the frontend can accept a return and handle the shortest path from backend
     */
    @Test
    public void displayStatisticsIntegrationTest(){
        TextUITester tester = new TextUITester("1\n2\n4");
        Scanner scnr = new Scanner(System.in);
        Backend backend = new Backend(new DijkstraGraph<>(new PlaceholderMap<>()));
        Frontend frontend = new Frontend(backend, scnr);

        frontend.startMainMenu();
        String output = tester.checkOutput();
        Assertions.assertTrue(output.contains("Below is the current dataset."));
        Assertions.assertTrue(!(output.contains("You have not loaded a file yet")));
    }
}
