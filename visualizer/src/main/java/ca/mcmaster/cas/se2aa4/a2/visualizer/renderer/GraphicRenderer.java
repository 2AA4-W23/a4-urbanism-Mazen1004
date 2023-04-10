package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ColorProperty;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.isRiver;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class GraphicRenderer implements Renderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.2f);
        canvas.setStroke(stroke);
        drawPolygons(aMesh,canvas);
        drawSegment(aMesh, canvas);
        drawCities(aMesh, canvas);
        drawRoads(aMesh, canvas);
    }

    private void drawPolygons(Mesh aMesh, Graphics2D canvas) {
        for(Structs.Polygon p: aMesh.getPolygonsList()){
            drawAPolygon(p, aMesh, canvas);
        }
    }
    //draw segments
    private void drawSegment(Mesh aMesh, Graphics2D canvas) {
        for (Structs.Segment segment: aMesh.getSegmentsList()) {
            drawASegments(segment, aMesh, canvas, Color.WHITE);
        }
    }
    private void drawASegments(Structs.Segment segment, Mesh aMesh, Graphics2D canvas, Color color){
        Structs.Vertex vertex1 = aMesh.getVertices(segment.getV1Idx());
        Structs.Vertex vertex2 = aMesh.getVertices(segment.getV2Idx());

       /* isRiver isRiver = new isRiver();

        if (isRiver.extract(segment.getPropertiesList()).toString().equals("rivers")) {
            canvas.drawLine((int) vertex1.getX(), (int) vertex1.getY(), (int) vertex2.getX(), (int) vertex2.getY());
            canvas.setColor(Color.red);
            canvas.setStroke(new BasicStroke(THICKNESS/3));
        }*/


    }
    private void drawRoads(Structs.Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.RED);
        for(Structs.Segment s: aMesh.getSegmentsList()) {
            List<Structs.Property> propertiesList = s.getPropertiesList();
            Structs.Vertex vertex1 = aMesh.getVertices(s.getV1Idx());
            Structs.Vertex vertex2 = aMesh.getVertices(s.getV2Idx());
            for (int j = 0; j < propertiesList.size(); j++) {
                Structs.Property property = propertiesList.get(j);
                if (property.getKey().equals("rgb_color") && property.getValue().equals("225,0,0")) {

                    canvas.drawLine((int) vertex1.getX(), (int) vertex1.getY(), (int) vertex2.getX(), (int) vertex2.getY());
                    canvas.setColor(Color.red);
                    canvas.setStroke(new BasicStroke(THICKNESS/2));

                }
            }

        }
    }
    //Draw City Centroid
    private void drawCities(Structs.Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.RED);

        for(Structs.Vertex p: aMesh.getVerticesList()) {
            List<Structs.Property> propertiesList = p.getPropertiesList();
            for (int j = 0; j < propertiesList.size(); j++) {
                Structs.Property property = propertiesList.get(j);
                Random random = new Random();

                float citySize = (random.nextFloat()*15) + 5;
                //System.out.println(citySize);

                if (property.getKey().equals("rgb_color") && property.getValue().equals("225,0,0")) {
                    Ellipse2D circle = new Ellipse2D.Float((float) p.getX()- (citySize/2), (float) p.getY()- (citySize/2),
                            (int)citySize, (int)citySize);
                    canvas.fill(circle);

                }
            }
        }
    }
    /*private void drawCities(Structs.Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.RED);
        for(Structs.Vertex p: aMesh.getVerticesList()) {
            List<Structs.Property> propertiesList = p.getPropertiesList();
            for (int j = 0; j < propertiesList.size(); j++) {
                Structs.Property property = propertiesList.get(j);
                Random random = new Random();
                float citySize = (random.nextFloat()*15) + 5;

                if (property.getKey().equals("rgb_color") && property.getValue().equals("255,0,0")) {
                    Ellipse2D circle = new Ellipse2D.Float(((float) p.getX()- (citySize/2)), ((float) p.getY()- (citySize/2)),
                            (citySize), (citySize));
                    canvas.fill(circle);

                }
            }
        }
    }*/

    private void drawAPolygon(Structs.Polygon p, Mesh aMesh, Graphics2D canvas) {
        Hull hull = new Hull();
        for(Integer segmentIdx: p.getSegmentIdxsList()) {
            hull.add(aMesh.getSegments(segmentIdx), aMesh);
        }
        Path2D path = new Path2D.Float();
        Iterator<Vertex> vertices = hull.iterator();
        Vertex current = vertices.next();
        path.moveTo(current.getX(), current.getY());
        while (vertices.hasNext()) {
            current = vertices.next();
            path.lineTo(current.getX(), current.getY());
        }
        path.closePath();
        canvas.draw(path);
        Optional<Color> fill = new ColorProperty().extract(p.getPropertiesList());
        if(fill.isPresent()) {
            Color old = canvas.getColor();
            canvas.setColor(fill.get());
            canvas.fill(path);
            canvas.setColor(old);
        }
    }

}
