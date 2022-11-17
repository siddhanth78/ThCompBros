

import java.awt.*;

abstract class PaintingPrimitives {
    private Color color;

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