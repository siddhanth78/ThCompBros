
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class Painter extends JFrame implements ActionListener, MouseInputListener {

    private Color color;
    private String primitive;

    private Point start;

    PaintingPanel centerPanel;

    public Painter() {

        start = new Point();

        setSize(500, 500);

        // When Painter is closed, java virtual machine stops
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel holder = new JPanel();
        holder.setLayout(new BorderLayout());

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1));

        // red paint button
        JButton redPaint = new JButton();
        redPaint.setBackground(Color.RED);
        redPaint.setOpaque(true);
        redPaint.setBorderPainted(false);
        leftPanel.add(redPaint);  // Added in next open cell in the grid

        redPaint.addActionListener(this);
        redPaint.setActionCommand("red");

        // green paint button
        JButton greenPaint = new JButton();
        greenPaint.setBackground(Color.GREEN);
        greenPaint.setOpaque(true);
        greenPaint.setBorderPainted(false);
        leftPanel.add(greenPaint);  

        greenPaint.addActionListener(this);
        greenPaint.setActionCommand("green");

        // blue paint button
        JButton bluePaint = new JButton();
        bluePaint.setBackground(Color.BLUE);
        bluePaint.setOpaque(true);
        bluePaint.setBorderPainted(false);
        leftPanel.add(bluePaint);  

        bluePaint.addActionListener(this);
        bluePaint.setActionCommand("blue");

        // Add color panel to holder JPanel
        holder.add(leftPanel, BorderLayout.WEST);

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2, 10, 0));

        JButton line = new JButton("Line");
        topPanel.add(line);
        line.addActionListener(this);
        line.setActionCommand("line");

        JButton circle = new JButton("Circle");
        topPanel.add(circle);
        circle.addActionListener(this);
        circle.setActionCommand("circle");

        holder.add(topPanel, BorderLayout.NORTH);

        // after finishing the PaintingPanel class (described later) add a
        // new object of this class as the CENTER panel
        centerPanel = new PaintingPanel();
        centerPanel.addMouseListener(this);

        holder.add(centerPanel, BorderLayout.CENTER);

        // And later you will add the chat panel to the SOUTH

        // Lastly, connect the holder to the JFrame
        setContentPane(holder);

        // And make it visible to layout all the components on the screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if(actionCommand.equals("red")) {
            color = Color.RED;
            System.out.println("red");
        } else if(actionCommand.equals("green")) {
            color = Color.GREEN;
            System.out.println("green");
        } else if(actionCommand.equals("blue")) {
            color = Color.BLUE;
            System.out.println("blue");
        } else if (actionCommand.equals("line")) {
            primitive = "line";
            System.out.println("line");
        } else if (actionCommand.equals("circle")) {
            primitive = "circle";
            System.out.println("circle");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
        start.x = e.getX();
        start.y = e.getY();
        System.out.println("Start X: " + start.x + ", Start Y: " + start.y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int endX = e.getX();
        int endY = e.getY();
        Point end = new Point(endX, endY);

        PaintingPrimitives shape;

        if(primitive.equals("line")) {
            shape = new Line(color, start, end);
        } else {
            shape = new Circle(color, start, end);
        }

        centerPanel.addPrimitive(shape);
        shape.draw(getGraphics());

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    //
    // MAIN
    //

    public static void main(String[] args) {
        new Painter();
    }
}