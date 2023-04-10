import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.Configuration.*;
import ca.mcmaster.cas.se2aa4.island.islandGen.islandGen;
import ca.mcmaster.cas.se2aa4.island.urbanismGen.UrbanismGen;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration(args);
        Structs.Mesh aMesh = new MeshFactory().read(config.input()); //input mesh from the command lines

        Structs.Mesh outputMesh; //initializing output mesh

        outputMesh = islandGen.islandGenerator(aMesh,config); //tiles take\



        new MeshFactory().write(outputMesh, config.output()); //output to visualizer
    }
}
