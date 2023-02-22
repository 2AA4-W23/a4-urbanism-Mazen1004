package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class MeshNew extends DotGen {
    ArrayList<Structs.Polygon> polygons = new ArrayList<>();

    public MeshNew(){

        //There is 575 (24x24) Centroid Points assuming you start at 0
        for (int i=0; i<525 ;i++){
            //There is 1199 (24x25x2) Segments assuming you start at 0
            int v;
            Structs.Polygon p = Structs.Polygon.newBuilder().addSegmentIdxs(i).addSegmentIdxs(600+i+1).addSegmentIdxs(25+i).addSegmentIdxs(600+i).setCentroidIdx(i).build();
            System.out.println(p.toString());

        }
    }
}

