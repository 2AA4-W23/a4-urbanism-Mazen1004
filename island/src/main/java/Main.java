import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.Features.*;
import ca.mcmaster.cas.se2aa4.island.Configuration.*;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;


public class Main {
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration(args);
        Structs.Mesh aMesh = new MeshFactory().read(config.input()); //input mesh from the command lines


        //String mode = config.mode(); //lagoon
        //String shape= config.shape(); //circle
        //String shape = config.export(Configuration.SHAPE);
        //System.out.println(mode);
        //System.out.println(shape);

        Structs.Mesh outputMesh; //initializing output mesh
        outputMesh = Tiles.tile(aMesh,"null","square"); //tiles take

        new MeshFactory().write(outputMesh, config.output()); //output to visualizer
    }
}
