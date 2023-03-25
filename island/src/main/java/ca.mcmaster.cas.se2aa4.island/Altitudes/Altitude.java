package ca.mcmaster.cas.se2aa4.island.Altitudes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.*;

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
            colors.Colors color = new colors.Colors();


            // calculates distance from center of canvas to polygon centroid
            double distance = distanceCalc(centerX, centerY, centroidVertices.getX(), centroidVertices.getY());
            //System.out.println(distance);

            for (int j=0; j<elevRadii().size(); j++){
                Structs.Property property = propertiesList.get(0);

                // checks if land can be attributed with altitude
                // e.g. only land and beach can have altitudes, not water.
                if (property.getKey().equals("rgb_color") && property.getValue().equals(color.LandColor) ||
                        property.getKey().equals("rgb_color") && property.getValue().equals(color.BeachColor)) {

                    // if distance from current polygon centroid > current elevation circle (radius), it belongs to that level
                    if (distance > elevRadii().get(j)) {
                        //System.out.println(elevRadii().get(j));
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
        //System.out.println("testing elevation");
        //System.out.println(Arrays.toString(elevations));
        return elevations;
    }

    public static String[] colorArray(String type){
        colors.Colors color = new colors.Colors();

        String[] colorArray = new String[elevRadii().size()];

        // Determine the color for each layer of elevation. i starts at 1 since 0 in the array is sea-level, thus blue.
        for (int i = 1; i < elevRadii().size(); i++) {
            // Calculate the color value between green and red based on the element's position in the array
            float ratio = (float) i / (float) (elevRadii().size() - 1);
            String[] greenValues = color.green.split(",");
            String[] redValues = color.red.split(",");
            int redValue = (int) (ratio * Integer.parseInt(redValues[0]) + (1 - ratio) * Integer.parseInt(greenValues[0]));
            int greenValue = (int) (ratio * Integer.parseInt(redValues[1]) + (1 - ratio) * Integer.parseInt(greenValues[1]));
            int blueValue = (int) (ratio * Integer.parseInt(redValues[2]) + (1 - ratio) * Integer.parseInt(greenValues[2]));

            // Create the color object for the element and add it to the colorArray
            String colorValue = redValue+","+greenValue+","+blueValue;
            colorArray[i] = colorValue;
        }

        // Colors elevation based on altitude type
        if (Objects.equals(type, "mountain")) {
            // First index is 0, thus colored sea level
            colorArray[0] = color.OceanColor;

        }
        else if (Objects.equals(type, "valley")) {
            colorArray[0] = color.green;
            //System.out.println(Arrays.toString(colorArray));

            colorArray = reverseArray(colorArray);
            // First index is 0, thus colored sea level
            colorArray[0] = color.OceanColor;
            //System.out.println(Arrays.toString(colorArray));
        }

        return colorArray;
    }

    public static Structs.Mesh.Builder cloneBuilder(Structs.Mesh aMesh, String[] colorArray, int[] array) {
        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        for (int i=0; i<array.length; i++) {
            Structs.Polygon polygon = aMesh.getPolygons(i);
            Structs.Polygon.Builder polygonBuilder = Structs.Polygon.newBuilder(polygon);

            List<Structs.Property> propertiesList = polygon.getPropertiesList();
            for (int j = 0; j < propertiesList.size(); j++) {
                Structs.Property property = propertiesList.get(j);

                if (property.getKey().equals("rgb_color")) {
                    for (int k=0; k<elevRadii().size(); k++) {
                        if (array[i] == k){
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

    // Function to reverse an array
    public static String[] reverseArray(String[] array) {
        List<String> arrayList = Arrays.asList(array);
        Collections.reverse(arrayList);
        return arrayList.toArray(array);
    }
}
