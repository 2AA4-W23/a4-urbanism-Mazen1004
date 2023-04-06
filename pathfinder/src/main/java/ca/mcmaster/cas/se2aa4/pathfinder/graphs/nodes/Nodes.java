package ca.mcmaster.cas.se2aa4.pathfinder.graphs.nodes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Nodes {
    //Nodes are created from centroids in the mesh, where each centroid has a unique ID
    private Structs.Vertex centroid;
    private  int id;

    public Nodes(Structs.Vertex centroid,int id){
        this.id = id;
        this.centroid = centroid;
    }

    public int getNodeID(){
        return this.id;
    }

    public Structs.Vertex getNodeCentroid(){
        return this.centroid;
    }

}
