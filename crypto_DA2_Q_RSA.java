import java.util.Random;

public class Main{
    public static void main(String[] args) {
        int B = 16; // Number of bits
        int e = 17; // Encryption exponent

        Random random = new Random();
       
        int count = 0; // Count of generated prime numbers
        int q;

        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                "IRN(decimal)",
                "IRN(binary)",
                "q (decimal)",
                "q (binary)",
                "Is q prime?",
                "(q-1)%e != 0?");

        while (count < 7) {
            //step 1
             int randomNum = random.nextInt(256);
            // Step 2: Set the two highest bits and the lowest bit to 1
            q = randomNum | 0b11000001; // Setting the highest and lowest bits

            // Convert to binary strings
            String randomBinary = String.format("%8s", Integer.toBinaryString(randomNum)).replace(' ', '0');
            String qBinary = String.format("%8s", Integer.toBinaryString(q)).replace(' ', '0');

            // Step 3: Check if q is prime
            boolean isPrime = isPrime(q);

            // Step 4: Check if (q-1) is co-prime with e=17
            boolean isCoprime = (q - 1) % e != 0;

            // Print details
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                    randomNum,
                    randomBinary,
                    q,
                    qBinary,
                    isPrime,
                    isCoprime);

            if (isPrime && isCoprime) {
                count++;
            }
            randomNum += 2; // Increment the random number
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
