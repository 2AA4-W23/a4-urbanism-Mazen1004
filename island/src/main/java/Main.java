import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.Configuration.*;
import ca.mcmaster.cas.se2aa4.island.Shapes.Circle;
import ca.mcmaster.cas.se2aa4.island.Shapes.Lagoon;
import ca.mcmaster.cas.se2aa4.island.islandGen.islandGen;

import java.util.Arrays;

import static ca.mcmaster.cas.se2aa4.island.Altitudes.Mountain.mountain;


public class Main {
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration(args);
        Structs.Mesh aMesh = new MeshFactory().read(config.input()); //input mesh from the command lines

        Structs.Mesh outputMesh; //initializing output mesh


        outputMesh = islandGen.islandGenerator(aMesh,config); //tiles take\

        new MeshFactory().write(outputMesh, config.output()); //output to visualizer
    }
}
