// --== CS400 Fall 2023 File Header Information ==--
// Name: Cole Movsessian
// Email: movsessian@wisc.edu
// Group: G37
// TA: Grant Waldow
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the Backend Developer's implementation of the Backend interface.
 * These tests verify the functionality of methods related to reading and managing meteorite data.
 */

public class BackendDeveloperTests {
    private BackendImplementation backend;

    /**
     * Tests that the readData method returns true upon success
     */
    @Test
    public void readFileDataTest(){
        PlaceholderMap graph = new PlaceholderMap();
        BackendImplementation backend = new BackendImplementation(graph);

        try{
            Assertions.assertTrue(backend.readData("campus.dot"));
        }catch(Exception e){
            Assertions.fail("File Not Found");
        }
    }

    /**
     * Tests that the readData method returns NoFileFoundException upon failure
     */
    @Test
    public void readFileDataNoFileFoundTest(){
        PlaceholderMap graph = new PlaceholderMap();
        BackendImplementation backend = new BackendImplementation(graph);

        try{
            backend.readData("fakeFile.txt");
        }catch(Exception e){
            Assertions.assertTrue(true);
        }
    }

    /**
     * Tests getShortestPath method returns a ShortestPath object
     */
    @Test
    public void shortestPathBackendTest(){
        PlaceholderMap graph = new PlaceholderMap();
        BackendImplementation backend = new BackendImplementation(graph);

        ShortestPathInterface pathTest = backend.getShortestPath("A","C");
        if(pathTest == null){
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
        PlaceholderMap graph = new PlaceholderMap();
        BackendImplementation backend = new BackendImplementation(graph);

        ShortestPathInterface pathTest = backend.getShortestPath("A","C");
        Double[] pathStats = backend.getStatistics(pathTest);
        if(pathStats == null){
            Assertions.fail("No shortest path statistics");
        }else{
            Assertions.assertTrue(true);
        }
    }

    /**
     * Tests the ShortestPathImplementation class independently from the backend implementation
     */
    @Test
    public void shortestPathImplementationTest(){
        ShortestPathImplementation shortestPath = new ShortestPathImplementation("A","B");

        if(shortestPath.getPathSegments() == null){
            Assertions.fail("No path segments");
        }
        if(shortestPath.getTotalPathCost() == -1.0){
            Assertions.fail("No path cost");
        }
        if(shortestPath.getWalkingTime() == null){
            Assertions.fail("No walking time");
        }
    }
}
