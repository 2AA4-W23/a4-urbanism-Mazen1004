package ca.mcmaster.cas.se2aa4.pathfinder.graphs;


import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.pathfinder.graphs.nodes.Nodes;
import ca.mcmaster.cas.se2aa4.pathfinder.graphs.edges.Edges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph extends GraphADT {
    private List<Nodes> Nodes = new ArrayList<>();
    private List<Edges> Edges = new ArrayList<>();

    //Adjacency List Stores the Graph with each Node having a list of Edges and corresponding Weights
    private Map<Integer,Map<Integer,Integer>> adjacencyList = new HashMap<>();

    public void addNode(Structs.Vertex centroid, int id){
        Nodes node = new Nodes(centroid,id);
        Nodes.add(node);
        Map<Integer, Integer> nodeNeighbor = new HashMap<>();
        adjacencyList.put(node.getNodeID(),nodeNeighbor);
        System.out.println(adjacencyList.toString());
    }

    public void addEdge(Nodes node1,Nodes node2, int edgeWeight){
        if(adjacencyList.containsKey(node1.getNodeID()) && adjacencyList.containsKey(node2.getNodeID())){
            if(adjacencyList.get(node1.getNodeID()).containsKey(node2.getNodeID())){
                System.out.println("edge already exists between them");
                System.out.println(adjacencyList.toString());
            }
            else{
                Edges edge = new Edges(node1, node2, edgeWeight);
                Edges.add(edge);

                Map<Integer, Integer> neighbors = adjacencyList.getOrDefault(node1.getNodeID(), new HashMap<>());
                neighbors.put(node2.getNodeID(),edge.getEdgeWeight());

                adjacencyList.put(node1.getNodeID(), neighbors);

                Map<Integer, Integer> neighbors2 = adjacencyList.getOrDefault(node2.getNodeID(), new HashMap<>());
                neighbors2.put(node1.getNodeID(),edge.getEdgeWeight());

                adjacencyList.put(node2.getNodeID(), neighbors2);
            }
        }
    }

    public List<Nodes> returnGraphNodes(){
        return Nodes;
    }

    public List<Edges> returnGraphEdges(){
        return Edges;
    }
    public Map<Integer,Map<Integer,Integer>> returnGraph() {
        System.out.println(adjacencyList.toString());
        return adjacencyList;
    }

}