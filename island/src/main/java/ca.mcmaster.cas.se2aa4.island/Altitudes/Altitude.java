package ca.mcmaster.cas.se2aa4.island.Altitudes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Altitude {
    protected static double canvasX = 1920; //hardcoded for now
    protected static double canvasY = 1080;
    protected static double centerX= canvasX/2;
    protected static double centerY= canvasY/2;

    public static double distanceCalc(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
    public static ArrayList<Integer> elevRadii() {
        ArrayList<Integer> elevRadii = new ArrayList<>(); // stores radius of all elevation circles

        int assignRadius=0;

        while (assignRadius < canvasX){
            elevRadii.add(assignRadius);
            assignRadius+=100;
        }
        // makes 0 the lowest (sea level) and the tallest point on the map is the highest value in elevRadii
        Collections.reverse(elevRadii);
        return elevRadii;
    }
    public static int[] mountain(Structs.Mesh aMesh){

        List<Structs.Polygon> polygonList = aMesh.getPolygonsList();
        int[] elevations = new int[polygonList.size()];

        for (int i=0; i<polygonList.size(); i++) {
            // finds the current polygon's centroid
            Structs.Polygon polygonInfo = aMesh.getPolygons(i);
            Structs.Vertex centroidVertices = aMesh.getVertices(polygonInfo.getCentroidIdx());

            // finds color of polygon
            List<Structs.Property> propertiesList = polygonInfo.getPropertiesList();
            colors.Colors mountain = new colors.Colors();


            // calculates distance from center of canvas to polygon centroid
            double distance = distanceCalc(centerX, centerY, centroidVertices.getX(), centroidVertices.getY());
            System.out.println(distance);

            for (int j=0; j<elevRadii().size(); j++){
                Structs.Property property = propertiesList.get(0);

                // checks if land can be attributed with altitude
                // e.g. only land and beach can have altitudes, not water.
                if (property.getKey().equals("rgb_color") && property.getValue().equals(mountain.LandColor) ||
                        property.getKey().equals("rgb_color") && property.getValue().equals(mountain.BeachColor)) {

                    // if distance from current polygon centroid > current elevation circle (radius), it belongs to that level
                    if (distance > elevRadii().get(j)) {
                        System.out.println(elevRadii().get(j));
                        elevations[i] = j;
                        break;
                        // else, continue through all elevation levels until found
                    } else {
                        elevations[i] = 0;
                    }
                }
            }
            System.out.println(elevRadii());
        }

        System.out.println(Arrays.toString(elevations));
        return elevations;
    }
}
