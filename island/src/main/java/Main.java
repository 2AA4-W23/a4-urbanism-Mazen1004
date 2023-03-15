import Configuration.*;
import Features.*;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;


public class Main {
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration(args);
        Structs.Mesh aMesh = new MeshFactory().read(config.input()); //input mesh from the command lines



        //Takes aMesh into Lagoon
        Lagoon shapeTest = new Lagoon();

        //shapeTest.lagoon(aMesh); this is equivalent to clone and should work

        new MeshFactory().write(shapeTest.lagoon(aMesh).build(), config.output()); //output mesh

    }

}
