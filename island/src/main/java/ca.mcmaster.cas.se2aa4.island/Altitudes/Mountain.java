package ca.mcmaster.cas.se2aa4.island.Altitudes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Mountain extends Altitude {
    public static Structs.Mesh.Builder mountainMesh(Structs.Mesh aMesh, int[] mountainArray) {

        String[] colorArray = colorArray("mountain");

        return cloneBuilder(aMesh, colorArray, mountainArray);
    }
}