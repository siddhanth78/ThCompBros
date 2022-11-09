
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Head {
    static final int PORT = 7069;
    static final int NUM_PORTS = 2;

    static int total;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Array for each client to keep track of sockets
        ArrayList<Socket> socketList = new ArrayList<>();

        // Array of each thread to join()
        ArrayList<Thread> threadList = new ArrayList<>();

        // getting client requests
        while (socketList.size() < NUM_PORTS) {
            ServerSocket ss = new ServerSocket(PORT);
            Socket socket = null;

            try {

                // Socket object to receive incoming client requests
                socket = ss.accept();
                socketList.add(socket);
                System.out.println("A new connection identified : " + socket);

            } catch (IOException e) {
                System.out.println("Exception in main...");
                e.printStackTrace();
            }
            ss.close();
        }

        // Create new thread
        for (Socket s : socketList) {
            NodeHandler thread = new NodeHandler(s, socketList);
            threadList.add(thread);
            thread.start();
            System.out.println("Thread assigned");
        }

        // Wait for all threads to join
        for(Thread th: threadList) {
            try {
                th.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Number of Primes within given range: " + total);
    }

    //
    // NodeHandler Inner Class
    //

    static class NodeHandler extends Thread {
        protected Socket socket;
        protected ArrayList<Socket> socketList;

        // Constructor
        public NodeHandler(Socket s, ArrayList<Socket> sList) {
            this.socket = s;
            this.socketList = sList;
            total = 0;
        }

        @Override
        public void run() {
            try {
                // obtaining input and out streams
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                int low = (int) ois.readObject();
                int high = (int) ois.readObject();
                int range = high - low;

                int numSockets = socketList.size();

                int index = 0;

                for (int i = 0; i < numSockets; i++) {
                    if (socket == socketList.get(i)) {
                        index = i;
                    }
                }

                int start = low + (range / numSockets) * index; // what out for int division
                int stop = low + (range / numSockets) * (index + 1);
                if (index + 1 == 4)
                    stop = high;

                oos.writeObject(start);
                oos.writeObject(stop);

                int threadTotal = (int) ois.readObject();
                addToTotal(threadTotal);

            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Exception in run...");
                e.printStackTrace();
            }
        }

        // Set Total without overriding
        public void addToTotal(int num) {
            total += num;
        }

    }
}
