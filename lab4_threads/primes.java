
public class primes {
    public static void main(String[] args) {
        primes p = new primes();
        System.out.println("Is 27 Prime: " + p.isPrime(27));
        System.out.println("Primes in range 10-100: " + p.numPrimes(10, 100));
    }

    private int isPrime(int num) {
        for(int i = 2; i < num; i++) {
            if(num % i == 0)
                return 0;
        }
        return 1;
    }

    private int numPrimes(int low, int high) {
        int count = 0;
        for(int i = low; i < high; i++) {
            count += isPrime(i);
        }
        return count;
    }
}
