package methods;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class Descriptor {

    public String decodeTheImage(BufferedImage coverImage) throws Exception {
        Raster raster = coverImage.getData();

        int w, h;
        w = coverImage.getWidth();
        h = coverImage.getHeight();

        int x, y;
        int r, g, b;
        int[] arr;
        //pHash length = 71
        byte[] bytes = new byte[71];
        int counter = 0;
        int data;

        SecurityManager securityManager = new SecurityManager("csf is the best");
        int flag = securityManager.getPermutation();

        //extraction
        for (y = 0; y < h; y++) {
            for (x = 0; x < w; x++) {
                //per pixel
                r = raster.getSample(x, y, 0);//red band
                g = raster.getSample(x, y, 1);//green band
                b = raster.getSample(x, y, 2);//blue band

                //extract the bits
                arr = ByteProcessor.extract(r, g, b, flag);
                //combine to form a byte
                data = ByteProcessor.combine(arr, flag);


                if (counter <= bytes.length) {
                    data = securityManager.primaryCrypto(data);
                    bytes[counter] = (byte) data;
                } else {
                    break;
                }

                counter++;
                flag = (flag + 1) % 3 + 1;
            }

        }
        return new String(bytes);
    }
}