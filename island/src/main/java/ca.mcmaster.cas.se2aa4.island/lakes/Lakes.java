package ca.mcmaster.cas.se2aa4.island.lakes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.islandGen.islandGen;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Lakes {
    public static Structs.Mesh.Builder lake(Structs.Mesh aMesh, int lakeCount, Tiles newTile) {

        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        colors.Colors circle = new colors.Colors();

        //Loop to count how many polygons are land
        int landCount = 0;
        for (int i = 0; i < aMesh.getPolygonsCount(); i++) {
            Structs.Polygon polygon = aMesh.getPolygons(i);
            List<Structs.Property> propertiesList = polygon.getPropertiesList();
            for (int j = 0; j < propertiesList.size(); j++) {
                Structs.Property property = propertiesList.get(j);
                if (property.getKey().equals("rgb_color") && property.getValue().equals(circle.LandColor)) {
                    landCount++;
                }
            }
        }

        //Randomly Generates lakes tiles based on number of Lakes
        int[] randomLakes = new int[lakeCount];
        Random rand = new Random();

        for (int i = 0; i < lakeCount; i++) {
            int randomIndex = rand.nextInt(landCount);
            randomLakes[i] = randomIndex;
        }
        System.out.println(Arrays.toString(randomLakes));

        int landCount1=0;
        for (int i = 0; i < aMesh.getPolygonsCount(); i++) {
            Structs.Polygon polygon = aMesh.getPolygons(i);
            Structs.Polygon.Builder polygonBuilder = Structs.Polygon.newBuilder(polygon);

            List<Structs.Property> propertiesList = polygon.getPropertiesList();
            for (int j = 0; j < propertiesList.size(); j++) {
                Structs.Property property = propertiesList.get(j);
                if (property.getKey().equals("rgb_color") && property.getValue().equals(circle.LandColor)) {
                    landCount1++;
                    for (int k = 0; k < randomLakes.length; k++) {
                        if (randomLakes[k] == landCount1) {
                            Structs.Property.Builder propertyBuilder = Structs.Property.newBuilder(property);
                            propertyBuilder.setValue(circle.LagoonSeaColor);
                            polygonBuilder.setProperties(j, propertyBuilder.build());
                            //Updating Humidity Value since polygon is a lake now
                            newTile.setHumidity(i,40);
                        }
                    }
                }
            }
            clone.addPolygons(polygonBuilder.build());
        }

        return clone;
    }
}