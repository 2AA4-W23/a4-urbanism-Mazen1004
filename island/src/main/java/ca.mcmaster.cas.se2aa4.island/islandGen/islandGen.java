package ca.mcmaster.cas.se2aa4.island.islandGen;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.Features.Circle;
import ca.mcmaster.cas.se2aa4.island.Features.Lagoon;
import ca.mcmaster.cas.se2aa4.island.lakes.Lakes;

import ca.mcmaster.cas.se2aa4.island.Features.Square;

public class islandGen {
    public static Structs.Mesh islandGenerator(Structs.Mesh aMesh,String mode, String shape, int lakeCount){

        Structs.Mesh outputMesh = aMesh;

       if (mode.equals("lagoon")) {
            //Takes aMesh into Lagoon
            outputMesh = Lagoon.lagoon(aMesh).build();
        } else if (shape.equals("circle")) {
           //Takes aMesh into Lagoon
           outputMesh = Circle.circle(aMesh).build();
       }
        //outputMesh = Lakes.lake(outputMesh,lakeCount).build();

        //outputMesh =Circle.circle(aMesh).build();;
        //outputMesh = Lakes.lake(outputMesh,lakeCount).build();*/
        if (shape.equals("square")) {
            //Takes aMesh into Lagoon
            outputMesh = Square.square(aMesh).build();
        }
        outputMesh = Lakes.lake(outputMesh,3).build();

        return outputMesh;
    }
}
