package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import java.util.Random;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    MeshImplement newMesh = new MeshImplement();
    Random random = new Random();

    public Mesh generate() {

        // Create all the vertices
        for(double x = 0; x < width; x += square_size) {
            for (double y = 0; y < height; y += square_size) {
                newMesh.addVertex(x,y);
            }
        }

        Coordinate centroids = new Coordinate();
        // Draws centroids
        for (int i = 0; i < 575; i++) {
            double x = random.nextDouble(475);
            double y = random.nextDouble(475);
            centroids.(x,y);
            newMesh.addCentroid(x,y);
        }

        //Draws all the Segments
        for(Vertex v: newMesh.vertices){
            newMesh.addSegment(v);
        }

        // Distribute colors randomly. Vertices are immutable, need to enrich them
        //ArrayList<Vertex> verticesWithColors = new ArrayList<>();
        Random bag = new Random();
        for(Vertex v: newMesh.vertices){
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue;
            newMesh.vertexColors(colorCode,v);
        }

        // Set colors of centroids to red
        //ArrayList<Vertex> centroidsWithColors = new ArrayList<>();
        for(Vertex v: newMesh.centroids){

            newMesh.centroidColors(v);
        }

        return newMesh.getMesh();
    }
    public void voronoi(){
        VoronoiDiagramBuilder map = new VoronoiDiagramBuilder();
        map.setSites();
        // get list of coordinates (of centroids) to set sites
        // use getdiagram which returns type geometry
        // use geometry libraries to extract information to put back into visualizer
            // getNumGeometries returns total number of geometries in getDiagram
            // use for loop to iterate through every geometry in the collection
            // use getGeometryN which returns the geometry in the collection
                // use getCoordinates to get an array containing all the vertices in the geometry
                    // loop through the array and extract the x and y coordinates
                    // use x and y to build vertices
                        // build segments using vertices
                    // use Geometry getCentroid to get centroid
                // input vertices, segments and centroid to polygon builder, then do the same as step2 to appear on SVG
        map.getDiagram();
    }
}
