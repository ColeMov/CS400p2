// --== CS400 Fall 2023 File Header Information ==--
// Name: Cole Movsessian
// Email: movsessian@wisc.edu
// Group: G37
// TA: Grant Waldow
// Lecturer: Florian Heimerl

import java.util.List;

/**
 * This interface contains the methods to store the results of the shortest path search
 */
public class ShortestPathImplementation
{

  private String origin;
  private String destination;

  protected ShortestPathImplementation(String origin, String destination)
  {
    this.origin = origin;
    this.destination = destination;
  }


    /**
     * Returns a list of buildings along the shortest path between the origin and destination
     * buildings
     * @return The list of buildings along the shortest path
     */
    List<String> getPathSegments(){
        return null;
    }

    /**
     * Returns the walking time along each segment of the shortest path between the origin and
     * destination buildings
     * @return The list of walking times along the shortest path
     */
    List<Double> getWalkingTime(){
        return null;
    }

    /**
     * Returns the total walking time along the shortest path between the origin and destination
     * buildings
     * @return The total walking time along the shortest path, in seconds
     */
    double getTotalPathCost(){
        return -1.0;
    }
}
