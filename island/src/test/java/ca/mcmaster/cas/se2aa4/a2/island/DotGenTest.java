package ca.mcmaster.cas.se2aa4.a2.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.island.Shapes.Circle;
import ca.mcmaster.cas.se2aa4.island.Shapes.Lagoon;
import ca.mcmaster.cas.se2aa4.island.aquifers.Aquifers;
import ca.mcmaster.cas.se2aa4.island.biomes.Biomes;
import ca.mcmaster.cas.se2aa4.island.tiles.Tiles;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class DotGenTest {

    //aquifersTest
    @Test
    public void testAquiferGeneration() {
        // test mesh
        Structs.Mesh.Builder meshBuilder = Structs.Mesh.newBuilder();
        meshBuilder.addVertices(Structs.Vertex.newBuilder().setX(0).setY(0));
        meshBuilder.addVertices(Structs.Vertex.newBuilder().setX(1).setY(0));
        meshBuilder.addVertices(Structs.Vertex.newBuilder().setX(1).setY(1));
        meshBuilder.addVertices(Structs.Vertex.newBuilder().setX(0).setY(1));

        Structs.Polygon.Builder polygonBuilder = Structs.Polygon.newBuilder();
        polygonBuilder.addSegmentIdxs(0);
        polygonBuilder.addSegmentIdxs(1);
        polygonBuilder.addSegmentIdxs(2);
        polygonBuilder.addSegmentIdxs(3);
        polygonBuilder.addProperties(Structs.Property.newBuilder().setKey("rgb_color").setValue("42,171,42"));

        meshBuilder.addPolygons(polygonBuilder.build());

        Structs.Mesh testMesh = meshBuilder.build();

        // aquifers for test mesh
        Structs.Mesh.Builder resultBuilder = Aquifers.aquifer(testMesh, 1, new Tiles());

        // Check that the resulting mesh is not null
        assertNotNull(resultBuilder);

        // Check that the resulting mesh has the same number of polygons as the original mesh
        assertEquals(testMesh.getPolygonsCount(), resultBuilder.build().getPolygonsCount());
    }

    //biomesTest
    @Test
    public void testCalculateBiomes() {
        // Create a mock Tiles object with dummy humidity and elevation values
        Tiles mockTiles = new Tiles();

        int[] elevationValues = {15};

        //Set Humidity Tile with Base Value
        mockTiles.setBaseHumidity(1);

        mockTiles.addHumidity(0,60);
        mockTiles.updateElevation(elevationValues);

        // Create a mock Mesh object with dummy data
        Structs.Mesh.Builder mockMeshBuilder = Structs.Mesh.newBuilder();
        Structs.Polygon mockPolygon = Structs.Polygon.newBuilder().build();
        mockMeshBuilder.addPolygons(mockPolygon);
        Structs.Mesh mockMesh = mockMeshBuilder.build();

        // Call calculateBiomes and verify that the output is as expected
        String[] outputBiomes = Biomes.calculateBiomes(mockMesh, mockTiles);
        String[] expectedBiomes = { "Mountain Wetlands" };
        assertEquals(Arrays.toString(expectedBiomes), Arrays.toString(outputBiomes));
    }

    //elevationTest
    @Test
    public void testSetBaseElevation() {
        Tiles tiles = new Tiles();
        tiles.setBaseElevation(10);
        int[] expectedElevation = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] actualElevation = tiles.retrieveElevation();
        assertArrayEquals(expectedElevation, actualElevation);
    }

    @Test
    public void testUpdateElevation() {
        Tiles tiles = new Tiles();
        tiles.setBaseElevation(10);
        int[] elevationArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        tiles.updateElevation(elevationArray);
        int[] expectedElevation = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] actualElevation = tiles.retrieveElevation();
        assertArrayEquals(expectedElevation, actualElevation);
    }

    //humidityTest
    @Test
    public void testSetBaseHumidity() {
        Tiles tiles = new Tiles();
        tiles.setBaseHumidity(10);
        int[] expectedHumidity = new int[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
        int[] actualHumidity = tiles.retrieveHumidity();
        assertArrayEquals(expectedHumidity, actualHumidity);
    }

    @Test
    public void testSetHumidity() {
        Tiles tiles = new Tiles();
        tiles.setBaseHumidity(10);
        tiles.setHumidity(0, 60);
        int expectedHumidity = 60;
        int actualHumidity = tiles.getHumidity(0);
        assertEquals(expectedHumidity, actualHumidity);
    }

    @Test
    public void testAddHumidity() {
        Tiles tiles = new Tiles();
        tiles.setBaseHumidity(10);
        tiles.setHumidity(0, 60);
        tiles.addHumidity(0, 10);
        int expectedHumidity = 70;
        int actualHumidity = tiles.getHumidity(0);
        assertEquals(expectedHumidity, actualHumidity);
    }

    //shapesTest
    @Test
    public void testShapeColors() {
        Structs.Mesh.Builder mesh = Structs.Mesh.newBuilder();
        Structs.Polygon.Builder polygon1 = Structs.Polygon.newBuilder();

        colors.Colors colors = new colors.Colors();

        Structs.Property p1 = Structs.Property.newBuilder().setKey("rgb_color").setValue(colors.LandColor).build();
        Structs.Property p2 = Structs.Property.newBuilder().setKey("rgb_color").setValue(colors.OceanColor).build();
        polygon1.addProperties(p1);

        Structs.Polygon.Builder polygon2 = Structs.Polygon.newBuilder();
        polygon2.addProperties(p2);


        mesh.addVertices(Structs.Vertex.newBuilder().setX(0).setY(0));
        mesh.addVertices(Structs.Vertex.newBuilder().setX(1).setY(0));
        mesh.addVertices(Structs.Vertex.newBuilder().setX(0).setY(1));
        mesh.addVertices(Structs.Vertex.newBuilder().setX(1).setY(1));
        mesh.addPolygons(polygon1.build());
        mesh.addPolygons(polygon2.build());

        Structs.Mesh.Builder circleMesh = Circle.circle(mesh.build(), null);

        String landColor = colors.LandColor;
        String oceanColor = colors.OceanColor;

        // check the colors of the first polygon
        Structs.Polygon firstPolygon = circleMesh.getPolygons(0);
        assertEquals(landColor, firstPolygon.getProperties(0).getValue());

        // check the colors of the second polygon
        Structs.Polygon secondPolygon = circleMesh.getPolygons(1);
        assertEquals(oceanColor, secondPolygon.getProperties(0).getValue());
    }

    @Test
    void testLagoonColors() {
        Structs.Mesh.Builder mesh = Structs.Mesh.newBuilder();
        Structs.Mesh outputMesh = mesh.build();
        Tiles tiles = new Tiles();
        Structs.Mesh.Builder lagoonMesh = Lagoon.lagoon(outputMesh, tiles);
        colors.Colors colors = new colors.Colors();

        for (Structs.Polygon polygon : lagoonMesh.getPolygonsList()) {
            if (polygon.getPropertiesList().size() > 0) {
                String color = polygon.getProperties(0).getValue();
                if (outputMesh.equals(polygon.getCentroidIdx())) {
                    assertEquals(colors.LagoonSeaColor, color);
                }
                if (outputMesh.equals(polygon.getCentroidIdx())) {
                    assertEquals(colors.LandColor, color);
                }else {
                    assertEquals(colors.OceanColor, color);
                }
            }
        }

    }

    @Test
    public void testDistanceCalc() {
        double x1 = 0;
        double y1 = 0;
        double x2 = 3;
        double y2 = 4;
        double expectedDistance = 5;

        double actualDistance = Circle.distanceCalc(x1, y1, x2, y2);

        assertEquals(expectedDistance, actualDistance, 0.0001);
    }

}
