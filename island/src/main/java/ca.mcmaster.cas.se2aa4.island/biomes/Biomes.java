package ca.mcmaster.cas.se2aa4.island.biomes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;

import java.util.Arrays;
import java.util.List;

public class Biomes {
    public static String[] calculateBiomes(Structs.Mesh aMesh, Tiles newTile){
        int[] humidityValues = newTile.retrieveHumidity();
        int[] elevationValues = newTile.retrieveElevation();


        String[] biomeType = new String[humidityValues.length];


        for (int i = 0; i < humidityValues.length; i++) {
            //Calculating biomes based on elevation and humidity (WHITAKER DIAGRAM)

            if (humidityValues[i] == 50){
                if (elevationValues[i]==0){
                    biomeType[i] = "Ocean";
                }else{
                    biomeType[i] = "Mountain Ocean";
                }
            } else if (humidityValues[i] == 40) {
                if (elevationValues[i]==0){
                    biomeType[i] = "Lakes";
                }else{
                    biomeType[i] = "Mountain Lakes";
                }
            } else if (humidityValues[i] == 20){
                if (elevationValues[i]==0){
                    biomeType[i] = "Fields";
                }else{
                    biomeType[i] = "Highlands";
                }
            } else if (humidityValues[i] > 50) {
                if (elevationValues[i]==0){
                    biomeType[i] = "Wetlands";
                }else{
                    biomeType[i] = "Mountain Wetlands";
                }
            } else{
                biomeType[i] = "Desert";
            }
        }
        System.out.println("BIOMES FOR EACH TILE IN THE MESH");
        System.out.println(Arrays.toString(biomeType));
        return biomeType;

    }

    public static Structs.Mesh.Builder biomeGenerator(Structs.Mesh aMesh, String biome, Tiles newTile) {

        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        colors.Colors circle = new colors.Colors();

        for (int i = 0; i < aMesh.getPolygonsCount(); i++) {
            Structs.Polygon polygon = aMesh.getPolygons(i);
            Structs.Polygon.Builder polygonBuilder = Structs.Polygon.newBuilder(polygon);

            List<Structs.Property> propertiesList = polygon.getPropertiesList();
            for (int j = 0; j < propertiesList.size(); j++) {
                Structs.Property property = propertiesList.get(j);
                if (property.getKey().equals("rgb_color") && (property.getValue().equals(circle.LandColor)
                        || property.getValue().equals(circle.red)|| property.getValue().equals(circle.green))) {
                    if ("desert".equals(biome)){
                        Structs.Property.Builder propertyBuilder = Structs.Property.newBuilder(property);
                        propertyBuilder.setValue(circle.DesertColor);
                        polygonBuilder.setProperties(j, propertyBuilder.build());
                        newTile.setHumidity(i,10);
                    } else if ("tundra".equals(biome)) {
                        Structs.Property.Builder propertyBuilder = Structs.Property.newBuilder(property);
                        propertyBuilder.setValue(circle.TundraColor);
                        polygonBuilder.setProperties(j, propertyBuilder.build());
                        newTile.setHumidity(i,30);
                    }
                }
            }
            clone.addPolygons(polygonBuilder.build());
        }

        return clone;
    }
}

