package ca.mcmaster.cas.se2aa4.island.tiles;

import java.util.Arrays;

public class Tiles {
    private int[] humidityValues;

    //Some Humidity Assumptions:
    // Base Humidity is 50, this is humidity for oceans
    // Island Humidity is 20, includes anywhere in the island
    // If lake generated humidity is set to 50
    // Generating Aquifers increases humidity of a tile by +30
    // E.g a tile on the island that is a lake and has an aquifer would have humidity 50+30=80

    //Use Similar Logic for Elevation

    private int[] elevationValues; //do same thing for elevation

    public void setBaseHumidity(int numPolygons) {
        humidityValues = new int[numPolygons];
        Arrays.fill(humidityValues, 50); // initialize all values to 50
        //System.out.println("testing");
        //System.out.println(Arrays.toString(humidityValues));
    }

    public int getHumidity(int polygonIndex) {
        return humidityValues[polygonIndex];
    }
    public int[] retrieveHumidity(){
        System.out.println(Arrays.toString(humidityValues));
        return humidityValues;
    }

    public void setHumidity(int polygonIndex, int value) {
        humidityValues[polygonIndex] = value;
    }

}
