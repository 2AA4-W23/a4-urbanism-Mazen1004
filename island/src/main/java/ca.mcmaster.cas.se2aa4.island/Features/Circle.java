package ca.mcmaster.cas.se2aa4.island.Features;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;

public class Circle extends Shape {
    //Will be Implemented in Step 2

    //circle shape
    public static Structs.Mesh.Builder circle(Structs.Mesh aMesh) {
        double radiusLagoon = 200;
        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        colors.Colors circle = new colors.Colors();

        List<Structs.Polygon> polygonList = aMesh.getPolygonsList();

        for (int i = 0; i < polygonList.size(); i++) {

            Structs.Polygon polygonIndex = aMesh.getPolygons(i);
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(polygonIndex);

            int centroidIndex = polygonIndex.getCentroidIdx();
            System.out.println(centroidIndex); //list of all the centroids

            Structs.Vertex centroidVertices = aMesh.getVertices(polygonIndex.getCentroidIdx());
            System.out.println(centroidVertices);


            //get the coordinates from the centroid
            double centroidX = centroidVertices.getX();
            double centroidY = centroidVertices.getY();

            double distance = distanceCalc(centerX, centerY, centroidX, centroidY);


            if (distance < radiusLagoon) { //add the tile if the distance is less than the radius of the lagoon
                String color= circle.LandColor;
                Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                pc.addProperties(p);
            }

            else {
                String color = circle.OceanColor; //ocean color
                Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                pc.addProperties(p);
            }
            clone.addPolygons(pc);
        }

        //need beaches

        return clone;
    }
}