package ca.mcmaster.cas.se2aa4.island.Shapes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;
import colors.Colors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lagoon extends Shape{
    public static Structs.Mesh.Builder lagoon(Structs.Mesh aMesh, Tiles newTile){

        //radius of how big we want the lagoon or land island to be
        double radiusLagoon = 200;
        double radiusLand = 500;

        //Accessing Colors Class
        Colors Lagoon = new Colors();

        List<Structs.Polygon> polygonList = aMesh.getPolygonsList();

        List <Integer> waterNeighbour = new ArrayList<>();

        ArrayList<Integer> tilesLagoon = new ArrayList<>();
        ArrayList<Integer> tilesLand = new ArrayList<>();
        ArrayList<Integer> tilesOcean = new ArrayList<>();

        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        int polyIndex = 0;

        for (int i = 0; i < polygonList.size(); i++) {

            Structs.Polygon polygonIndex = aMesh.getPolygons(i);
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(polygonIndex);

            int centroidIndex = polygonIndex.getCentroidIdx();
//            System.out.println(centroidIndex); //list of all the centroids

            Structs.Vertex centroidVertices = aMesh.getVertices(polygonIndex.getCentroidIdx());
//            System.out.println(centroidVertices);



            //get the coordinates from the centroid
            double centroidX = centroidVertices.getX();
            double centroidY = centroidVertices.getY();

            double distance = distanceCalc(centerX, centerY, centroidX, centroidY);


            if (distance < radiusLagoon) { //add the tile if the distance is less than the radius of the lagoon
                tilesLagoon.add(centroidIndex);
                String color=Lagoon.LagoonSeaColor;
                Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                pc.addProperties(p);
                waterNeighbour.add(polyIndex);
            }
            else if ((distance > radiusLagoon) && (distance < radiusLand)) { // the land area in between ocean and lagoon
                tilesLand.add(centroidIndex);
                String color = Lagoon.LandColor; //land color
                Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                newTile.setHumidity(i,30);
                pc.addProperties(p);

            }
            else {
                tilesOcean.add(centroidIndex);
                String color = Lagoon.OceanColor; //ocean color
                Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                pc.addProperties(p);
                waterNeighbour.add(polyIndex);

            }
            polyIndex++;
            clone.addPolygons(pc);
        }

        // beaches baby
        polyIndex = 0;
        for(Structs.Polygon poly: aMesh.getPolygonsList()) {
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(poly);

            List <Integer> currentNeighbour = poly.getNeighborIdxsList();
            if (!waterNeighbour.contains(polyIndex)) {
                for (Integer integer : currentNeighbour) {
                    if (waterNeighbour.contains(integer)) {
                        String color = Lagoon.BeachColor; // beach color
                        Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                        pc.addProperties(p);
                        clone.addPolygons(pc);
                    }
                }
            }
            polyIndex++;
        }
        //returns clone mesh
        return clone;
    }

}
