import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;

public class PaintingPanel extends JPanel {

    private ArrayList<PaintingPrimitives> primitives = new ArrayList<PaintingPrimitives>();

    public PaintingPanel() {
        this.setBackground(Color.WHITE); 
    }

    public void addPrimitive(PaintingPrimitives obj) {
        this.primitives.add(obj);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (PaintingPrimitives obj : primitives) { 
            obj.draw(g);
        } 
    }
}