package ca.mcmaster.cas.se2aa4.island.Altitudes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Valley extends Altitude {
    public static Structs.Mesh.Builder valleyMesh(Structs.Mesh aMesh, int[] valleyArray) {

        String[] colorArray = colorArray("valley");

        return cloneBuilder(aMesh, colorArray, valleyArray);
    }
}
