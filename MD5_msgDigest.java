import java.io.*;

public class MD5_msgDigest {

    private static final int BLOCK_SIZE = 64;

    private static final int[] S = {
            7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
            5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20,
            4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
            6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21
    };

    private static final int[] K = new int[64];

    static {
        for (int i = 0; i < 64; i++) {
            K[i] = (int) (long) (Math.pow(2, 32) * Math.abs(Math.sin(i + 1)));
        }
    }

    public static void main(String[] args) {
    
        String message = "Monisha Sugirthalingam";
        System.out.println("Input String is : "+message);
        byte[] bytes = message.getBytes();
        long[] paddedMessage = padMessage(bytes);
        int[] md5 = generateMD5(paddedMessage);
        String md5Hex = convertToHex(md5);
        System.out.println("MD5 Hash: " + md5Hex);
    }

    private static long[] padMessage(byte[] bytes) {
        int originalLength = bytes.length;
        long bitLength = (long) originalLength * 8;

        int newLength = (originalLength % BLOCK_SIZE < 56) ? originalLength + (56 - (originalLength % BLOCK_SIZE))
                : originalLength + (BLOCK_SIZE - (originalLength % BLOCK_SIZE)) + 56;

        byte[] paddedBytes = new byte[newLength];
        System.arraycopy(bytes, 0, paddedBytes, 0, originalLength);
        paddedBytes[originalLength] = (byte) 0x80;

        for (int i = 0; i < 8; i++) {
            paddedBytes[newLength - 8 + i] = (byte) (bitLength & 0xff);
            bitLength >>= 8;
        }

        int paddedMessageLength = newLength / 4;
        long[] paddedMessage = new long[paddedMessageLength];
        for (int i = 0; i < paddedMessageLength; i++) {
            paddedMessage[i] = ((paddedBytes[i * 4] & 0xff)) |
                    ((paddedBytes[i * 4 + 1] & 0xff) << 8) |
                    ((paddedBytes[i * 4 + 2] & 0xff) << 16) |
                    ((paddedBytes[i * 4 + 3] & 0xff) << 24);
        }

        return paddedMessage;
    }

    private static int[] generateMD5(long[] paddedMessage) {
        int[] md5 = { 0x67452301, 0xefcdab89, 0x98badcfe, 0x10325476 };

        for (int i = 0; i < paddedMessage.length; i += 16) {
            int[] a = md5.clone();
            long[] chunk = new long[16];
            int chunkSize = Math.min(16, paddedMessage.length - i);
            System.arraycopy(paddedMessage, i, chunk, 0, chunkSize);

            for (int j = 0; j < 64; j++) {
                int f, g;
                if (j < 16) {
                    f = (a[1] & a[2]) | ((~a[1]) & a[3]);
                    g = j;
                } else if (j < 32) {
                    f = (a[3] & a[1]) | ((~a[3]) & a[2]);
                    g = (5 * j + 1) % 16;
                } else if (j < 48) {
                    f = a[1] ^ a[2] ^ a[3];
                    g = (3 * j + 5) % 16;
                } else {
                    f = a[2] ^ (a[1] | (~a[3]));
                    g = (7 * j) % 16;
                }

                f += a[0] + K[j] + chunk[g];
                a[0] = a[3];
                a[3] = a[2];
                a[2] = a[1];
                a[1] += Integer.rotateLeft(f, S[j]);
            }

            for (int j = 0; j < 4; j++) {
                md5[j] += a[j];
            }
        }

        return md5;
    }

    private static String convertToHex(int[] md5) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String hex = Integer.toHexString(md5[i]);
            while (hex.length() < 8) {
                hex = "0" + hex;
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
