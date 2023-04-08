package ca.mcmaster.cas.se2aa4.pathfinder.graphs.edges;

public class Edges {
    private int weight;
    private int node1ID;
    private int node2ID;

    public Edges(int node1,int node2, int edgeWeight){
        this.node1ID = node1;
        this.node1ID = node2;
        //weight of edge set by user, technical debt as edgeWeight must be calculated
        this.weight = edgeWeight;
    }

    public int getEdgeWeight(){
        return this.weight;
    }

    public int getEdgeNodeA(){
        return this.node1ID;
    }

    public int getEdgeNodeB(){
        return this.node2ID;
    }
}
