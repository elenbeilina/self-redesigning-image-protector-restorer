package utils;

import components.ImagePanel;

import javax.swing.*;
import java.awt.image.BufferedImage;

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
}
