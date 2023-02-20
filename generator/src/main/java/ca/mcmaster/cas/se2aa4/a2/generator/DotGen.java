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

    private final int width = 1000;
    private final int height = 1000;
    private final int square_size = 20;

    public Mesh generate() {
        int numOfVertices = 0;

        ArrayList<Vertex> vertices = new ArrayList<>();
        // Create all the vertices
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
        ArrayList<Segment> segments = new ArrayList<>();

        for (int j=0; j <= numOfVertices - 2; j+=2){
                segments.add(Segment.newBuilder().setV1Idx(j).setV2Idx(j + 1).build());
        }
        for (int i=0; i <= numOfVertices - 3; i+=2) {
            // Checks to see if the next top left value in the square segment is in a new square column.
            // Eliminate lines being drawn diagonally from the last value in the column to the first value in the next column.
            if ((i + 2) % 100 != 0) {
                segments.add(Segment.newBuilder().setV1Idx(i).setV2Idx(i + 2).build());
            }
            // Draws segments for the last column in the mesh
            if ((i % 2400 < 100) && i > 100 ) {
                segments.add(Segment.newBuilder().setV1Idx(i + 1).setV2Idx(i + 3).build());
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

        return Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segments).build();
    }

}
