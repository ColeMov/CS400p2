// --== CS400 Fall 2023 File Header Information ==--
// Name: Cole Movsessian
// Email: movsessian@wisc.edu
// Group: G37
// TA: Grant Waldow
// Lecturer: Florian Heimerl

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the ShortestPathInterface and stores the results of the shortest path search.
 */
public class ShortestPath implements ShortestPathInterface {

  private String origin;
  private String destination;
  private DijkstraGraph pathGraph;

  // Constructor initializes the ShortestPathImplementation with origin, destination, and the graph
  protected ShortestPath(String origin, String destination, DijkstraGraph graph) {
    this.origin = origin;
    this.destination = destination;
    this.pathGraph = graph;

    // Compute the shortest path during object creation
    graph.computeShortestPath(origin, destination);
  }

  /**
   * Returns a list of buildings along the shortest path between the origin and destination buildings.
   *
   * @return The list of buildings along the shortest path
   */
  public List<String> getPathSegments() {
    return pathGraph.shortestPathData(origin, destination);
  }

  /**
   * Returns the walking time along each segment of the shortest path between the origin and
   * destination buildings.
   *
   * @return The list of walking times along the shortest path
   */
  public List<Double> getWalkingTime() {
    List<String> buildingsVisited = getPathSegments();
    List<Double> walkTimes = new ArrayList<>(buildingsVisited.size());

    for (int i = 0; i < buildingsVisited.size() - 1; i++) {
      if (buildingsVisited.get(i + 1) != null) {
        // Add the walking time for each segment of the path
        walkTimes.add(pathGraph.shortestPathCost(buildingsVisited.get(i), buildingsVisited.get(i + 1)));
      }
    }
    return walkTimes;
  }

  /**
   * Returns the total walking time along the shortest path between the origin and destination buildings.
   *
   * @return The total walking time along the shortest path, in seconds
   */
  public double getTotalPathCost() {
    return pathGraph.shortestPathCost(origin, destination);
  }
}
