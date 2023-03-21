import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.Configuration.*;
import ca.mcmaster.cas.se2aa4.island.islandGen.islandGen;

import static ca.mcmaster.cas.se2aa4.island.Altitudes.Mountain.mountain;


public class Main {
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration(args);
        Structs.Mesh aMesh = new MeshFactory().read(config.input()); //input mesh from the command lines

        String mode = config.mode(); //lagoon
        String shape= config.shape(); //circle
        int lakes = Integer.parseInt(config.lakes());
        //String shape = config.export(Configuration.SHAPE);
        //System.out.println(mode);
        //System.out.println(shape);

        Structs.Mesh outputMesh; //initializing output mesh

//        outputMesh = islandGen.islandGenerator(aMesh,mode,shape,lakes); //tiles take
        outputMesh = islandGen.islandGenerator;
        int[] output = mountain(outputMesh);
        new MeshFactory().write(outputMesh, config.output()); //output to visualizer
    }
}
