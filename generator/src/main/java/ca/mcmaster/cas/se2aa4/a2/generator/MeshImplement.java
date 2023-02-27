package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
//add segment import
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

public class MeshImplement implements MeshADT  {

    public ArrayList<Vertex> vertices = new ArrayList<>();
    public ArrayList<Segment> segments = new ArrayList<>();
    public ArrayList<Vertex> centroids = new ArrayList<>();
    public ArrayList<Vertex> verticesWithColors = new ArrayList<Vertex>();
    public ArrayList<Vertex> centroidsWithColors = new ArrayList<Vertex>();
    public MeshImplement(){
        segments = new ArrayList<Segment>();
        vertices = new ArrayList<Vertex>();
        centroids = new ArrayList<Vertex>();
        verticesWithColors = new ArrayList<Vertex>();
        centroidsWithColors = new ArrayList<Vertex>();
    }

    @Override
    public void addVertex(double x, double y) {
        Float xRound = Float.parseFloat(String.format("%.2f",x));
        Float yRound = Float.parseFloat(String.format("%.2f",y));
        vertices.add(Vertex.newBuilder().setX((double) xRound).setY((double) yRound).build());


    }

    @Override
    public void addSegment(Vertex v) {
        //Draws Horizontal Segments
        if((vertices.indexOf(v)+1)%25 != 0){
            segments.add(Segment.newBuilder().setV1Idx(vertices.indexOf(v)).setV2Idx(vertices.indexOf(v)+1).build());
        }
        //Draws Vertical Segments
        if((vertices.indexOf(v)+25) < vertices.size()){
            segments.add(Segment.newBuilder().setV1Idx(vertices.indexOf(v)).setV2Idx(vertices.indexOf(v)+25).build());
        }
    }

    @Override
    public void addCentroid(double x, double y) {
        Float xRound = Float.parseFloat(String.format("%.2f",x));
        Float yRound = Float.parseFloat(String.format("%.2f",y));
        centroids.add(Vertex.newBuilder().setX((double) xRound).setY((double) yRound).build());;
    }

    @Override
    public Structs.Mesh getMesh() {
        return Structs.Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segments).addAllVertices(centroidsWithColors).build();
    }

    @Override
    public void vertexColors(String colorCode, Vertex vertex) {
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        Vertex colored = Vertex.newBuilder(vertex).addProperties(color).build();
        verticesWithColors.add(colored);
    }

    @Override
    public void centroidColors(Vertex vertex) {
        // Set colors of centroids to red
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("255,0,0").build();
        Vertex colored = Vertex.newBuilder(vertex).addProperties(color).build();
        verticesWithColors.add(colored);
    }

}
