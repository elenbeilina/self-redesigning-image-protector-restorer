package methods;

import components.Block;
import exceptions.EncodeException;
import utils.HeaderUtils;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Encryptor {

    public void encryptHash(BufferedImage coverImage, Block partToEncrypt,
                               String messageToHide) {

            capacityCheck(coverImage, messageToHide);

            byte[] messageInBytes = messageToHide.getBytes();
            WritableRaster raster = coverImage.getRaster();

            SecurityManager securityManager = new SecurityManager("csf is the best");

            int x, y;
            int r,g,b;

            int[] arr;
            int[] result;
            int counter = 0;
            int data;
            int flag = securityManager.getPermutation();
            boolean keepEmbedding = true;

            //embedding
            for (y = partToEncrypt.getY(); y < partToEncrypt.getY() + partToEncrypt.getHeight() && keepEmbedding; y++) {
                for (x = partToEncrypt.getX(); x < partToEncrypt.getX() + partToEncrypt.getWidth(); x++) {
                    //per pixel
                    r = raster.getSample(x, y, 0);//red
                    g = raster.getSample(x, y, 1);//green
                    b = raster.getSample(x, y, 2);//blue

                    if (counter < messageInBytes.length) {//embed header
                        data = messageInBytes[counter];
                        data = securityManager.primaryCrypto(data);
                    } else {//embed file content
                        keepEmbedding = false;
                        break;
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
