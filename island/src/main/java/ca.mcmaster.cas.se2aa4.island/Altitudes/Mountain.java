package ca.mcmaster.cas.se2aa4.island.Altitudes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.islandGen.islandGen;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;
import org.apache.logging.log4j.core.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Mountain extends Altitude {
    public static Structs.Mesh.Builder mountainMesh(Structs.Mesh aMesh, int[] mountainArray) {

        String[] colorArray = colorArray("mountain");

        return cloneBuilder(aMesh, colorArray, mountainArray);
    }
}