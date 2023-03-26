package ca.mcmaster.cas.se2aa4.island.tiles;

import java.util.Arrays;

public class Tiles {
    //Tiles stores our humidity and elevation values for our Island
    //Allows easy referencing in our other classes
    private int[] humidityValues;
    private int[] elevationValues;

    //Some Humidity Assumptions:
    // Base Humidity is 50, this is humidity for oceans
    // Island Humidity is 20, includes anywhere in the island
    // If lake generated humidity is set to 40
    // Generating Aquifers increases humidity of a tile by +30
    // E.g a tile on the island that is a lake and has an aquifer would have humidity 40+30=70

    //HUMIDITY FUNCTIONS
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
        System.out.println("MOST RECENT HUMIDITY FOR EACH TILE IN MESH");
        System.out.println(Arrays.toString(humidityValues));
        System.out.println("---------------------------------------------------------------------------");
        return humidityValues;
    }

    public void setHumidity(int polygonIndex, int value) {
        humidityValues[polygonIndex] = value;
    }
    public void addHumidity(int polygonIndex, int value) {
        humidityValues[polygonIndex]=humidityValues[polygonIndex]+value;
    }

    //Some Elevation Assumptions:
    // Base Elevation is 0, if not altitude is defined
    // If altitude is defined it is extracted from the altitude functions

    //ELEVATION FUNCTIONS
    public void setBaseElevation(int numPolygons) {
        elevationValues = new int[numPolygons];
        Arrays.fill(elevationValues, 0);
    }
    //Update Elevation function extracts elevation from altitudes class if mountain or valley generated
    public int[] updateElevation(int[] array){
        //System.out.println(Arrays.toString(elevationValues));
        elevationValues = array;
        return elevationValues;
    }
    public int[] retrieveElevation(){
        System.out.println("ELEVATION FOR EACH TILE IN MESH");
        System.out.println(Arrays.toString(elevationValues));
        System.out.println("---------------------------------------------------------------------------");
        return elevationValues;
    }
    public int getElevation(int polygonIndex) {
        return elevationValues[polygonIndex];
    }
}
