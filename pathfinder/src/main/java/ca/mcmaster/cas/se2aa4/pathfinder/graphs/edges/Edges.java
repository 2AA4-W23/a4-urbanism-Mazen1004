package ca.mcmaster.cas.se2aa4.pathfinder.graphs.edges;

import ca.mcmaster.cas.se2aa4.pathfinder.graphs.nodes.Nodes;

public class Edges {
    private int weight;
    private Nodes nodeA;
    private Nodes nodeB;

    public Edges(Nodes node1, Nodes node2, int edgeWeight){
        this.nodeA = node1;
        this.nodeB = node2;
        //weight of edge set by user, technical debt as edgeWeight must be calculated
        this.weight = edgeWeight;
    }

    public int getEdgeWeight(){
        return this.weight;
    }

    public Nodes getEdgeNodeA(){
        return this.nodeA;
    }

    public Nodes getEdgeNodeB(){
        return this.nodeB;
    }
}
