package ca.mcmaster.cas.se2aa4.island.rivers;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Rivers {
    public static Structs.Mesh.Builder river(Structs.Mesh aMesh, int riverCount) {

        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());
        clone.addAllPolygons(aMesh.getPolygonsList());

        colors.Colors rivers = new colors.Colors();

        for (int i = 0; i < riverCount; i++) {

            //get each polygon
            Structs.Polygon polygonIndex = aMesh.getPolygons(i);


            //this is just the builder for each polygon
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(polygonIndex);

            //get the segment indices of each polygon
            int segmentIndex = polygonIndex.getSegmentIdxs(i);

            // want the segment itself from the polygon


            //you want to color segmentIndex.
            Structs.Segment segment = aMesh.getSegments(segmentIndex);


            Structs.Segment.Builder segmentBuilder = Structs.Segment.newBuilder(segment);
            String color = rivers.LandColor;

            Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();

            segmentBuilder.addProperties(p);

            clone.addSegments(segmentBuilder.build());
            clone.addPolygons(pc.build());
//            System.out.println(segmentBuilder);
        }
        return clone;
    }
}