package methods;

import exceptions.DecodeException;

import java.awt.image.BufferedImage;

import static java.awt.Color.RED;
import static utils.Util.getByteData;

public class Descriptor {

    public byte[] decodeTheImage(BufferedImage coverImage) {
        byte[] image = getByteData(coverImage);
        int offset = 0;
        int imageLength = image.length;

        int[] bitArray = {1, 1, 0, 0, 0, 0, 0, 0};

        // counting how many bits are modified per byte
        int count = 0;
        for (int k : bitArray) {
            if (k == 1) {
                count++;
            }
        }

        boolean[] data = new boolean[imageLength * count];
        for (byte value : image) {
            for (int j = 7; j >= 0; j--) {
                if (bitArray[j] == 1) {
                    int singleBit = (value >> j) & 1;
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

                    int rgb = RED.getRed();
                    image[i] = (byte) rgb;
                }
            }
        }
        try {
            return secretMessage;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DecodeException("");
        }
    }
}