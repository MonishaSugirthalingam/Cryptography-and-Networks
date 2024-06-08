public class SHA256 {

    private static final int[] K = {
            0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
            0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
            0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
            0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
            0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
            0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
            0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
            0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
    };

    public static void main(String[] args) {
        String message = "Monisha Sugirthalingam";
        System.out.println("Input String is : "+message);
        byte[] messageBytes = message.getBytes();
        byte[] hash = sha256(messageBytes);
        System.out.println("SHA-256 hash: " + bytesToHex(hash));
    }

    private static byte[] sha256(byte[] message) {
        int initialMessageLength = message.length * 8;
        int appendedOneIndex = message.length;
        byte[] appendedMessage = new byte[appendedOneIndex + 64];

        System.arraycopy(message, 0, appendedMessage, 0, message.length);
        appendedMessage[appendedOneIndex] = (byte) 0x80;

        // Appending length
        for (int i = 0; i < 8; i++) {
            appendedMessage[appendedMessage.length - 8 + i] = (byte) ((initialMessageLength >>> (56 - i * 8)) & 0xFF);
        }

        int[] words = new int[64];
        for (int i = 0; i < appendedMessage.length / 4; i++) {
            int b0 = (appendedMessage[i * 4] & 0xFF) << 24;
            int b1 = (appendedMessage[i * 4 + 1] & 0xFF) << 16;
            int b2 = (appendedMessage[i * 4 + 2] & 0xFF) << 8;
            int b3 = appendedMessage[i * 4 + 3] & 0xFF;
            words[i] = b0 | b1 | b2 | b3;
        }

        int[] hashValues = new int[]{
                0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a,
                0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19
        };
        //compression function
        int a, b, c, d, e, f, g, h;
        for (int i = 0; i < words.length; i += 16) {
            int[] temp = hashValues.clone();
            for (int j = 0; j < 64; j++) {
                if (j < 16) {
                    words[j] = words[i + j];
                } else {
                    words[j] = sigma1(words[j - 2]) + words[j - 7] + sigma0(words[j - 15]) + words[j - 16];
                }
                f = temp[4] + ch(temp[4], temp[5], temp[6]) + temp[7] + K[j] + words[j];
                g = maj(temp[0], temp[1], temp[2]) + temp[3];
                h = f + g;
                temp[7] = temp[6];
                temp[6] = temp[5];
                temp[5] = temp[4];
                temp[4] = temp[3] + f;
                temp[3] = temp[2];
                temp[2] = temp[1];
                temp[1] = temp[0];
                temp[0] = h;
            }
            for (int j = 0; j < 8; j++) {
                hashValues[j] += temp[j];
            }
        }

        byte[] hash = new byte[32];
        for (int i = 0; i < hashValues.length; i++) {
            int intHash = hashValues[i];
            hash[i * 4] = (byte) ((intHash >>> 24) & 0xFF);
            hash[i * 4 + 1] = (byte) ((intHash >>> 16) & 0xFF);
            hash[i * 4 + 2] = (byte) ((intHash >>> 8) & 0xFF);
            hash[i * 4 + 3] = (byte) (intHash & 0xFF);
        }

        return hash;
    }

    private static int ch(int x, int y, int z) {
        return (x & y) ^ (~x & z);
    }

    private static int maj(int x, int y, int z) {
        return (x & y) ^ (x & z) ^ (y & z);
    }

    private static int sigma0(int x) {
        return Integer.rotateRight(x, 7) ^ Integer.rotateRight(x, 18) ^ (x >>> 3);
    }

    private static int sigma1(int x) {
        return Integer.rotateRight(x, 17) ^ Integer.rotateRight(x, 19) ^ (x >>> 10);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
