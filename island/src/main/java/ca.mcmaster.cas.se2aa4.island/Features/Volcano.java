package ca.mcmaster.cas.se2aa4.island.Features;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;

public class Volcano extends Altitude {
    public static Structs.Mesh.Builder volcano(Structs.Mesh aMesh){
        List<Structs.Polygon> polygonList = aMesh.getPolygonsList();

        for (int i = 0; i < polygonList.size(); i++) {
            Structs.Polygon polygonIndex = aMesh.getPolygons(i);
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(polygonIndex);
        }
    }
}
