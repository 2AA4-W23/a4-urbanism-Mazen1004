import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.Features.*;
import ca.mcmaster.cas.se2aa4.island.Configuration.*;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;


public class Main {
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration(args);
        Structs.Mesh aMesh = new MeshFactory().read(config.input()); //input mesh from the command lines

        String mode = config.mode();
        Structs.Mesh outputMesh; //initializing output mesh
        outputMesh = Tiles.tile(aMesh, mode); //tiles take

        new MeshFactory().write(outputMesh, config.output()); //output to visualizer
    }
}
