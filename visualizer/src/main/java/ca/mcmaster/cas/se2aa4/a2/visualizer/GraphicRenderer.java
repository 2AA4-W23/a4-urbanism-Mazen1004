package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;


import javax.xml.stream.events.StartDocument;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.net.SocketOption;
import java.util.List;
import java.awt.geom.Line2D;

public class GraphicRenderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLUE);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        for (Vertex v: aMesh.getVerticesList()) {
            double centre_x = v.getX() - (THICKNESS/2.0d);
            double centre_y = v.getY() - (THICKNESS/2.0d);
            Color old = canvas.getColor();
            canvas.setColor(extractColor(v.getPropertiesList()));
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
            canvas.fill(point);
            canvas.setColor(old);
        }

        for (Structs.Segment s: aMesh.getSegmentsList()) {
            //line needs start and end
            //start is v1 position
            //need access to v1, to get x and y
            //get v1
            System.out.println("@@@" + s.getV1Idx());
            System.out.println("@@@" + s.getV2Idx());

            Vertex v1 = aMesh.getVertices(s.getV1Idx());
            Vertex v2 = aMesh.getVertices(s.getV2Idx());
            System.out.println("@@@" + v1.getX() + v1.getY());
            System.out.println("@@@" + v2.getX() + v2.getY());
            canvas.setColor(Color.GREEN);
            System.out.println(Color.GREEN);
            Color seg1=extractColor(v1.getPropertiesList());
            Color seg2=extractColor(v2.getPropertiesList());
            System.out.println(seg1);
            System.out.println(seg2);
            //Calculates Average Color for each rgb value from the vertices
            int avgRed=(seg1.getRed()+ seg2.getRed())/2;
            int avgGreen=(seg1.getGreen()+ seg2.getGreen())/2;
            int avgBlue=(seg1.getBlue()+ seg2.getBlue())/2;
            System.out.println(avgRed);
            System.out.println(avgGreen);
            System.out.println(avgBlue);

            //Stores Average Color as a new color
            Color AvgColor=new Color(avgRed,avgGreen,avgBlue);
            System.out.println(AvgColor);
            canvas.setColor(AvgColor);
            canvas.draw(new Line2D.Double(v1.getX(), v1.getY(), v2.getX(), v2.getY()));
        }


    }
    private Color extractColor(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        if (val == null)
            return Color.BLACK;
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new Color(red, green, blue);
    }

}
