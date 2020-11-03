package methods;

import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

import static utils.Util.getByteData;

public class Descriptor {

    public String decodeTheMessage(BufferedImage coverImage) {
        byte[] image = getByteData(coverImage);
        int offset = 0;
        int imageLength = image.length;

        int[] bitArray = {0,0,0,0,1,0,0,0};

        // counting how many bits are modified per byte
        int count = 0;
        for (int k : bitArray) {
            if (k == 1) {
                count++;
            }
        }

        boolean[] data = new boolean[imageLength * count];
        for (byte b : image) {
            for (int j = 7; j >= 0; j--) {
                if (bitArray[j] == 1) {
                    int singleBit = (b >> j) & 1;
                    data[offset++] = singleBit == 1;
                }
            }
        }

        // converting boolean array to byte array
        int secretMessageLength = (imageLength * count) / 8;
        byte[] secretMessage = new byte[secretMessageLength];
        for (int i = 0; i < secretMessageLength; i++) {
            for (int bit = 0; bit < 8; bit++) {
                if (data[i * 8 + bit]) {
                    secretMessage[i] |= (128 >> bit);
                }
            }
        }
        try {
            return new String(secretMessage, StandardCharsets.US_ASCII);
        } catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}