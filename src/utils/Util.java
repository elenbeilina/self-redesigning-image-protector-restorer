package utils;

import components.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Util {
    public static String MESSAGE_ENCRYPTION_ERROR = "Encryption error occurred";
    public static String MESSAGE_DECRYPTION_ERROR = "Decryption error occurred";
    public static String MESSAGE_UNEXPECTED_ERROR = "Unexpected error occurred";
    public static String MESSAGE_IO_ERROR = "I/O error occurred";

    public static String MESSAGE_ENCRYPTION_COMPLETED = "Encryption completed";
    public static String MESSAGE_SIMILARITIES_COMPLETED = "Perceptive hash and hamming distance analysis completed. \n\nDistance:";

    public static BufferedImage convertImage(BufferedImage originalImage) {
        int newImageType = originalImage.getType();

        if (newImageType == BufferedImage.TYPE_INT_RGB
                || newImageType == BufferedImage.TYPE_INT_BGR) {
            newImageType = BufferedImage.TYPE_3BYTE_BGR;
        } else if (newImageType == BufferedImage.TYPE_INT_ARGB ||
                newImageType == BufferedImage.TYPE_CUSTOM) {
            newImageType = BufferedImage.TYPE_4BYTE_ABGR;
        } else if (newImageType == BufferedImage.TYPE_INT_ARGB_PRE) {
            newImageType = BufferedImage.TYPE_4BYTE_ABGR_PRE;
        } else {
            // no need to convert original image
            return originalImage;
        }
        BufferedImage newImage = new BufferedImage(originalImage.getWidth(),
                originalImage.getHeight(), newImageType);
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0, 0, null);
        g.dispose();
        return newImage;
    }

    public static boolean[] convertToBits(byte payload[]) {
        boolean result[] = new boolean[8 * payload.length];
        int offset = 0;
        for (byte b: payload) {
            for (int i = 7; i >= 0; i--) {
                int singleBit = (b >> i) & 1;
                if (singleBit == 1) {
                    result[offset++] = true;
                } else {
                    result[offset++] = false;
                }
            }
        }
        return result;
    }

    public static byte[] getByteData(BufferedImage userSpaceImage) {
        WritableRaster raster = userSpaceImage.getRaster();
        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
        return buffer.getData();
    }

    public static int[] getIntData(BufferedImage image) {
        return ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    }

    public static BufferedImage getImage(byte[] data) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);

        return ImageIO.read(bis);
    }

    public static int returnMask(int bit) {
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
