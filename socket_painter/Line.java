

import java.awt.*;
import java.awt.Point;
import java.io.Serializable;

public class Line extends PaintingPrimitives implements Serializable {

    private static final long serialVersionUID = 1L;

    private Point start;
    private Point end;

    public Line(Color c, Point start, Point end) {
        super(c);
        this.start = start;
        this.end = end;
    }

    @Override
    protected void drawGeometry(Graphics g) {
        g.drawLine(this.start.x, this.start.y, this.end.x, this.end.y);
    }

}