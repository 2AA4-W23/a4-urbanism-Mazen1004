package ca.mcmaster.cas.se2aa4.island.SoilAbsorbtion;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;

import java.util.Arrays;
import java.util.Objects;

public abstract class soilAbsorption {
    public static void modifyHumidity(String input, Tiles newTile) {
        int[] humidityValues = newTile.retrieveHumidity();

        if (Objects.equals(input, "drySoil")){
            for (int i=0; i<humidityValues.length; i++) {
                newTile.addHumidity(i, -30);
            }
        }else if (Objects.equals(input, "wetSoil")){
            for (int i=0; i<humidityValues.length; i++) {
                newTile.addHumidity(i, 30);
            }
        }
    }
}
