package ca.mcmaster.cas.se2aa4.island.tiles;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.Features.Lagoon;
import ca.mcmaster.cas.se2aa4.island.Configuration.*;

public class Tiles {
    public static Structs.Mesh tile(Structs.Mesh aMesh, String mode){

        Structs.Mesh outputMesh = aMesh;


        if (mode.equals("lagoon")) {
            //Takes aMesh into Lagoon
            outputMesh = Lagoon.lagoon(aMesh).build();
        }
        else { // no mode is given
            return outputMesh;
        }

        return outputMesh;
    }
}
