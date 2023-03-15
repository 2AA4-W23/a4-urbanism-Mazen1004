import Configuration.*;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration(args);
        Structs.Mesh aMesh = new MeshFactory().read(config.input()); //input mesh from the command lines


        // calculate the distance of each centroid from the center
        final double centerX = 960; //width of canvas 1920 divided by 2
        final double centerY = 540; //height of canvas 1080 divided by 2

        //radius of how big we want the lagoon or land island to be
        double radiusLagoon = 200;
        double radiusLand = 500;

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
            System.out.println(centroidIndex); //list of all the centroids

            Structs.Vertex centroidVertices = aMesh.getVertices(polygonIndex.getCentroidIdx());
            System.out.println(centroidVertices);


            //get the coordinates from the centroid
            double centroidX = centroidVertices.getX();
            double centroidY = centroidVertices.getY();

            double distance = distanceCalc(centerX, centerY, centroidX, centroidY);


            if (distance < radiusLagoon) { //add the tile if the distance is less than the radius of the lagoon
                tilesLagoon.add(centroidIndex);
                String color = 42 + ","+ 122 + ","+ 219; //lagoon color
                Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                pc.addProperties(p);
                waterNeighbour.add(polyIndex);
            }
            else if ((distance > radiusLagoon) && (distance < radiusLand)) { // the land area in between ocean and lagoon
                tilesLand.add(centroidIndex);
                String color = 42 + ","+ 171 + ","+ 42; //land color
                Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                pc.addProperties(p);
            }
            else {
                tilesOcean.add(centroidIndex);
                String color = 0 + ","+ 0 + ","+ 139; //ocean color
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
                        String color = 242 + "," + 210 + "," + 169; // beach color
                        Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                        pc.addProperties(p);
                        clone.addPolygons(pc);
                    }
                }
            }
            polyIndex++;
        }


        new MeshFactory().write(clone.build(), config.output()); //output mesh

        //System.out.println(tilesLagoon);
        //System.out.println(tilesLand);

    }
    public static double distanceCalc(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}
