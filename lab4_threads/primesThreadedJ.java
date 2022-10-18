public class primesThreadedJ {

    private static int count;
    public static void main(String[] args) {

        count = 0;
        int low = 10;
        int high = 100;
        int range = high - low;

        int numThreads = 4;
        Thread[] ths = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = low + (range / numThreads) * i; // what out for int division
            int stop = low + (range / numThreads) * (i + 1);
            if(i + 1 == 4)
                stop = high;
            
            numPrimesThread np = new numPrimesThread(start, stop, count);
            Thread th = new Thread(np);
            ths[i] = th;
            th.start(); // fork
        }

        // wait for all threads to join
        for(int i = 0; i < numThreads; i++) {
            try {
                ths[i].join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Primes in range " + low + "-" + high + ": " + count);
    }

    //
    // INNER CLASS
    //

    static class numPrimesThread implements Runnable {
        private int start;
        private int stop;

        public numPrimesThread(int start, int stop, int count) {
            this.start = start;
            this.stop = stop;                   
        }

        @Override
        public void run() {
            System.out.println("start: " + start + ", stop: " + stop);
            for (int i = start; i < stop; i++) {
                count += isPrime(i);
            }
            System.out.println("count: " + count);
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