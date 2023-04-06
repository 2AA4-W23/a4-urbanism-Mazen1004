import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.pathfinder.graphs.Graph;
import ca.mcmaster.cas.se2aa4.pathfinder.graphs.nodes.Nodes;
import ca.mcmaster.cas.se2aa4.pathfinder.graphs.edges.Edges;
import ca.mcmaster.cas.se2aa4.pathfinder.pathfinding.Dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //Basic Graph for Testing
        Graph graph = new Graph();
        Structs.Vertex vertex = Structs.Vertex.newBuilder().setX(0).setY(0).build();
        Structs.Vertex vertex1 = Structs.Vertex.newBuilder().setX(20).setY(0).build();
        Structs.Vertex vertex2 = Structs.Vertex.newBuilder().setX(20).setY(20).build();

        graph.addNode(vertex,0);
        graph.addNode(vertex1,1);
        graph.addNode(vertex2,2);

        List<Nodes> nodes = graph.returnGraphNodes();
        Nodes Node0 = nodes.get(0);
        Nodes Node1 = nodes.get(1);
        Nodes Node2 = nodes.get(2);

        //Inserting Edge between node 1 and 2 of weight 5
        graph.addEdge(Node0,Node1,5);

        graph.addEdge(Node0,Node2,1);

        graph.addEdge(Node2,Node1,2);

        Map<Integer, Map<Integer, Integer>> adjacencyList = graph.returnGraph();

        /*
        TEST GRAPH CREATED:
                       0
                      / \
                 (W=5)   (W=1)
                   /      \
                  /        2
                  \        /
                   \    (W=2)
                    \    /
                      1

         SHORTEST PATH BETWEEN 0&1 is through 0-2-1
         java -jar pathfinder/pathfinder.jar
         */

        //Finding the Shortest Path between 0-1
        Dijkstra.findShortestPath(adjacencyList,0,1);

    }
}

