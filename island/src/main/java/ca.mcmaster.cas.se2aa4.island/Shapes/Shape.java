package ca.mcmaster.cas.se2aa4.island.Shapes;


public abstract class Shape {
    protected static double canvasX = 1920; //hardcoded for now
    protected static double canvasY = 1080;
    protected static double centerX= canvasX/2;
    protected static double centerY= canvasY/2;


    public static double distanceCalc(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }




}
