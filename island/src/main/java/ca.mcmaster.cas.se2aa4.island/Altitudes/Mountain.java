package ca.mcmaster.cas.se2aa4.island.Altitudes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.islandGen.islandGen;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Mountain extends Altitude {
    public static ArrayList<Integer> elevRadii() {
        ArrayList<Integer> elevRadii = new ArrayList<>(); // stores radius of all elevation circles

        int assignRadius=100;

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
    public static Structs.Mesh.Builder mountainMesh(Structs.Mesh aMesh, int[] mountainArray) {
        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        colors.Colors mountain = new colors.Colors();

        String[] colorArray = new String[elevRadii().size()];

        // Define the green and red colors as Strings with comma-separated RGB values
        String green = "0,225,0";
        String red = "225,0,0";
        String blue = "0,0,225";

        // Determine the color for each layer of elevation. i starts at 1 since 0 in the array is sea-level, thus blue.
        for (int i = 1; i < elevRadii().size(); i++) {
            // Calculate the color value between green and red based on the element's position in the array
            float ratio = (float) i / (float) (elevRadii().size() - 1);
            String[] greenValues = green.split(",");
            String[] redValues = red.split(",");
            int redValue = (int) (ratio * Integer.parseInt(redValues[0]) + (1 - ratio) * Integer.parseInt(greenValues[0]));
            int greenValue = (int) (ratio * Integer.parseInt(redValues[1]) + (1 - ratio) * Integer.parseInt(greenValues[1]));
            int blueValue = (int) (ratio * Integer.parseInt(redValues[2]) + (1 - ratio) * Integer.parseInt(greenValues[2]));

            // Create the color object for the element and add it to the colorArray
            String color = redValue+","+greenValue+","+blueValue;
            colorArray[i] = color;
        }
        colorArray[0] = blue;

        System.out.println(Arrays.toString(colorArray));


        for (int i=0; i<mountainArray.length; i++) {
            Structs.Polygon polygon = aMesh.getPolygons(i);
            Structs.Polygon.Builder polygonBuilder = Structs.Polygon.newBuilder(polygon);

            List<Structs.Property> propertiesList = polygon.getPropertiesList();
            for (int j = 0; j < propertiesList.size(); j++) {
                Structs.Property property = propertiesList.get(j);

                if (property.getKey().equals("rgb_color")) {
                    for (int k=0; k<elevRadii().size(); k++) {
                        if (mountainArray[i] == k){
                            Structs.Property.Builder propertyBuilder = Structs.Property.newBuilder(property);
                            propertyBuilder.setValue(colorArray[k]);
                            polygonBuilder.setProperties(j, propertyBuilder.build());
                        }
                    }
                }
            }
            clone.addPolygons(polygonBuilder.build());
        }
        return clone;
    }
}