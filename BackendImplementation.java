// --== CS400 Fall 2023 File Header Information ==--
// Name: Cole Movsessian
// Email: movsessian@wisc.edu
// Group: G37
// TA: Grant Waldow
// Lecturer: Florian Heimerl

import java.io.FileNotFoundException;

/**
 * This interface contains the methods used in the backend of the UW Path Finder app
 */
public class BackendImplementation {

  private PlaceholderMap graph;
  public BackendImplementation(PlaceholderMap graph){
      this.graph = graph;
  }


    /**
     * Reads the data from the provided file path and parses it into this Backend's graph
     * @param filepath The path to the file to read graph data from
     * @throws FileNotFoundException If the data is unable to be properly read
     * @return True if file is successfully loaded
     */
    public boolean readData(String filepath) throws FileNotFoundException{
      return false;
    }

    /**
     * Returns a ShortestPath object that encapsulates the shortest path between the provided
     * origin and destination buildings
     * @param origin The building that the shortest path starts at
     * @param destination The building that the shortest path ends at
     * @return An object containing the shortest path
     */
    public ShortestPathInterface getShortestPath(String origin, String destination){
      return null;
    }


    /**
     * Returns a list of statistics about the provided path:
     * <li> Number of nodes (buildings)
     * <li> Number of edges
     * <li> Total walking time
     * @param path The shortest path between two nodes
     * @return An array containing the information about the path
     */
    public Double[] getStatistics(ShortestPathInterface path){
      return null;
    }
}
