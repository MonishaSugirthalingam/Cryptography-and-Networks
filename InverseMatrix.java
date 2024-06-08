public class Main {
    
    static class ExtendedGCDResult {
        long gcd, x, y;

        public ExtendedGCDResult(long gcd, long x, long y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }
    }

    static ExtendedGCDResult extendedGCD(long a, long b) {
        if (a == 0)
            return new ExtendedGCDResult(b, 0, 1);
        else {
            ExtendedGCDResult result = extendedGCD(b % a, a);
            long temp = result.x;
            result.x = result.y - (b / a) * result.x;
            result.y = temp;
            return result;
        }
    }

    static long modInverse(long a, long n) {
        ExtendedGCDResult result = extendedGCD(a, n);
        if (result.gcd != 1) {
            System.out.println(a + " does not have an inverse modulo " + n);
            return -1;
        } else {
            return (result.x % n + n) % n; // Ensure positive result
        }
    }

    public static void main(String[] args) {
        System.out.println("Inverse of 2 mod 7: " + modInverse(2, 7));
        System.out.println("Inverse of 6 mod 15: " + modInverse(6, 15));
    }
}
