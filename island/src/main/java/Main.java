import Configuration.*;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Main {
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration(args);
        Structs.Mesh aMesh = new MeshFactory().read(config.input());
        System.out.println(aMesh.getPolygonsList());

        System.out.println("print this bro");
        new MeshFactory().write(aMesh, config.output());
    }
}
