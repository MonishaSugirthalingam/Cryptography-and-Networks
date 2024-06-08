import java.math.BigInteger;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        // Step 5: Calculate n = pq
        System.out.print("Enter Valid P value from the list(p!=q) : ");
        String x=input.nextLine();
        System.out.print("Enter Valid Q value from the list(p!=q) : ");
        String y=input.nextLine();
        BigInteger p = new BigInteger(x); // Insert the actual value of p
        BigInteger q = new BigInteger(y); // Insert the actual value of q
        BigInteger n = p.multiply(q);
        System.out.println("n = " + n);

        // Step 6: Calculate z = (p-1)*(q-1)
        BigInteger z = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        System.out.println("z = " + z);

        // Step 7: Select decryption exponent d
        BigInteger e = BigInteger.valueOf(17); // Given encryption exponent
        BigInteger d = BigInteger.ONE; // Initialize d
        while (true) {
            if ((d.multiply(e)).mod(z).equals(BigInteger.ONE)) {
                break; // Found suitable d
            }
            d = d.add(BigInteger.ONE); // Increment d
        }
        System.out.println("d = " + d);

        // Step 8: Provide public key [e, n]
        System.out.println("Public key [e, n]: [" + e + ", " + n + "]");

        // Step 9: Wait for Alice to send the secret message (ciphertext)
        // Assume ciphertext received from Alice is denoted as c
        System.out.print("Enter the ciphertext received from Alice : ");
        String cipher=input.nextLine();
        // Step 10: Decrypt the message
        BigInteger c = new BigInteger(cipher); // Insert the actual value of the ciphertext
        BigInteger m = c.modPow(d, n);
        System.out.println("Decrypted message m = " + m);
    }
}
