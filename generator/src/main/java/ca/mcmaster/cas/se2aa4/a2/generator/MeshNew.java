package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class MeshNew extends DotGen {
    ArrayList<Structs.Polygon> polygons = new ArrayList<>();

    public MeshNew(){
        //There is 575 (24x24) Centroid Points assuming you start at 0
        for (int c=0; c<575 ;c++){
            //There is 1199 (24x25x2) Segments assuming you start at 0
            Structs.Polygon p = Structs.Polygon.newBuilder().setCentroidIdx(c).addSegmentIdxs(c).addSegmentIdxs(600+c+25).addSegmentIdxs(c+25).addSegmentIdxs(600+c).build();
            System.out.println(p.toString());
        }
    }
}

