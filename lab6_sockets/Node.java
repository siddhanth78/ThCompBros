
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Node {
    static final int PORT = 7069;

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", PORT);
            System.out.println("Connected");

            // DataOutputStream
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            int low = 10;
            int high = 100;

            oos.writeObject(low);
            oos.writeObject(high);
            int start = (int) ois.readObject();
            int stop = (int) ois.readObject();

            int count = 0;

            for (int i = start; i < stop; i++) {
                count += isPrime(i);
            }

            System.out.println("Number of Primes found: " + count);

            oos.writeObject(count);

            s.close();

        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //
    // METHODS
    //

    static int isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0)
                return 0;
        }
        return 1;
    }

}
