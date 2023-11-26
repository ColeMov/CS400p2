// --== CS400 Fall 2023 File Header Information ==--
// Name: Cole Movsessian
// Email: movsessian@wisc.edu
// Group: G37
// TA: Grant Waldow
// Lecturer: Florian Heimerl

import java.io.FileNotFoundException;
import java.io.*;

/**
 * This class implements the BackendInterface for the UW Path Finder app.
 */
public class Backend implements BackendInterface {
    private DijkstraGraph pathGraph;
    private double totalTime = 0.0;

    // Constructor to initialize the BackendImplementation with a DijkstraGraph
    public Backend(DijkstraGraph graph) {
        this.pathGraph = graph;
    }

    /**
     * Reads data from the provided file path and parses it into this Backend's graph.
     *
     * @param filePath The path to the file to read graph data from
     * @throws FileNotFoundException If the data cannot be properly read
     * @return True if the file is successfully loaded
     */
    public boolean readData(String filePath) throws FileNotFoundException {
        // Check for file existence and throw an exception if not found
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        try {
            // Skip the header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                try {
                    // Split the line into data parts using double quotes
                    String[] datas = line.split("\"");
                    String[] secondData = datas[4].split("=");

                    String source = datas[1];
                    String destination = datas[3];
                    Double weight = Double.parseDouble(secondData[1].substring(0, (secondData[1].length() - 2)));

                    // Ensure nodes exist in the graph and add them if not
                    if (!pathGraph.containsNode(source)) {
                        pathGraph.insertNode(source);
                    }
                    if (!pathGraph.containsNode(destination)) {
                        pathGraph.insertNode(destination);
                    }

                    // Ensure edges exist in the graph and add them if not
                    if (!pathGraph.containsEdge(source, destination) || !pathGraph.containsEdge(destination, source)) {
                        pathGraph.insertEdge(source, destination, weight);
                        pathGraph.insertEdge(destination, source, weight);
                        totalTime += weight;
                    }
                } catch (Exception e) {
                    // Handle any exceptions during parsing
                    break;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns a ShortestPath object encapsulating the shortest path between
     * the provided origin and destination buildings.
     *
     * @param origin The building that the shortest path starts at
     * @param destination The building that the shortest path ends at
     * @return An object containing the shortest path
     */
    public ShortestPath getShortestPath(String origin, String destination) {
        return new ShortestPath(origin, destination, pathGraph);
    }

    /**
     * Returns statistics about the provided path:
     * - Number of nodes (buildings)
     * - Number of edges
     * - Total walking time
     *
     * @return An array containing the information about the path
     */
    public Double[] getStatistics() {
        Double[] graphStats = new Double[3];
        graphStats[0] = (double) pathGraph.getNodeCount();
        graphStats[1] = (double) pathGraph.getEdgeCount() / 2.0; // Divide by 2 to count undirected edges
        graphStats[2] = getTotalWalkingTime();

        return graphStats;
    }

    /**
     * Calculate the total walking time for all edges in the graph.
     *
     * @return Total walking time
     */
    private double getTotalWalkingTime() {
        return totalTime;
    }
}
