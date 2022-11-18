import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;

public class Hub {

    private ArrayList<ClientHandler> clients;
    private ArrayList<PaintingPrimitives> hubPrimitives;

    public static void main(String[] args) {
        try {
            new Hub();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Hub() throws IOException {
        final int PORT = 4200;
        ServerSocket ss = new ServerSocket(PORT);

        hubPrimitives= new ArrayList<>();

        // Array of each thread to join()
        clients = new ArrayList<>();

        // getting client requests
        while (true) {
            try {
                // Socket object to receive incoming client requests
                Socket socket = ss.accept();
                System.out.println("A new connection identified : " + socket);

                // Create thread for new Client
                ClientHandler c = new ClientHandler(socket, this); // pass in hub - main new Hub()
                // Clients...
                // hub.putMessage(ois.readObj())
                clients.add(c);

                System.out.println(c);
                c.start();
                System.out.println("Thread assigned");

                // Send all current primitives to new Client
                for (PaintingPrimitives p : hubPrimitives) {
                    c.putMessage(p);
                }

                // Hub always running getMessage()
                // if getMessage() send primitive to clientHandler

            } catch (IOException e) {
                System.out.println("Exception in main...");
                e.printStackTrace();
            }

        }

        // ss.close();

        // // Wait for all threads to join
        // for (Thread th : threadList) {
        // try {
        // th.join();
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // }

    }

    public synchronized void putMessage(PaintingPrimitives p, ClientHandler cin) {
        
        // Thought !?
            // don't draw in mouseListener
            // draw after Client recieves new primitive
                // then no double drawing :)
                // delay tho :(
        
        hubPrimitives.add(p);

        for(ClientHandler c: clients) {
            if(c != cin)
                c.putMessage(p);
        }
    }

    //
    // ClientHandler Inner Class
    //

    private static class ClientHandler extends Thread {
        protected Socket socket;
        protected Hub hub;
        protected ObjectOutputStream oos;
        protected ObjectInputStream ois;

        // Constructor
        public ClientHandler(Socket s, Hub h) {
            this.socket = s;
            this.hub = h;

            try {
                oos = new ObjectOutputStream(s.getOutputStream());
                ois = new ObjectInputStream(s.getInputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public synchronized void putMessage(PaintingPrimitives p) {
            try {
                oos.writeObject(p);
                System.out.println("write obj to Client");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                // obtaining input stream

                // responsible fro reading and writting all primitives
                // reads primitives from client
                // fowards to main via putMessage()

                // get message from main
                // sends message to draw client
                while (true) {

                    // when client connects send everything, when somebody writes send new primitive
                    // dont double draw - uuid to get rid of double draws

                    // uuid - unique random number across multiple machines
                    // Every primitive gets uuid ??

                    // read single object
                    // put it to the hub - add to arrayList, push to primitives
                    

                    // forward primitve to Hub
                    try {
                        PaintingPrimitives p = (PaintingPrimitives) ois.readObject();
                        hub.putMessage(p, this);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    ois.reset();

                    // read and write message quese

                    // try catch
                    // if client gets message - someone has sent message and hub is calling all
                    // clients to draw
                    // push to that client

                    // if client sends

                }

            } catch (IOException io) {
                System.out.println("Exception in run...");
                io.printStackTrace();
            }
        }
    }
}