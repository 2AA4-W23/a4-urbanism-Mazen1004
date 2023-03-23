package ca.mcmaster.cas.se2aa4.island.islandGen;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.Shapes.Circle;
import ca.mcmaster.cas.se2aa4.island.Shapes.Lagoon;
import ca.mcmaster.cas.se2aa4.island.lakes.Lakes;

import ca.mcmaster.cas.se2aa4.island.Shapes.Square;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;

public class islandGen {
    public static Structs.Mesh islandGenerator(Structs.Mesh aMesh, String mode, String shape, int lakeCount){
    //String mode, String shape, int lakeCount other inputs
        Structs.Mesh outputMesh;

        //Base Humidity is Initialized
        Tiles newTile = new Tiles();
        //System.out.println("number of polygons" + aMesh.getPolygonsCount());
        newTile.setBaseHumidity(aMesh.getPolygonsCount());



        // -shape circle
        if ("circle".equals(shape)) {
            outputMesh = Circle.circle(aMesh).build();
            if (lakeCount !=  0) {
                outputMesh = Lakes.lake(outputMesh,lakeCount,newTile).build();
            }
        // -shape square
        } else if ("square".equals(shape)) {
            outputMesh = Square.square(aMesh).build();
            if (lakeCount !=  0) {
                outputMesh = Lakes.lake(outputMesh,lakeCount,newTile).build();
            }
        }

        // -mode lagoon
        else  { //defaults as the mvp when the user doesn't type anything
            outputMesh = Lagoon.lagoon(aMesh).build();
            if (lakeCount !=  0) {
                outputMesh = Lakes.lake(outputMesh,lakeCount,newTile).build();
            }
        }
        System.out.println(mode);
        System.out.println(shape);
        System.out.println("Testing Array Output");
        newTile.retrieveHumidity();

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


        return outputMesh;
    }
}
