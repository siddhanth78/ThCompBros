

import java.awt.*;
import java.awt.Point;
import java.io.Serializable;

public class Circle extends PaintingPrimitives implements Serializable {

    private static final long serialVersionUID = 1L;

    private Point center;
    private Point radiusPoint;

    public Circle(Color c, Point center, Point radiusPoint) {
        super(c);
        this.center = center;
        this.radiusPoint = radiusPoint;
    }

    public void drawGeometry(Graphics g) {
        int radius = (int) Math.abs(center.distance(radiusPoint));
        g.drawOval(this.center.x - radius, this.center.y - radius, radius*2, radius*2);           
    }
    

}