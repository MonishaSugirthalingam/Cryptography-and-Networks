import java.math.BigInteger;
public class Main {
    public static void main(String[] args) {
        String hexString = "24BC1462D"; // Example hexadecimal string
        int shiftBits = 7; // Example number of bits to shift

        // Convert the hexadecimal string to a binary string
        String binaryString = hexToBinary(hexString);

        // Perform the circular left shift
        String shiftedBinaryString = circularLeftShift(binaryString, shiftBits);

        // Convert the shifted binary string back to a hexadecimal string
        String shiftedHexString = binaryToHex(shiftedBinaryString);

        // Print the result
        System.out.println("Original Hex String: " + hexString);
        System.out.println("Shifted Hex String: " + shiftedHexString);
    }

    // Function to convert hexadecimal string to binary string
    public static String hexToBinary(String hexString) {
        String binaryString = new BigInteger(hexString, 16).toString(2);
        while (binaryString.length() < hexString.length() * 4) {
            binaryString = "0" + binaryString;
        }
        return binaryString;
    }

    // Function to convert binary string to hexadecimal string
    public static String binaryToHex(String binaryString) {
        String hexString = new BigInteger(binaryString, 2).toString(16).toUpperCase();
        return hexString;
    }

    // Function to perform circular left shift on binary string
    public static String circularLeftShift(String binaryString, int shiftBits) {
        int length = binaryString.length();
        shiftBits = shiftBits % length; // Ensure shiftBits is within the range of string length
        String shiftedBinaryString = binaryString.substring(shiftBits) + binaryString.substring(0, shiftBits);
        return shiftedBinaryString;
    }
}
