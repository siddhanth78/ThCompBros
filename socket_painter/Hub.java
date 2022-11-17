import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Hub {

    private static ArrayList<PaintingPrimitives> hubPrimitives;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final int PORT = 4200;

        // Array for each client to keep track of sockets
        ArrayList<Socket> socketList = new ArrayList<>();

        // Array of each thread to join()
        ArrayList<Thread> threadList = new ArrayList<>();

        // getting client requests
        while (true) {
            ServerSocket ss = new ServerSocket(PORT);
            Socket socket = null;

            try {
                // Socket object to receive incoming client requests
                socket = ss.accept();
                socketList.add(socket);
                System.out.println("A new connection identified : " + socket);

                // Create thread for new Client
                ClientHandler thread = new ClientHandler(socket);
                threadList.add(thread);
                thread.start();
                System.out.println("Thread assigned");

            } catch (IOException e) {
                System.out.println("Exception in main...");
                e.printStackTrace();
            }
            ss.close();
        }

        // // Wait for all threads to join
        // for (Thread th : threadList) {
        //     try {
        //         th.join();
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }

    }

    //
    // ClientHandler Inner Class
    //

    private static class ClientHandler extends Thread {
        protected Socket socket;

        // Constructor
        public ClientHandler(Socket s) {
            this.socket = s;
        }

        @Override
        public void run() {
            try {
                // obtaining input stream
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                ArrayList<PaintingPrimitives> primitives = new ArrayList<>();

            
                primitives = (ArrayList) ois.readObject();;
                setPanel(primitives);
                

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Exception in run...");
                e.printStackTrace();
            }
        }

        private synchronized void setPanel(ArrayList<PaintingPrimitives> p) {
            hubPrimitives = p;
        }
    }
}