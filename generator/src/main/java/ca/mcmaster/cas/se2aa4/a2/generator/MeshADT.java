package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
//add segment import
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public interface MeshADT {

    public void addVertex(double x, double y);
    void addSegment(Vertex v);
    public void addCentroid(double x, double y);

    public Mesh getMesh();

    void vertexColors(String colorCode, Vertex vertex);

    void centroidColors(Vertex vertex);


}




