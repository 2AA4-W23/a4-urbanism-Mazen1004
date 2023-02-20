package ca.mcmaster.cas.se2aa4.a2.generator;
import org.locationtech.jts.algorithm;

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

                int centroidX= (((x + x + square_size)/ 2) + ((x + x + square_size)/ 2))/2 ;
                int centroidY = (((y + y + square_size)/ 2) + ((y + y + square_size)/ 2))/2 ;
                vertices.add(Vertex.newBuilder().setX((double) centroidX).setY((double) centroidY).build());

                //third dot above first
                vertices.add(Vertex.newBuilder().setX((double) x + square_size).setY((double) y + square_size).build());
                // (0,0) (20,0) (0,20) (20,20)
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y + square_size).build());

                //x+y slanted 45 deg
                //   0      1     2       3
                numOfVertices+=4;
            }
        }

        ArrayList<Segment> segments = new ArrayList<>();

        //horizontal line
        for (int j=0; j <= numOfVertices - 1; j++){
              segments.add(Segment.newBuilder().setV1Idx(j).setV2Idx(j + 1).build());
        }

        for (int i = 1; i < 100; i += 2 ) {
            for (int j=0; j < 2400; j += 100) {
                segments.add(Segment.newBuilder().setV1Idx(j+i).setV2Idx(j+i + 100).build());
            }
        }

        //can make nested if you really want
        for (int j=0; j <= numOfVertices - 4; j+=2){
            if ((j + 2) % 100 != 0)  {
                segments.add(Segment.newBuilder().setV1Idx(j).setV2Idx(j + 2).build());
            }

        }
        int columnCounter = 0;
        for (int j=1; j <= numOfVertices - 4; j+=2){
            if ((j + 2) % (columnCounter*100 + 1) != 0) {
                segments.add(Segment.newBuilder().setV1Idx(j).setV2Idx(j + 2).build());
            }
            else {
                columnCounter++;
            }
        }



//        for (int i=0; i <= numOfVertices - 3; i++) {
//            if (i % 2 == 0) {
//                segments.add(Segment.newBuilder().setV1Idx(i).setV2Idx(i + 2).build());
//            }
//        }
//        for (int i=1; i <= numOfVertices - 3; i++) {
//            if (i % 1 != 0) {
//                segments.add(Segment.newBuilder().setV1Idx(i).setV2Idx(i + 2).build());
//            }

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
