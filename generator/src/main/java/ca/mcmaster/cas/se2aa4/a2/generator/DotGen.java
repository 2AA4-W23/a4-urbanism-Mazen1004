package ca.mcmaster.cas.se2aa4.a2.generator;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
//add segment import
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;


public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    MeshImplement newMesh = new MeshImplement();

    public Mesh generate() {
        // Create all the vertices
        for(double x = 0; x < width; x += square_size) {
            for (double y = 0; y < height; y += square_size) {
                newMesh.addVertex(x,y);

            }
        }
        //Create all the centroids
        for(double x = 10; x < width-10; x += square_size) {
            for (double y = 10; y < height-10; y += square_size) {
                newMesh.addCentroid(x,y);
            }
        }

        //Draws all the Segments
        for(Vertex v: newMesh.vertices){
            newMesh.addSegment(v);
        }

        // Distribute colors randomly. Vertices are immutable, need to enrich them
        //ArrayList<Vertex> verticesWithColors = new ArrayList<>();
        Random bag = new Random();
        for(Vertex v: newMesh.vertices){
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue;
            newMesh.vertexColors(colorCode,v);
        }

        // Set colors of centroids to red
        //ArrayList<Vertex> centroidsWithColors = new ArrayList<>();
        for(Vertex v: newMesh.centroids){

            newMesh.centroidColors(v);
        }

        return newMesh.getMesh();
    }

}
