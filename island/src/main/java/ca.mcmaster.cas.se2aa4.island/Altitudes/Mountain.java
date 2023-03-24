package ca.mcmaster.cas.se2aa4.island.Altitudes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.islandGen.islandGen;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Mountain extends Altitude {
    public static Structs.Mesh.Builder mountainMesh(Structs.Mesh aMesh, int[] mountainArray) {
        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        colors.Colors mountain = new colors.Colors();

        ArrayList<String> colorArray = new ArrayList<>(elevRadii().size());

        // Determine the color for each layer of elevation. i starts at 1 since 0 in the array is sea-level, thus blue.
        for (int i = 1; i < elevRadii().size(); i++) {
            // Calculate the color value between green and red based on the element's position in the array
            float ratio = (float) i / (float) (elevRadii().size() - 1);
            String[] greenValues = mountain.green.split(",");
            String[] redValues = mountain.red.split(",");
            int redValue = (int) (ratio * Integer.parseInt(redValues[0]) + (1 - ratio) * Integer.parseInt(greenValues[0]));
            int greenValue = (int) (ratio * Integer.parseInt(redValues[1]) + (1 - ratio) * Integer.parseInt(greenValues[1]));
            int blueValue = (int) (ratio * Integer.parseInt(redValues[2]) + (1 - ratio) * Integer.parseInt(greenValues[2]));

            // Create the color object for the element and add it to the colorArray
            String color = redValue+","+greenValue+","+blueValue;
            colorArray.set(i, color);
        }
        // First index is 0, thus sea level
        colorArray.set(0, mountain.OceanColor);
        Collections.reverse(colorArray);
        System.out.println(colorArray);


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
                            propertyBuilder.setValue(colorArray.get(k));
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