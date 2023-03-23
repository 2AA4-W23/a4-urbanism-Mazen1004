package ca.mcmaster.cas.se2aa4.island.Shapes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;

import java.util.ArrayList;
import java.util.List;

public class Circle extends Shape {
    //Will be Implemented in Step 2

    //circle shape
    public static Structs.Mesh.Builder circle(Structs.Mesh aMesh, Tiles newTile) {

        List <Integer> landTiles = new ArrayList<>();

        List <Integer> landPolygon = new ArrayList<>();
        List <Integer> waterPolygon = new ArrayList<>();

        int polyIndex = 0;
        double radiusCircle = 400;

        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        colors.Colors circle = new colors.Colors();

        List<Structs.Polygon> polygonList = aMesh.getPolygonsList();

        for (int i = 0; i < polygonList.size(); i++) {

            Structs.Polygon polygonIndex = aMesh.getPolygons(i);
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(polygonIndex);

//            int centroidIndex = polygonIndex.getCentroidIdx();
//            System.out.println(centroidIndex); //list of all the centroids

            Structs.Vertex centroidVertices = aMesh.getVertices(polygonIndex.getCentroidIdx());
//            System.out.println(centroidVertices);


            //get the coordinates from the centroid
            double centroidX = centroidVertices.getX();
            double centroidY = centroidVertices.getY();

            double distance = distanceCalc(centerX, centerY, centroidX, centroidY);


            if (distance < radiusCircle) { //add the tile if the distance is less than the radius of the circle
                String color = circle.LandColor;
                Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                landTiles.add(polyIndex); //add index for land tile
                newTile.setHumidity(i,30);
                pc.addProperties(p);
            }

            else {
                String color = circle.OceanColor; //ocean color
                Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                pc.addProperties(p);
            }

            polyIndex++;
            clone.addPolygons(pc);
            //System.out.println(landTiles);
        }

        //beaches for the circle
        //basically if the int at the water tile .neighbour contains a land tile make that a beach

        polyIndex = 0;
        for(Structs.Polygon poly: aMesh.getPolygonsList()) { // for each polygon in the list

            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(poly); //just builds each polygon

            List <Integer> currentNeighbour = poly.getNeighborIdxsList(); //the neighbour of each polygon
//            System.out.println(polyIndex);
//            System.out.println(currentNeighbour);
//            System.out.println(landTiles);

            if (!landTiles.contains(polyIndex)) {
                for (Integer integer : currentNeighbour) {
                    if (landTiles.contains(integer)) {
                        String color = circle.BeachColor; // beach color
                        Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                        pc.addProperties(p);
                        clone.addPolygons(pc);

                    }
                }
            }
            polyIndex++;
        }


        return clone;
    }
}