package methods;

import exceptions.EncodeException;
import utils.HeaderUtils;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Encryptor {

    public void hideTheMessage(BufferedImage coverImage,
                               File imageToHide) throws IOException {
        {

            capacityCheck(coverImage, imageToHide);

            String header = HeaderUtils.formHeader(imageToHide.getName(), imageToHide.length());

            WritableRaster raster = coverImage.getRaster();
            FileInputStream srcFile = new FileInputStream(imageToHide);

            SecurityManager securityManager = new SecurityManager("csf is the best");

            int x, y;
            int r, g, b;
            int[] arr;
            int[] result;
            int counter = 0;
            int data;
            int flag = securityManager.getPermutation();
            boolean keepEmbedding = true;

            //embedding
            for (y = 0; y < coverImage.getHeight() && keepEmbedding; y++) {
                for (x = 0; x < coverImage.getWidth(); x++) {
                    //per pixel
                    r = raster.getSample(x, y, 0);//red
                    g = raster.getSample(x, y, 1);//green
                    b = raster.getSample(x, y, 2);//blue

                    if (counter < HeaderUtils.HEADER_LENGTH) {//embed header
                        data = header.charAt(counter);
                    } else {//embed file content
                        data = srcFile.read();
                        if (data == -1) {
                            keepEmbedding = false;
                            srcFile.close();
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
    }

    private void capacityCheck(BufferedImage coverImage, File imageToHide ){
        int w, h, tot;
        w = coverImage.getWidth();
        h = coverImage.getHeight();

        tot = w * h;
        if (tot < imageToHide.length() + HeaderUtils.HEADER_LENGTH)
            throw new EncodeException("Embedding capacity of " + coverImage + " is less than the size of " + imageToHide);

    }
}
