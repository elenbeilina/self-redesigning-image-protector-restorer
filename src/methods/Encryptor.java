package methods;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static utils.Converter.txtToByte;

public class Encryptor {

    public void hideTheMessage(String message, BufferedImage originalImage, File fileToEncrypt) throws Exception {
        int i = 0;
        int j = 0;

        byte[] txt = txtToByte(message);
        for (byte b : txt) {
            for (int k = 7; k >= 0; k--) {
                Color c = new Color(originalImage.getRGB(j, i));
                byte blue = (byte) c.getBlue();
                int red = c.getRed();
                int green = c.getGreen();

                int bitVal = (b >>> k) & 1;
                blue = (byte) ((blue & 0xFE) | bitVal);

                Color newColor = new Color(red,
                        green, (blue & 0xFF));
                originalImage.setRGB(j, i, newColor.getRGB());
                j++;
            }
            i++;
        }
        System.out.println("Text Hidden");

        ImageIO.write(originalImage, "png", fileToEncrypt);
    }

}
