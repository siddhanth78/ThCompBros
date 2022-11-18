

import java.awt.*;
import java.io.Serializable;

abstract class PaintingPrimitives implements Serializable {
    private Color color;

    protected PaintingPrimitives() {
    }

    public PaintingPrimitives(Color c) {
        this.color = c;
    }

    public final void draw(Graphics g) {
        System.out.println(g);

        g.setColor(this.color);
        drawGeometry(g);
    }
    
    protected abstract void drawGeometry(Graphics g);

}