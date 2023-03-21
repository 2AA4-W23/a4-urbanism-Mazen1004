package ca.mcmaster.cas.se2aa4.island.Altitudes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mountain extends Altitude {
    public static int[] mountain(Structs.Mesh aMesh){
        ArrayList<Integer> elevRadii = new ArrayList<>(); // stores radius of all elevation circles

        int assignRadius=200;

        while (assignRadius < canvasX){
            elevRadii.add(assignRadius);
            assignRadius+=200;
        }

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

            for (int j=0; i<elevRadii.size(); i++){
                Structs.Property property = propertiesList.get(j);

                if (property.getKey().equals("rgb_color") && property.getValue().equals(mountain.LandColor) ||
                        property.getKey().equals("rgb_color") && property.getValue().equals(mountain.BeachColor)) {
                    if (distance > j+1){
                        elevations[i] = elevRadii.indexOf(j);
                        break;
                    }
                }
            }
        }
        // make arraylist with every single circle radius
        // for i in number of polygons
            // for j in cirleradii
                // if i>j+1, add elevation[i] = j

        // check the current tile's color:
            // if water, don't repeat the wile loop.
        // circles of 200 radius starting form center of shape
            // 200 tip, 400 second, 600 third
        // continues until hits sea level. will not by perfect increments of 200 so when

        System.out.println(Arrays.toString(elevations));
        return elevations;
    }
}
