import java.util.List;
/**
 * This interface contains the methods to store the results of the shortest path search
 */
public interface ShortestPathInterface
{
  /*
  Fields:
  private String origin;
  private String destination;

  CONSTRUCTOR:
  protected ShortestPathInterface(String origin, String destination)
  {
    this.origin = origin;
    this.destination = destination;
  }
   */

  /**
   * Returns a list of buildings along the shortest path between the origin and destination
   * buildings
   * @return The list of buildings along the shortest path
   */
  List<String> getPathSegments();

  /**
   * Returns the walking time along each segment of the shortest path between the origin and
   * destination buildings
   * @return The list of walking times along the shortest path
   */
  List<Double> getWalkingTime();

  /**
   * Returns the total walking time along the shortest path between the origin and destination
   * buildings
   * @return The total walking time along the shortest path, in seconds
   */
  double getTotalPathCost();
}
