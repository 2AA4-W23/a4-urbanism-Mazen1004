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

    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Segment> segments = new ArrayList<>();
    ArrayList<Vertex> centroids = new ArrayList<>();

    public Mesh generate() {
        // Create all the vertices
        for(double x = 0; x < width; x += square_size) {
            for (double y = 0; y < height; y += square_size) {
                Float xRound = Float.parseFloat(String.format("%.2f",x));
                Float yRound = Float.parseFloat(String.format("%.2f",y));
                vertices.add(Vertex.newBuilder().setX((double) xRound).setY((double) yRound).build());;

            }
        }
        //Create all the centroids
        for(double x = 10; x < width-10; x += square_size) {
            for (double y = 10; y < height-10; y += square_size) {
                Float xRound = Float.parseFloat(String.format("%.2f",x));
                Float yRound = Float.parseFloat(String.format("%.2f",y));
                centroids.add(Vertex.newBuilder().setX((double) xRound).setY((double) yRound).build());;

            }
        }


        /*
        for(int x = 0; x < width; x += square_size*2) {
            for (int y = 0; y < height; y += square_size*2) {
                //first dot
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build()); //overlapping with third dot
                //second dot left
                vertices.add(Vertex.newBuilder().setX((double) x + square_size).setY((double) y).build());
                //third dot above first
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y + square_size).build());
                //x+y slanted 45 deg
                vertices.add(Vertex.newBuilder().setX((double) x + square_size).setY((double) y + square_size).build());
                // (0,0) (20,0) (0,20) (20,20)
                //   0      1     2       3
                numOfVertices+=4;


            }
        }
        */


        //Draws all the Segments
        for(Vertex v: vertices){
            //Draws Horizontal Segments
            if((vertices.indexOf(v)+1)%25 != 0){
                segments.add(Segment.newBuilder().setV1Idx(vertices.indexOf(v)).setV2Idx(vertices.indexOf(v)+1).build());
            }
            //Draws Vertical Segments
            if((vertices.indexOf(v)+25) < vertices.size()){
                segments.add(Segment.newBuilder().setV1Idx(vertices.indexOf(v)).setV2Idx(vertices.indexOf(v)+25).build());
            }

        }

        // Distribute colors randomly. Vertices are immutable, need to enrich them
        ArrayList<Vertex> verticesWithColors = new ArrayList<>();
        Random bag = new Random();
        for(Vertex v: vertices){
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue;
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
            verticesWithColors.add(colored);
        }

        // Set colors of centroids to red
        ArrayList<Vertex> centroidsWithColors = new ArrayList<>();
        for(Vertex v: centroids){
            Property color = Property.newBuilder().setKey("rgb_color").setValue("255,0,0").build();
            Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
            verticesWithColors.add(colored);
        }

        return Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segments).addAllVertices(centroidsWithColors).build();
    }

}
