package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class MeshNew extends DotGen {
    ArrayList<Structs.Polygon> polygons = new ArrayList<>();

    public MeshNew(){

        //There is 575 (24x24) Centroid Points assuming you start at 0
        for (int c=0; c<10 ;c++){
            //There is 1199 (24x25x2) Segments assuming you start at 0
            int v;
            for (v=0;v<1199;v++){
            }
            Structs.Polygon p = Structs.Polygon.newBuilder().addSegmentIdxs(v).addSegmentIdxs(v+1).addSegmentIdxs(v+25).addSegmentIdxs(v+26).setCentroidIdx(c).build();
            System.out.println(p.toString());

        }
    }
}

