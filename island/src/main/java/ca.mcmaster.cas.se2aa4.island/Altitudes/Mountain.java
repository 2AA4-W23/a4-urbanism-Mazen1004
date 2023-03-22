package ca.mcmaster.cas.se2aa4.island.Altitudes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Mountain extends Altitude {
    public static int[] mountain(Structs.Mesh aMesh){
        ArrayList<Integer> elevRadii = new ArrayList<>(); // stores radius of all elevation circles

        int assignRadius=100;

        while (assignRadius < canvasX){
            elevRadii.add(assignRadius);
            assignRadius+=100;
        }
        // makes 0 the lowest (sea level) and the tallest point on the map is the highest value in elevRadii
        Collections.reverse(elevRadii);

        System.out.println(elevRadii);



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

            for (int j=0; j<elevRadii.size(); j++){
                Structs.Property property = propertiesList.get(0);

                // checks if land can be attributed with altitude
                // e.g. only land and beach can have altitudes, not water.
                if (property.getKey().equals("rgb_color") && property.getValue().equals(mountain.LandColor) ||
                        property.getKey().equals("rgb_color") && property.getValue().equals(mountain.BeachColor)) {

                    // if distance from current polygon centroid > current elevation circle (radius), it belongs to that level
                    if (distance > elevRadii.get(j)) {
                        System.out.println(elevRadii.get(j));
                        elevations[i] = j;
                        break;
                        // else, continue through all elevation levels until found
                    } else {
                        elevations[i] = 0;
                    }
                }
            }
            System.out.println(elevRadii);
        }

        System.out.println(Arrays.toString(elevations));
        return elevations;
    }
}