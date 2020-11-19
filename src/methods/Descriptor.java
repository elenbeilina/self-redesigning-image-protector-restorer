package methods;

import utils.HeaderUtils;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

public class Descriptor {

    public File decodeTheImage(BufferedImage coverImage, String key) throws Exception {
        Raster raster = coverImage.getData();

        int w, h;
        w = coverImage.getWidth();
        h = coverImage.getHeight();

        int x, y;
        int r, g, b;
        int[] arr;
        int counter = 0;
        int data;

        SecurityManager securityManager = new SecurityManager(key);
        int flag = securityManager.getPermutation();
        int fileSize = 0;
        String header = "";

        FileOutputStream fout = null;

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

                if (counter < HeaderUtils.HEADER_LENGTH) {//embed header
                    header = header + (char) data;

                    if (counter == HeaderUtils.HEADER_LENGTH - 1) {
                        //we have the header
                        //extract the file name
                        String extractedFile = HeaderUtils.getFileName(header);
                        fileSize = HeaderUtils.getFileSize(header);
                        System.out.println("FILESIZE : " + fileSize);

                        fout = new FileOutputStream(extractedFile);
                    }
                } else {//extract
                    data = securityManager.primaryCrypto(data);
                    Objects.requireNonNull(fout).write(data);

                    if (counter == fileSize + HeaderUtils.HEADER_LENGTH) {
                        System.out.println("***" + counter);
                        fout.close();

                        return new File(HeaderUtils.getFileName(header));
                    }

                }

                counter++;
                flag = (flag + 1) % 3 + 1;
            }

        }
        return null;
    }
}