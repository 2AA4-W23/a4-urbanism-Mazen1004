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
        if(!adjacencyList.containsKey(id)){
            Nodes node = new Nodes(centroid,id);
            Nodes.add(node);
            Map<Integer, Integer> nodeNeighbor = new HashMap<>();
            adjacencyList.put(node.getNodeID(),nodeNeighbor);
            //System.out.println(adjacencyList.toString());
        }
    }

    public void addEdge(int node1ID,int node2ID, int edgeWeight){
        if(adjacencyList.containsKey(node1ID) && adjacencyList.containsKey(node2ID)){
            if(adjacencyList.get(node1ID).containsKey(node2ID)){
                System.out.println("edge already exists between them");
                System.out.println(adjacencyList.toString());
            }
            else{
                Edges edge = new Edges(node1ID, node2ID, edgeWeight);
                Edges.add(edge);

                Map<Integer, Integer> neighbors = adjacencyList.getOrDefault(node1ID, new HashMap<>());
                neighbors.put(node2ID,edge.getEdgeWeight());

                adjacencyList.put(node1ID, neighbors);

                Map<Integer, Integer> neighbors2 = adjacencyList.getOrDefault(node2ID, new HashMap<>());
                neighbors2.put(node1ID,edge.getEdgeWeight());

                adjacencyList.put(node2ID, neighbors2);
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