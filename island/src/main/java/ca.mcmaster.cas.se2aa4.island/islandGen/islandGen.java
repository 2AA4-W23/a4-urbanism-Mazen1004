package ca.mcmaster.cas.se2aa4.island.islandGen;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.Features.Circle;
import ca.mcmaster.cas.se2aa4.island.Features.Lagoon;
import ca.mcmaster.cas.se2aa4.island.lakes.Lakes;

import ca.mcmaster.cas.se2aa4.island.Features.Square;

public class islandGen {
    public static Structs.Mesh islandGenerator(Structs.Mesh aMesh,String mode, String shape, int lakeCount){

        Structs.Mesh outputMesh = aMesh;

        if ("lagoon".equals(mode)) {
            outputMesh = Lagoon.lagoon(aMesh).build();
        } else {
            System.out.println("For mode MVP you input must be '-mode lagoon'");
            switch (shape) {
                case "circle":
                    outputMesh = Circle.circle(aMesh).build();
                    break;
                case "square":
                    outputMesh = Square.square(aMesh).build();
                    break;
            }
            switch (lakeCount) {
                default:
                    outputMesh = Lakes.lake(outputMesh,lakeCount).build();
            }

        }

        //outputMesh = Lakes.lake(outputMesh,lakeCount).build();

        //outputMesh =Circle.circle(aMesh).build();;
        //outputMesh = Lakes.lake(outputMesh,lakeCount).build();*/

        return outputMesh;
    }
}
