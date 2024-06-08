import java.util.Random;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        int B = 16; // Number of bits
        int e = 17; // Encryption exponent

        Random random = new Random();

        int count = 0; // Count of generated prime numbers
        int p;
        
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-10s\n", 
            "IRN(decimal)",
            "IRN(binary)",
            "p(decimal)",
            "p(binary)",
            "Is p prime?",
            "(p-1)%e!=0?");
        System.out.println();
        while (count < 7) {
            // Step 1: Generate a random 8-bit number
            int randomNum = random.nextInt(256); // 2^8 = 256
            // Step 2: Set the two highest bits and the lowest bit to 1
            p = randomNum | 0b11000001; // Setting the highest and lowest bits
            
            // Convert to binary strings
            String randomBinary = String.format("%8s", Integer.toBinaryString(randomNum)).replace(' ', '0');
            String pBinary = String.format("%8s", Integer.toBinaryString(p)).replace(' ', '0');
            
            // Step 3: Check if p is prime
            boolean isPrime = isPrime(p);
            
            // Step 4: Check if (p-1) is co-prime with e=17
            boolean isCoprime = (p - 1) % e != 0;
            
            // Print details
            System.out.printf("%-20d %-20s %-20d %-20s %-20s %-20s\n", 
                randomNum,
                randomBinary,
                p,
                pBinary,
                isPrime,
                isCoprime);
            
            if (isPrime && isCoprime) {
                count++;
            }
        }
    }

    // Method to check if a number is prime
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
