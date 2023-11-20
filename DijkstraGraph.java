// --== CS400 File Header Information ==--
// Name: Cole Movsessian
// Email: movsessian@wisc.edu
// Group and Team: G27 3
// Group TA: Grant Waldow
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes. This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
        extends BaseGraph<NodeType, EdgeType>
        implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph. The final node in this path is stored in its node
     * field. The total cost of this path is stored in its cost field. And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in its node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;

        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }

        public int compareTo(SearchNode other) {
            if (cost > other.cost)
                return +1;
            if (cost < other.cost)
                return -1;
            return 0;
        }
    }

    /**
     * Constructor that sets the map that the graph uses.
     * @param map the map that the graph uses to map a data object to the node
     *        object it is stored in
     */
    public DijkstraGraph(MapADT<NodeType, Node> map) {
        super(map);
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations. The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *                                or when either start or end data do not
     *                a correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) throws NoSuchElementException {
        // implement in step 5.3
        if(!nodes.containsKey(start) || !nodes.containsKey(end)){
            throw new NoSuchElementException("Start or end node not present");
        }

        PlaceholderMap visited = new PlaceholderMap();
        PriorityQueue<SearchNode> queue = new PriorityQueue<>();
        queue.add(new SearchNode(nodes.get(start),0,null));
        while(!queue.isEmpty()){
            SearchNode currentNode = queue.poll();
            if(currentNode.node.data.equals(nodes.get(end).data)){
                return currentNode;
            }
            if(!visited.containsKey(currentNode.node)){
                visited.put(currentNode.node,currentNode.node.data);
                for(int i = 0; i < currentNode.node.edgesLeaving.size();i++){
                    if(!visited.containsKey(currentNode.node.edgesLeaving.get(i).successor)){
                        Edge destination = currentNode.node.edgesLeaving.get(i);
                        if(destination.data == null){
                            queue.add(new SearchNode(destination.successor, currentNode.cost,currentNode));
                        }
                        queue.add(new SearchNode(destination.successor, currentNode.cost + destination.data.doubleValue(),currentNode));
                    }
                }
            }
        }
        throw new NoSuchElementException("No path found");
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value. This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path. This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        // implement in step 5.4
        List<NodeType> shortestPathList = new LinkedList<>();
        SearchNode node = computeShortestPath(start, end);
        while(node != null){
            shortestPathList.add(0,node.node.data);
            node = node.predecessor;
        }
        return shortestPathList;
	}

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path from the node containing the start data to the node containing the
     * end data. This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
        // implement in step 5.4
        SearchNode destinationNode = computeShortestPath(start, end);
        return destinationNode.cost;
    }

    /**
     * Tester for graph implementation from lecture and confirms shortest paths
     */
    @Test
    public void lectureGraphImplementation1(){
        DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");

        graph.insertEdge("A","B",4);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","E",15);
        graph.insertEdge("B","D",1);
        graph.insertEdge("B","E",10);
        graph.insertEdge("C","D",5);
        graph.insertEdge("D","E",3);
        graph.insertEdge("D","F",0);
        graph.insertEdge("F","D",2);
        graph.insertEdge("F","H",4);
        graph.insertEdge("G","H",4);

        Assertions.assertEquals("[A, B, D, E]", graph.shortestPathData("A", "E").toString());
        Assertions.assertEquals(8, graph.shortestPathCost("A", "E"));
        Assertions.assertEquals("[A, B, D, F]", graph.shortestPathData("A", "F").toString());
        Assertions.assertEquals(5, graph.shortestPathCost("A", "F"));
    }

    /**
     * Tester for graph implementation from lecture and confirms shortest paths
     * given new start and destination nodes
     */
    @Test
    public void lectureGraphImplementation2(){
        DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");

        graph.insertEdge("A","B",4);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","E",15);
        graph.insertEdge("B","D",1);
        graph.insertEdge("B","E",10);
        graph.insertEdge("C","D",5);
        graph.insertEdge("D","E",3);
        graph.insertEdge("D","F",0);
        graph.insertEdge("F","D",2);
        graph.insertEdge("F","H",4);
        graph.insertEdge("G","H",4);

        Assertions.assertEquals("[C, D, F, H]", graph.shortestPathData("C", "H").toString());
        Assertions.assertEquals(9, graph.shortestPathCost("C", "H"));
        Assertions.assertEquals("[B, D, E]", graph.shortestPathData("B", "E").toString());
        Assertions.assertEquals(4, graph.shortestPathCost("B", "E"));
    }

    /**
     * Tester for graph implementation from lecture and tests behavior given
     * no possible sequence of directed edges connecting start and destination
     */
    @Test
    public void lectureGraphImplementation3(){
        DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");

        graph.insertEdge("A","B",4);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","E",15);
        graph.insertEdge("B","D",1);
        graph.insertEdge("B","E",10);
        graph.insertEdge("C","D",5);
        graph.insertEdge("D","E",3);
        graph.insertEdge("D","F",0);
        graph.insertEdge("F","D",2);
        graph.insertEdge("F","H",4);
        graph.insertEdge("G","H",4);

        Assertions.assertThrows(NoSuchElementException.class, () -> graph.computeShortestPath("A", "G"));
        Assertions.assertThrows(NoSuchElementException.class, () -> graph.computeShortestPath("D", "A"));
        Assertions.assertThrows(NoSuchElementException.class, () -> graph.computeShortestPath("B", "C"));
    }

    /**
     * Tester for graph implementation from lecture and tests behavior given
     * start and end nodes not present in the graph
     */
    @Test
    public void lectureGraphImplementation4(){
        DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");

        graph.insertEdge("A","B",4);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","E",15);
        graph.insertEdge("B","D",1);
        graph.insertEdge("B","E",10);
        graph.insertEdge("C","D",5);
        graph.insertEdge("D","E",3);
        graph.insertEdge("D","F",0);
        graph.insertEdge("F","D",2);
        graph.insertEdge("F","H",4);
        graph.insertEdge("G","H",4);

        Assertions.assertThrows(NoSuchElementException.class, () -> graph.computeShortestPath("X", "Z"));
    }
}
