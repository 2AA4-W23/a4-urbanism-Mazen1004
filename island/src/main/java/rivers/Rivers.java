package rivers;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Rivers {
    public static Structs.Mesh.Builder river(Structs.Mesh aMesh, int riverCount) {

        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        colors.Colors rivers = new colors.Colors();



        //Loop to count how many polygons are land
        int landCount = 0;
        for (int i = 0; i < aMesh.getPolygonsCount(); i++) {
            Structs.Polygon polygon = aMesh.getPolygons(i);
            List<Structs.Property> propertiesList = polygon.getPropertiesList();
            for (int j = 0; j < propertiesList.size(); j++) {
                Structs.Property property = propertiesList.get(j);
                if (property.getKey().equals("rgb_color") && property.getValue().equals(rivers.LandColor)) {
                    landCount++;
                }
            }
        } //number of land tiles in our island

        //Randomly Generates lakes tiles based on number of Lakes
        int[] randomLakes = new int[riverCount];
        Random rand = new Random();

        for (int i = 0; i < riverCount; i++) {
            int randomIndex = rand.nextInt(landCount);
            randomLakes[i] = randomIndex;
        }
        System.out.println(Arrays.toString(randomLakes)); //which polygons are going to get a lake

        int landCount1=0;
        for (int i = 0; i < riverCount; i++) {


            //get each polygon
            Structs.Polygon polygonIndex = aMesh.getPolygons(i);

            //this is just the builder for each polygon
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(polygonIndex);

            //get the segment indices of each polygon
            int segmentIndex = polygonIndex.getSegmentIdxs(i);

            //you want to color segmentIndex.
            Structs.Segment segment = aMesh.getSegments(segmentIndex);

           System.out.println("segmentIndex:" + segmentIndex);
            //polygon 1 has segment index 2658
            //polygon 2 has segment index 1390

            System.out.println("segment" + segment);



            Structs.Segment.Builder segmentBuilder = Structs.Segment.newBuilder(segment);
            String color = rivers.LandColor;

            Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();

            segmentBuilder.addProperties(p);
//            System.out.println(segmentBuilder);


//            for (int j = 0; j < propertiesList.size(); j++) {
//                System.out.println(propertiesList);
//                Structs.Property property = propertiesList.get(j);
//                if (property.getKey().equals("rgb_color") && property.getValue().equals(rivers.LandColor)) {
//                    landCount1++;
//                    for (int k = 0; k < randomLakes.length; k++) {
//                        if (randomLakes[k] == landCount1) { //polygon matches the random array
//                            Structs.Property.Builder propertyBuilder = Structs.Property.newBuilder(property);
//                            propertyBuilder.setValue(rivers.LagoonSeaColor);
//                            segmentBuilder.setProperties(j, propertyBuilder.build());
//                        }
//                    }
//                }
//            }

            clone.addSegments(segmentBuilder.build());
            clone.addPolygons(pc.build());
            System.out.println(segmentBuilder);
        }
        return clone;
    }
}