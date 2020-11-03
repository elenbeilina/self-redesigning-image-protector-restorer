package utils;

import components.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

public class Util {
    public static String MESSAGE_ENCRYPTION_ERROR = "Encryption error occurred";
    public static String MESSAGE_DECRYPTION_ERROR = "Decryption error occurred";
    public static String MESSAGE_UNEXPECTED_ERROR = "Unexpected error occurred";
    public static String MESSAGE_IO_ERROR = "I/O error occurred";

    public static String MESSAGE_ENCRYPTION_COMPLETED = "Encryption completed";
    public static String MESSAGE_DECRYPTION_COMPLETED = "Decryption completed. \n\nDecrypted text:";

    public static BufferedImage toBufferedImage(ImagePanel panel) {
        JLabel label = panel.getImage();
        Icon icon = label.getIcon();
        return new BufferedImage(icon.getIconWidth(),
                icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
    }

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
}
