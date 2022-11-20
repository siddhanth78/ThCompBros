import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class Painter extends JFrame implements ActionListener, MouseInputListener {

    static final int PORT = 4200;
    private static Socket s;

    private Color color;
    private String primitive;

    private Point start;

    private PaintingPanel centerPanel;

    protected ObjectOutputStream oos;

    public Painter() {

        // Initialize private instance variables
        color = Color.RED;
        primitive = "line";

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

        // Center Panel
        centerPanel = new PaintingPanel();
        centerPanel.addMouseListener(this);

        holder.add(centerPanel, BorderLayout.CENTER);

        // And later you will add the chat panel to the SOUTH

        // Connect holder to JFrame
        setContentPane(holder);

        try {
            s = new Socket("localhost", PORT);
            System.out.println("Connected");
            oos = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

       

        // Make it visible to layout all the components on the screen
        setVisible(true);

        // Spawn thread to recieve primtives from Hub
        HubConnector h = new HubConnector(this);
        h.start();
    }

    //
    //
    //
    //
    //

    // Hub Connection class
        // should write what mouseListener is writing
        // print confirmation of recieved primitive

    // uuid
    // logic

    private static class HubConnector extends Thread {

        protected ObjectInputStream ois;
        //protected ObjectOutputStream oos;
        protected Painter painter;

        public HubConnector(Painter pa) {
            this.painter = pa;

            try {
                //oos = new ObjectOutputStream(s.getOutputStream());
                ois = new ObjectInputStream(s.getInputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public synchronized PaintingPrimitives getMessage() {
            PaintingPrimitives p = null;
            try {
                p = (PaintingPrimitives) ois.readObject();
                System.out.println("obj read from Hub");
            } catch (IOException | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return p;
        }

        @Override
        public void run() {
            while (true) {
                // get primitive from Hub
                painter.centerPanel.addPrimitive(getMessage());

                painter.centerPanel.paintComponents(painter.getGraphics());
                painter.centerPanel.validate();
                painter.centerPanel.repaint();
            }
        }
    }

    //
    // Mouse Listeners
    //

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if(actionCommand.equals("red")) {
            color = Color.RED;
            //System.out.println("red");
        } else if(actionCommand.equals("green")) {
            color = Color.GREEN;
            //System.out.println("green");
        } else if(actionCommand.equals("blue")) {
            color = Color.BLUE;
            //System.out.println("blue");
        } else if (actionCommand.equals("line")) {
            primitive = "line";
        } else if (actionCommand.equals("circle")) {
            primitive = "circle";
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
        start = new Point();
        start.x = e.getX();
        start.y = e.getY();
        //System.out.println("Start X: " + start.x + ", Start Y: " + start.y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int endX = e.getX();
        int endY = e.getY();
        Point end = new Point(endX, endY);

        PaintingPrimitives p = null;

        if(primitive.equals("line")) {
            p = new Line(color, start, end);
        } else {
            p = new Circle(color, start, end);
        }

        centerPanel.addPrimitive(p);

        try {
            // DataOutputStream
            
            //ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            oos.writeObject(p);
            System.out.println("Write primitive to Hub...");
            //centerPanel = (PaintingPanel) ois.readObject();

        } catch (IOException exc) {
            exc.printStackTrace();
        }

        centerPanel.paintComponents(getGraphics());
        centerPanel.validate();
        centerPanel.repaint();
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