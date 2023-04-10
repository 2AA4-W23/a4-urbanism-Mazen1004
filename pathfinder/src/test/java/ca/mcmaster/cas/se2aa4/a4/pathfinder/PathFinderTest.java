
package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.pathfinder.graphs.Graph;
import ca.mcmaster.cas.se2aa4.pathfinder.graphs.edges.Edges;
import ca.mcmaster.cas.se2aa4.pathfinder.graphs.nodes.Nodes;


import ca.mcmaster.cas.se2aa4.pathfinder.pathfinding.Dijkstra;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PathFinderTest {
    //TESTING PATHFINDER (DIJKSTRA ALGORITHM)
    @Test
    public void PathfinderTest(){
        //Initializing Graph with Nodes and Edges
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

        graph.addEdge(0,1,5);
        graph.addEdge(0,2,1);
        graph.addEdge(2,1,2);

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
        List<Integer> calculatedShortestPath = Dijkstra.findShortestPath(adjacencyList, 0, 1);

        List<Integer> expectedShortestPath = Arrays.asList(0,2,1);

        assertArrayEquals(expectedShortestPath.toArray(),calculatedShortestPath.toArray());
    }



    //TESTING GRAPH-ADT FUNCTIONS
    @Test
    public void testAddNode() {
        Graph graph = new Graph();
        Structs.Vertex centroid = Structs.Vertex.newBuilder().setX(20).setY(0).build();
        int id = 10;
        graph.addNode(centroid, id);
        List<Nodes> nodes = graph.returnGraphNodes();
        assertEquals(1, nodes.size());
        assertEquals(id, nodes.get(0).getNodeID());
        assertEquals(centroid, nodes.get(0).getNodeCentroid());
    }

    @Test
    public void testAddEdge() {
        Graph graph = new Graph();
        //Creating Nodes and connecting them with an edge of weight 5
        Structs.Vertex centroid1 = Structs.Vertex.newBuilder().setX(20).setY(0).build();
        Structs.Vertex centroid2 = Structs.Vertex.newBuilder().setX(20).setY(20).build();
        Nodes node1 = new Nodes(centroid1, 10);
        Nodes node2 = new Nodes(centroid2, 20);
        int weight = 5;
        graph.addNode(centroid1, 10);
        graph.addNode(centroid2, 20);
        graph.addEdge(10, 20, 5);

        //Checks whether nodes are added correctly
        List<Edges> edges = graph.returnGraphEdges();
        assertEquals(1, edges.size());

    }


    //TESTING EDGES FUNCTIONS
    @Test
    public void testGetEdgeWeight() {
        Structs.Vertex vertex1 = Structs.Vertex.newBuilder().setX(20).setY(0).build();
        Structs.Vertex vertex2 = Structs.Vertex.newBuilder().setX(20).setY(20).build();
        Nodes node1 = new Nodes(vertex1, 1);
        Nodes node2 = new Nodes(vertex2,1);
        Edges edge = new Edges(1, 2, 5);
        int expectedWeight = 5;
        int actualWeight = edge.getEdgeWeight();
        assertEquals(expectedWeight, actualWeight);
    }

    @Test
    public void testCalculateWeight() {
        Structs.Vertex vertex1 = Structs.Vertex.newBuilder().setX(20).setY(0).build();
        Structs.Vertex vertex2 = Structs.Vertex.newBuilder().setX(20).setY(20).build();
        Nodes node1 = new Nodes(vertex1,1);
        Nodes node2 = new Nodes(vertex2,2);
        Edges edge = new Edges(1, 2, 5);
        int expectedWeight = 5;
        int actualWeight = edge.getEdgeWeight();
        assertEquals(expectedWeight, actualWeight);
        assertEquals(expectedWeight, edge.getEdgeWeight());
    }

    //TESTING NODES FUNCTIONS
    @Test
    public void testGetNodeID() {
        Structs.Vertex centroid = Structs.Vertex.newBuilder().setX(20).setY(0).build();
        Nodes node = new Nodes(centroid, 10);
        assertEquals(10, node.getNodeID());
    }

    @Test
    public void testGetNodeCentroid() {
        Structs.Vertex centroid = Structs.Vertex.newBuilder().setX(20).setY(0).build();
        Nodes node = new Nodes(centroid, 10);
        assertEquals(centroid, node.getNodeCentroid());
    }
}
