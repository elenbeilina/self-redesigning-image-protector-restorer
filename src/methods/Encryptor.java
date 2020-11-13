package methods;

import exceptions.EncodeException;
import utils.HeaderUtils;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Encryptor {

    public void hideTheMessage(BufferedImage coverImage,
                               String messageToHide) {
        {
            capacityCheck(coverImage, messageToHide);

            byte[] messageInBytes = messageToHide.getBytes();
            WritableRaster raster = coverImage.getRaster();

            SecurityManager securityManager = new SecurityManager("csf is the best");

            int x, y;
            int r = 0;
            int g = 0;
            int b = 0;
            int[] arr;
            int[] result;
            int counter = 0;
            int data = 0;
            int flag = securityManager.getPermutation();
            boolean keepEmbedding = true;

            //embedding
            for (y = 0; y < coverImage.getHeight() && keepEmbedding; y++) {
                for (x = 0; x < coverImage.getWidth(); x++) {
                    //per pixel
                    r = raster.getSample(x, y, 0);//red
                    g = raster.getSample(x, y, 1);//green
                    b = raster.getSample(x, y, 2);//blue

                    if (counter <= messageInBytes.length) {//embed header
                        data = messageInBytes[counter];
                    } else {//embed file content
                        keepEmbedding = false;
                        break;
                    }
                    data = securityManager.primaryCrypto(data);
                }

                arr = ByteProcessor.slice(data, flag);
                result = ByteProcessor.merge(r, g, b, arr, flag);

                //update the raster
                raster.setSample(x, y, 0, result[0]);
                raster.setSample(x, y, 1, result[1]);
                raster.setSample(x, y, 2, result[2]);

                counter++;
                flag = (flag + 1) % 3 + 1;
            }

        }

    }

    private void capacityCheck(BufferedImage coverImage, String messageToHide) {
        int w, h, tot;
        w = coverImage.getWidth();
        h = coverImage.getHeight();

        tot = w * h;
        if (tot < messageToHide.length() + HeaderUtils.HEADER_LENGTH)
            throw new EncodeException("Embedding capacity of " + coverImage + " is less than the size of " + messageToHide);

    }
}
