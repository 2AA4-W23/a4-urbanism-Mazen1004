package ca.mcmaster.cas.se2aa4.island.islandGen;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.Shapes.Circle;
import ca.mcmaster.cas.se2aa4.island.Shapes.Lagoon;
import ca.mcmaster.cas.se2aa4.island.lakes.Lakes;

import ca.mcmaster.cas.se2aa4.island.Shapes.Square;

public class islandGen {
    public static Structs.Mesh islandGenerator(Structs.Mesh aMesh){
    //String mode, String shape, int lakeCount other inputs
        Structs.Mesh outputMesh = aMesh;
        outputMesh=Circle.circle(aMesh).build();

//        if ("lagoon".equals(mode)) {
//            outputMesh = Lagoon.lagoon(aMesh).build();
//        } else {
//            System.out.println("For mode MVP you input must be '-mode lagoon'");
//            switch (shape) {
//                case "circle":
//                    outputMesh = Circle.circle(aMesh).build();
//                    break;
//                case "square":
//                    outputMesh = Square.square(aMesh).build();
//                    break;
//            }
//            switch (lakeCount) {
//                default:
//                    outputMesh = Lakes.lake(outputMesh,lakeCount).build();
//            }
//
//        }

        //outputMesh = Lakes.lake(outputMesh,lakeCount).build();

        //outputMesh =Circle.circle(aMesh).build();;
        //outputMesh = Lakes.lake(outputMesh,lakeCount).build();*/

        return outputMesh;
    }
}
