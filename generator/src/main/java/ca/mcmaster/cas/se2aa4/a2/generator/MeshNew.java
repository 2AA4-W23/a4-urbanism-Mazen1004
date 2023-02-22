package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class MeshNew extends DotGen {
    ArrayList<Structs.Polygon> polygons = new ArrayList<>();
    ArrayList<Integer> numOfCentroids = new ArrayList<>();
    ArrayList<Integer> neighborIndicies = new ArrayList<Integer>();


    public MeshNew(){
        for (int i=0; i<575; i++){
            numOfCentroids.add(i);
        }

        //There is 575 (24x24) Centroid Points assuming you start at 0
        for (int c=0; c<575; c++){
            neighborIndicies.clear();

            if (numOfCentroids.contains(c-1)) {
                neighborIndicies.add(c-1);
                System.out.println("test");
            }
            if (numOfCentroids.contains(c-25)) {
                neighborIndicies.add(c-25);
                System.out.println("test2");
            }
            if (numOfCentroids.contains(c+1)) {
                neighborIndicies.add(c+1);
                System.out.println("test3");
            }
            if (numOfCentroids.contains(c+25)) {
                neighborIndicies.add(c+25);
                System.out.println("test4");
            }
            System.out.println(neighborIndicies.toString());
            //There is 1199 (24x25x2) Segments assuming you start at 0
            Structs.Polygon p = Structs.Polygon.newBuilder().setCentroidIdx(c).addSegmentIdxs(c).addSegmentIdxs(600+c+25).addSegmentIdxs(c+25).addSegmentIdxs(600+c).addAllNeighborIdxs(neighborIndicies).build();
            System.out.println(p.toString());
        }
    }
}

