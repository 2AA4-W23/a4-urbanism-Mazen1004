package ca.mcmaster.cas.se2aa4.island.islandGen;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.Altitudes.Mountain;
import ca.mcmaster.cas.se2aa4.island.Configuration.Configuration;
import ca.mcmaster.cas.se2aa4.island.Shapes.Circle;
import ca.mcmaster.cas.se2aa4.island.Shapes.Lagoon;
import ca.mcmaster.cas.se2aa4.island.aquifers.Aquifers;
import ca.mcmaster.cas.se2aa4.island.lakes.Lakes;

import ca.mcmaster.cas.se2aa4.island.Shapes.Square;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;
import rivers.Rivers;

public class islandGen {
    public static Structs.Mesh islandGenerator(Structs.Mesh aMesh, Configuration config){
    //String mode, String shape, int lakeCount other inputs

        String mode = config.mode(); //lagoon
        String shape= config.shape(); //circle
        String elevation= config.elevation(); //circle
        int lakes = Integer.parseInt(config.lakes());
        int rivers = Integer.parseInt(config.rivers());
        int aquifers = Integer.parseInt(config.aquifers());

        Structs.Mesh outputMesh;

        //Base Humidity is Initialized
        Tiles newTile = new Tiles();
        //System.out.println("number of polygons" + aMesh.getPolygonsCount());
        newTile.setBaseHumidity(aMesh.getPolygonsCount());



        // -shape circle
        if ("circle".equals(shape)) {
            outputMesh = Circle.circle(aMesh,newTile).build();
            if (lakes !=  0) {
                outputMesh = Lakes.lake(outputMesh,lakes,newTile).build();
            }
            if (rivers !=  0) {
                outputMesh = Rivers.river(outputMesh,rivers).build();
            }
            if (aquifers !=  0) {
                outputMesh = Aquifers.aquifer(outputMesh, aquifers,newTile).build();
            }
            if ("mountain".equals(elevation)) {
                int[] arr = Mountain.mountain(outputMesh);
                outputMesh = Mountain.mountainMesh(outputMesh, arr).build();
            }

        // -shape square
        } else if ("square".equals(shape)) {
            outputMesh = Square.square(aMesh,newTile).build();
            if (lakes !=  0) {
                outputMesh = Lakes.lake(outputMesh,lakes,newTile).build();
            }
            if (aquifers !=  0) {
                outputMesh = Aquifers.aquifer(outputMesh, aquifers,newTile).build();
            }
            if ("mountain".equals(elevation)) {
                int[] arr = Mountain.mountain(outputMesh);
                outputMesh = Mountain.mountainMesh(outputMesh, arr).build();
            }
        }

        //elevation -volcano



        // -mode lagoon
        else  { //defaults as the mvp when the user doesn't type anything
            outputMesh = Lagoon.lagoon(aMesh, newTile).build();
            if (lakes !=  0) {
                outputMesh = Lakes.lake(outputMesh,lakes,newTile).build();
            }
        }

        System.out.println(mode);
        System.out.println(shape);
        System.out.println(lakes);
        System.out.println(rivers);
        System.out.println(aquifers);
        System.out.println(elevation);

       System.out.println("Testing Array Output");
        newTile.retrieveHumidity();



        return outputMesh;
    }
}
