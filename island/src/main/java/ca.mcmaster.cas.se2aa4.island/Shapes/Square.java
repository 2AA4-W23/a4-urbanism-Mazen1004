package ca.mcmaster.cas.se2aa4.island.Shapes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class Square extends Shape{
    public static Structs.Mesh.Builder square(Structs.Mesh aMesh) {

        List <Integer> waterNeighbour = new ArrayList<>();
        int polyIndex = 0;
        //basic objects to create a shape
        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        colors.Colors square = new colors.Colors();


        List<Structs.Polygon> polygonList = aMesh.getPolygonsList();
        for (int i = 0; i < polygonList.size(); i++) {

            Structs.Polygon polygonIndex = aMesh.getPolygons(i);
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(polygonIndex);
            Structs.Vertex centroidVertices = aMesh.getVertices(polygonIndex.getCentroidIdx());
            double centroidX = centroidVertices.getX();
            double centroidY = centroidVertices.getY();
            double squareSize = 300;

            if ((centroidX > centerX - squareSize) && (centroidX < centerX + squareSize) && (centroidY > centerY - squareSize) && (centroidY < centerY +squareSize)) { //add the tile if the distance is less than the radius of the circle
                String color = square.LandColor;
                Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                pc.addProperties(p);
                waterNeighbour.add(polyIndex); //add index for land tile
            }
            else {
                String color = square.OceanColor; //ocean color
                Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                pc.addProperties(p);
            }
            clone.addPolygons(pc);
            polyIndex++;
        }

        polyIndex = 0;
        for(Structs.Polygon poly: aMesh.getPolygonsList()) { // for each polygon in the list

            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(poly); //just builds each polygon

            List <Integer> currentNeighbour = poly.getNeighborIdxsList(); //the neighbour of each polygon
//            System.out.println(polyIndex);
//            System.out.println(currentNeighbour);
//            System.out.println(waterNeighbour);

            if (!waterNeighbour.contains(polyIndex)) {
                for (Integer integer : currentNeighbour) {
                    if (waterNeighbour.contains(integer)) {
                        String color = square.BeachColor; // beach color
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
