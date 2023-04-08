package ca.mcmaster.cas.se2aa4.pathfinder.graphs;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.pathfinder.graphs.edges.Edges;
import ca.mcmaster.cas.se2aa4.pathfinder.graphs.nodes.Nodes;

import java.util.List;
import java.util.Map;

public abstract class GraphADT {
    public abstract void addNode(Structs.Vertex centroid, int id);
    public abstract void addEdge(int node1,int node2, int edgeWeight);
    public abstract List<Nodes> returnGraphNodes();
    public abstract List<Edges> returnGraphEdges();
    public abstract Map<Integer, Map<Integer,Integer>> returnGraph();


}
