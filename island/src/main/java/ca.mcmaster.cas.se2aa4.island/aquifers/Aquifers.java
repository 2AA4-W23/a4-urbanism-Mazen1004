package ca.mcmaster.cas.se2aa4.island.aquifers;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.islandGen.islandGen;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Aquifers {
    public static Structs.Mesh.Builder aquifer(Structs.Mesh aMesh, int aquiferCount, Tiles newTile) {

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
        int[] randomAquifers = new int[aquiferCount];
        Random rand = new Random();

        for (int i = 0; i < aquiferCount; i++) {
            int randomIndex = rand.nextInt(landCount);
            randomAquifers[i] = randomIndex;
        }
        //System.out.println(Arrays.toString(randomAquifers));

        int landCount1=0;
        for (int i = 0; i < aMesh.getPolygonsCount(); i++) {
            Structs.Polygon polygon = aMesh.getPolygons(i);
            Structs.Polygon.Builder polygonBuilder = Structs.Polygon.newBuilder(polygon);

            List<Structs.Property> propertiesList = polygon.getPropertiesList();
            for (int j = 0; j < propertiesList.size(); j++) {
                Structs.Property property = propertiesList.get(j);
                if (property.getKey().equals("rgb_color") && property.getValue().equals(circle.LandColor)) {
                    landCount1++;
                    for (int k = 0; k < randomAquifers.length; k++) {
                        if (randomAquifers[k] == landCount1) {
                            //Updating Humidity Value since polygon has an aquifer now
                            newTile.addHumidity(i,30);
                        }
                    }
                }
            }
            clone.addPolygons(polygonBuilder.build());
        }
        return clone;
    }
}