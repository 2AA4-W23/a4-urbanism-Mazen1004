package ca.mcmaster.cas.se2aa4.island.urbanismGen;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.pathfinder.graphs.Graph;
import ca.mcmaster.cas.se2aa4.pathfinder.pathfinding.Dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UrbanismGen {

    static Graph graph = new Graph();

    //This class converts landTiles from the Mesh into a Graph, where Nodes are centroids of land tiles
    //Edges are segments between each centroid, distance calculated using distance formula
    public static Map<Integer, Map<Integer, Integer>> adaptorClass(Structs.Mesh aMesh) {
        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        colors.Colors circle = new colors.Colors();

        List<Structs.Vertex> vertices = aMesh.getVerticesList();
        //Loop to count how many polygons are land Polygons, where cities can be generated
        int nodeCount = 0;
        for (int i = 0; i < aMesh.getPolygonsCount(); i++) {
            Structs.Polygon polygon = aMesh.getPolygons(i);
            Structs.Polygon.Builder polygonBuilder = Structs.Polygon.newBuilder(polygon);
            List<Structs.Property> propertiesList = polygon.getPropertiesList();

            for (int j = 0; j < propertiesList.size(); j++) {
                Structs.Property property = propertiesList.get(j);
                if (property.getKey().equals("rgb_color") && property.getValue().equals(circle.LandColor)) {
                    Structs.Vertex centroid = vertices.get(polygon.getCentroidIdx());

                    graph.addNode(centroid, i);

                    Structs.Property.Builder propertyBuilder = Structs.Property.newBuilder(property);
                    propertyBuilder.setValue(circle.green);
                    polygonBuilder.setProperties(j, propertyBuilder.build());


                    System.out.println("ADDING EDGES/NEIGHBORS");
                    List<Integer> neighborList = polygon.getNeighborIdxsList();
                    System.out.println(neighborList);
                    System.out.println(neighborList.size());
                    for (int k = 0; k < neighborList.size(); k++) {
                        Structs.Polygon neighborPolygon = aMesh.getPolygons(neighborList.get(k));

                        int NeighborIdx = neighborList.get(k);
                        Structs.Vertex centroidNeighbor = vertices.get(neighborPolygon.getCentroidIdx());
                        graph.addNode(centroidNeighbor, NeighborIdx);


                        propertyBuilder.setValue(circle.red);
                        polygonBuilder.setProperties(j, propertyBuilder.build());


                        graph.addEdge(i, NeighborIdx, 1);


                    }


                }
            }
            clone.addPolygons(polygonBuilder.build());
        }
        Map<Integer, Map<Integer, Integer>> adjacencyList = graph.returnGraph();
        return adjacencyList;
        //return clone;

    }

    //Loops through adjacency List generates cities randomly based on user input
    public static List<Integer> generateCities(Structs.Mesh aMesh, int cityNumber) {

        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        colors.Colors circle = new colors.Colors();

        Map<Integer, Map<Integer, Integer>> adjacencyList = graph.returnGraph();

        List<Integer> cityPolygons = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < cityNumber; i++) {
            // Get a random key from the outer map (which is a polygon ID)
            List<Integer> keys = new ArrayList<>(adjacencyList.keySet());
            int randomKey = keys.get(rand.nextInt(keys.size()));

            // Add the key to the cityPolygons list
            cityPolygons.add(randomKey);
        }
        calculateCenterHub(aMesh,cityPolygons);

        System.out.println("CITIES ARE IN THESE POLYGONS");
        System.out.println(cityPolygons); // Example output: [527, 18, 10, 4]
        return cityPolygons;



/*
        //TESTING LOL (WILL NEED TO FIX TO COLOR VERTICES NOT POLYGONS)
        for (int i = 0; i < aMesh.getPolygonsCount(); i++) {

            Structs.Polygon polygon = aMesh.getPolygons(i);
            Structs.Polygon.Builder polygonBuilder = Structs.Polygon.newBuilder(polygon);
            List<Structs.Property> propertiesList = polygon.getPropertiesList();

            // Check if polygon ID is in cityPolygons
            if (cityPolygons.contains(i)) {
                // Set polygon color to green
                for (int j = 0; j < propertiesList.size(); j++) {
                    Structs.Property property = propertiesList.get(j);
                    if (property.getKey().equals("rgb_color")) {
                        Structs.Property.Builder propertyBuilder = Structs.Property.newBuilder(property);
                        propertyBuilder.setValue(circle.DesertColor);
                        polygonBuilder.setProperties(j, propertyBuilder.build());

                    }
                }
            }
            clone.addPolygons(polygonBuilder.build());
        }
        return clone;*/
    }


    public static int calculateCenterHub(Structs.Mesh aMesh, List<Integer> cityPolygons){

        //Center Point Values of Mesh
        double canvasX = 1920;
        double canvasY = 1080;
        double centerX= canvasX/2;
        double centerY= canvasY/2;

        double minDistance = Double.MAX_VALUE;
        int centerPolygonID = -1;

        List<Structs.Vertex> vertices = aMesh.getVerticesList();

        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());


        //Structs.Polygon polygon = aMesh.getPolygons(i);
        //Structs.Polygon.Builder polygonBuilder = Structs.Polygon.newBuilder(polygon);

        for (int i = 0; i < aMesh.getPolygonsCount(); i++) {

            Structs.Polygon polygon = aMesh.getPolygons(i);
            Structs.Polygon.Builder polygonBuilder = Structs.Polygon.newBuilder(polygon);
            List<Structs.Property> propertiesList = polygon.getPropertiesList();

            for (int j = 0; j < cityPolygons.size(); j++) {
                int cityNodeID = cityPolygons.get(j);

                if (i == cityNodeID) {
                    Structs.Vertex centroid = vertices.get(polygon.getCentroidIdx());
                    double centroidX = centroid.getX();
                    double centroidY = centroid.getY();
                    double distance = distanceCalc(centerX, centerY, centroidX, centroidY);

                    if (distance < minDistance) {
                        centerPolygonID = cityNodeID;
                        minDistance = distance;
                    }
                }
            }
        }
        /*
        Structs.Polygon polygon = aMesh.getPolygons(centerPolygonID);
        Structs.Polygon.Builder polygonBuilder = Structs.Polygon.newBuilder(polygon);
        List<Structs.Property> propertiesList = polygon.getPropertiesList();

        colors.Colors circle = new colors.Colors();

        for (int j = 0; j < propertiesList.size(); j++) {
            Structs.Property property = propertiesList.get(j);
            if (property.getKey().equals("rgb_color") ) {
                Structs.Vertex centroid = vertices.get(polygon.getCentroidIdx());

                Structs.Property.Builder propertyBuilder = Structs.Property.newBuilder(property);
                propertyBuilder.setValue(circle.TundraColor);
                polygonBuilder.setProperties(j, propertyBuilder.build());
            }
        }
        clone.addPolygons(polygonBuilder.build());
        return clone;*/
        System.out.println("CENTER POLYGON ID IS " + centerPolygonID);
        return centerPolygonID;
    }
    public static double distanceCalc(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public static List<List<Integer>> roadGeneratorPath(Map<Integer, Map<Integer, Integer>> adjacencyList, int centralHub, List<Integer> cityPolygons){

        List<List<Integer>> shortestPaths = new ArrayList<>();

        for (int i : cityPolygons) {
            if (centralHub != i){
                List<Integer> shortestPath = Dijkstra.findShortestPath(adjacencyList, centralHub, i);
                System.out.println("Shortest path from " + centralHub + " to " + i + ": " + shortestPath);
                shortestPaths.add(shortestPath);
            }
        }
        System.out.println("SHORTEST PATHS ARE" + shortestPaths);
        return shortestPaths;

    }
}




