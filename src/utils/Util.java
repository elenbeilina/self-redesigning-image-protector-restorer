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
}
