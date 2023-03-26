package ca.mcmaster.cas.se2aa4.island.islandGen;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.Altitudes.Altitude;
import ca.mcmaster.cas.se2aa4.island.Altitudes.Mountain;
import ca.mcmaster.cas.se2aa4.island.Altitudes.Valley;
import ca.mcmaster.cas.se2aa4.island.Configuration.Configuration;
import ca.mcmaster.cas.se2aa4.island.Shapes.Circle;
import ca.mcmaster.cas.se2aa4.island.Shapes.Lagoon;
import ca.mcmaster.cas.se2aa4.island.Shapes.Square;
import ca.mcmaster.cas.se2aa4.island.aquifers.Aquifers;
import ca.mcmaster.cas.se2aa4.island.biomes.Biomes;
import ca.mcmaster.cas.se2aa4.island.lakes.Lakes;
import ca.mcmaster.cas.se2aa4.island.rivers.Rivers;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;
import ca.mcmaster.cas.se2aa4.island.SoilAbsorbtion.soilAbsorption;

import java.util.Random;

public class islandGen {
    public static Structs.Mesh islandGenerator(Structs.Mesh aMesh, Configuration config){
    //String mode, String shape, int lakeCount other inputs

        String mode = config.mode(); //lagoon
        String shape= config.shape(); //circle
        String altitude= config.altitude(); //mountain
        int lakes = Integer.parseInt(config.lakes());
        int rivers = Integer.parseInt(config.rivers());
        int aquifers = Integer.parseInt(config.aquifers());
        int seed = Integer.parseInt(config.seed());
        String biomes = config.biomes();
        String soil = config.soil();

        Structs.Mesh outputMesh;




        //Base Humidity and Elevation Initialized
        Tiles newTile = new Tiles();
        //System.out.println("number of polygons" + aMesh.getPolygonsCount());
        newTile.setBaseHumidity(aMesh.getPolygonsCount());
        newTile.setBaseElevation(aMesh.getPolygonsCount());

        //if the seed doesn't equal zero the seed will be randomly generated
      if (seed != 0) {
          Random random = new Random(seed);
          int randomSeed = random.nextInt(10);
      }

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
            if ("mountain".equals(altitude)) {
                int[] arr = Altitude.mountain(outputMesh);
                newTile.updateElevation(arr);
                outputMesh = Mountain.mountainMesh(outputMesh, arr).build();
            }
            else if ("valley".equals(altitude)) {
                int[] arr = Altitude.mountain(outputMesh);
                newTile.updateElevation(arr);
                outputMesh = Valley.valleyMesh(outputMesh, arr).build();
            }
            if (!"none".equals(biomes)) {
                outputMesh = Biomes.biomeGenerator(outputMesh,biomes,newTile).build();
            }
            if ("drySoil".equals(soil)) {
                soilAbsorption.modifyHumidity("drySoil", newTile);
            } else if ("wetSoil".equals(soil)) {
                soilAbsorption.modifyHumidity("wetSoil", newTile);
            }

        // -shape square
        } else if ("square".equals(shape)) {
            outputMesh = Square.square(aMesh,newTile).build();
            if (lakes !=  0) {
                outputMesh = Lakes.lake(outputMesh,lakes,newTile).build();
            }
            if (rivers !=  0) {
                outputMesh = Rivers.river(outputMesh,rivers).build();
            }
            if (aquifers !=  0) {
                outputMesh = Aquifers.aquifer(outputMesh, aquifers,newTile).build();
            }
            if ("mountain".equals(altitude)) {
                int[] arr = Altitude.mountain(outputMesh);
                newTile.updateElevation(arr);
                outputMesh = Mountain.mountainMesh(outputMesh, arr).build();
            }
            else if ("valley".equals(altitude)) {
                int[] arr = Altitude.mountain(outputMesh);
                newTile.updateElevation(arr);
                outputMesh = Valley.valleyMesh(outputMesh, arr).build();
            }
            if (!"none".equals(biomes)) {
                outputMesh = Biomes.biomeGenerator(outputMesh,biomes,newTile).build();
            }
            if ("drySoil".equals(soil)) {
                soilAbsorption.modifyHumidity("drySoil", newTile);
            } else if ("wetSoil".equals(soil)) {
                soilAbsorption.modifyHumidity("wetSoil", newTile);
            }
        }


        // -mode lagoon
        else  { //defaults as the mvp when the user doesn't type anything
            outputMesh = Lagoon.lagoon(aMesh, newTile).build();
            if (lakes !=  0) {
                outputMesh = Lakes.lake(outputMesh,lakes,newTile).build();
            }
        }

        //biome calculation for all the tiles in the mesh
        Biomes.calculateBiomes(outputMesh, newTile);


        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Command line input summary: ");
        System.out.println("mode is " + mode);
        System.out.println("shape is " + shape);
        System.out.println("lakes count is " + lakes);
        System.out.println("rivers count is " + rivers);
        System.out.println("biomes is " + biomes);
        System.out.println("aquifers count is " + aquifers);
        System.out.println("altitude is " + altitude);
        System.out.println("seed is " + seed);




        return outputMesh;

    }
}
