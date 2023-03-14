import Configuration.*;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration(args);
        Structs.Mesh aMesh = new MeshFactory().read(config.input());

        // calculate the distance of each centroid from the center
        final double centerX = 960; //weight of canvas 1920 / 2
        final double centerY = 540; //height of canvas 1080 / 2

        //radius of how big we want the lagoon or land island to be
        double radiusLagoon = 10;
        double radiusLand = 50;

        List<Structs.Polygon> polygonList = aMesh.getPolygonsList();

        for (int i = 0; i < polygonList.size(); i++) {
            Structs.Polygon polygonIndex = aMesh.getPolygons(i);
            int centroidIndex = polygonIndex.getCentroidIdx();
            System.out.println(centroidIndex); //list of all the centroids

            Structs.Vertex centroidVertices = aMesh.getVertices(polygonIndex.getCentroidIdx());
            System.out.println(centroidVertices);

            //get the coordinates from the centroid
            double centroidX = centroidVertices.getX();
            double centroidY = centroidVertices.getY();

            double distanceLagoon = distanceCalc(centerX, centerY, centroidX, centroidY);
            ArrayList<Integer> tilesLagoon = new ArrayList<>();
            if (distanceLagoon < radiusLagoon) { //add the tile if the distance is less than the radius
                tilesLagoon.add(centroidIndex);
            }
            System.out.println(tilesLagoon);
        }



        new MeshFactory().write(aMesh, config.output());
    }
    public static double distanceCalc(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}
