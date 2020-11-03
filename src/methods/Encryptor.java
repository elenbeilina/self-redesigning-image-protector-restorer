package methods;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static utils.Util.convertToBits;
import static utils.Util.getByteData;

public class Encryptor {

    public void hideTheMessage(BufferedImage originalImage,
                           String message) {
        int[] bitArray = {0,0,0,0,1,0,0,0};

        byte[] image = getByteData(originalImage);
        byte[] payload = message.getBytes();
        int offset = 0;
        int imageLength = image.length;
        boolean[] data = convertToBits(payload);
        int dataLength = data.length;
        int dataOverFlag = 0;
        for (int i = 0; i < imageLength && dataOverFlag == 0; i++) {
            for (int j = 7; j >= 0  && dataOverFlag == 0; j--) {
                if (bitArray[j] == 1) {
                    int mask = returnMask(j);
                    image[i] = (byte) ((image[i] & mask));
                    if (data[offset++]) {
                        image[i] = (byte) (image[i] | ~mask);
                    }
                    if (offset >= dataLength) {
                        dataOverFlag = 1;
                    }
                }
            }
        }
    }

    private int returnMask(int bit) {
        int mask = 0xFF;
        switch (bit) {
            case 0:
                mask = 0xFE;
                break;
            case 1:
                mask = 0xFD;
                break;
            case 2:
                mask = 0xFB;
                break;
            case 3:
                mask = 0xF7;
                break;
            case 4:
                mask = 0xEF;
                break;
            case 5:
                mask = 0xDF;
                break;
            case 6:
                mask = 0xBF;
                break;
            case 7:
                mask = 0x7F;
                break;
        }
        return mask;
    }

}
