#include <iostream>
#include <iomanip>
#include <cstring>

// Constants for MD5 Transform
const unsigned int S[64] = {
    7, 12, 17, 22,
    5,  9, 14, 20,
    4, 11, 16, 23,
    6, 10, 15, 21
};
const unsigned int K[64] = {
    0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee,
    0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501,
    0x698098d8, 0x8b44f7af, 0xffff5bb1, 0x895cd7be,
    0x6b901122, 0xfd987193, 0xa679438e, 0x49b40821,
    0xf61e2562, 0xc040b340, 0x265e5a51, 0xe9b6c7aa,
    0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8,
    0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed,
    0xa9e3e905, 0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a,
    0xfffa3942, 0x8771f681, 0x6d9d6122, 0xfde5380c,
    0xa4beea44, 0x4bdecfa9, 0xf6bb4b60, 0xbebfbc70,
    0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x04881d05,
    0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665,
    0xf4292244, 0x432aff97, 0xab9423a7, 0xfc93a039,
    0x655b59c3, 0x8f0ccc92, 0xffeff47d, 0x85845dd1,
    0x6fa87e4f, 0xfe2ce6e0, 0xa3014314, 0x4e0811a1,
    0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391
};

class MD5 {
private:
    unsigned int state[4]; // A, B, C, D
    unsigned int buffer[16];
    unsigned long long int messageLength;
    unsigned char* data;

    void transform();

public:
    MD5();
    ~MD5();
    void update(const char* input, unsigned int length);
    void finalize();
    void printHash();
};

MD5::MD5() {
    // Initialize state variables
    state[0] = 0x67452301;
    state[1] = 0xefcdab89;
    state[2] = 0x98badcfe;
    state[3] = 0x10325476;

    messageLength = 0;
    data = new unsigned char[64];
}

MD5::~MD5() {
    delete[] data;
}

void MD5::update(const char* input, unsigned int length) {
    unsigned int i, index, partLen;

    // Compute number of bytes mod 64
    index = (unsigned int)((messageLength >> 3) % 64);

    // Update message length
    messageLength += (length << 3);

    // Number of bytes we need to fill in buffer
    partLen = 64 - index;

    // Transform as many times as possible.
    if (length >= partLen) {
        memcpy(&data[index], input, partLen);
        transform();

        for (i = partLen; i + 63 < length; i += 64)
            transform();

        index = 0;
    } else {
        i = 0;
    }

    // Buffer remaining input
    memcpy(&data[index], &input[i], length - i);
}

void MD5::finalize() {
    unsigned char bits[8];
    unsigned int index, padLen;

    // Save number of bits
    for (int i = 0; i < 8; i++) {
        bits[i] = (unsigned char)((messageLength >> (i * 8)) & 0xFF);
    }

    // Pad with one bit and zeros until 56 bytes mod 64
    index = (unsigned int)((messageLength >> 3) % 64);
    padLen = (index < 56) ? (56 - index) : (120 - index);
    update("\x80", 1);
    while (padLen--)
        update("\0", 1);

    // Append length in bits and transform
    for (int i = 0; i < 8; i++)
        update((char*)&bits[i], 1);

    transform();
}

void MD5::printHash() {
    for (int i = 0; i < 4; i++) {
        std::cout << std::hex << std::setw(8) << std::setfill('0') << state[i];
    }
    std::cout << std::endl;
}

void MD5::transform() {
    unsigned int a = state[0], b = state[1], c = state[2], d = state[3];
    unsigned int f, g;

    for (int i = 0; i < 64; i++) {
        if (i < 16) {
            f = (b & c) | ((~b) & d);
            g = i;
        } else if (i < 32) {
            f = (d & b) | ((~d) & c);
            g = (5 * i + 1) % 16;
        } else if (i < 48) {
            f = b ^ c ^ d;
            g = (3 * i + 5) % 16;
        } else {
            f = c ^ (b | (~d));
            g = (7 * i) % 16;
        }

        unsigned int temp = d;
        d = c;
        c = b;
        b = b + ((a + f + K[i] + buffer[g]) << S[i % 4] | ((a + f + K[i] + buffer[g]) >> (32 - S[i % 4])));
        a = temp;
    }

    state[0] += a;
    state[1] += b;
    state[2] += c;
    state[3] += d;
}

int main() {
    MD5 md5;
    md5.update("Hello, World!", strlen("Hello, World!"));
    md5.finalize();
    std::cout << "MD5 Hash: ";
    md5.printHash();
    return 0;
}