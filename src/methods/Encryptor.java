package methods;

import java.awt.image.BufferedImage;

import static utils.Util.*;

public class Encryptor {

    public void hideTheMessage(BufferedImage originalImage,
                               BufferedImage imageToHide) {
        int[] bitArray = {1, 1, 0, 0, 0, 0, 0, 0};

        if (originalImage.getWidth() < imageToHide.getWidth()
        || originalImage.getHeight()< imageToHide.getHeight()){
            throw new ArrayIndexOutOfBoundsException("Pic smaller image to hide");
        }

        byte[] image = getByteData(originalImage);
        byte[] payload = getByteData(imageToHide);
        int offset = 0;
        int imageLength = image.length;
        boolean[] data = convertToBits(payload);
        int dataLength = data.length;
        int dataOverFlag = 0;
        for (int i = 0; i < imageLength && dataOverFlag == 0; i++) {
            for (int j = 7; j >= 0 && dataOverFlag == 0; j--) {
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
}
