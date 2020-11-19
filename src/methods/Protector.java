package methods;

import components.Block;
import exceptions.EncodeException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Protector {

    public void protectImage(BufferedImage image, BufferedImage partToProtect, Block block, String key) {
        Encryptor encryptor = new Encryptor();
        try {
            ImageIO.write(partToProtect, "png", new File("protected.png"));
            File toProtect = new File("protected.png");

            Graphics graphics = image.getGraphics();
            graphics.setColor(Color.BLACK);
            graphics.fillRect(block.getX(), block.getY(), block.getWidth(), block.getHeight());

            encryptor.hideTheMessage(image, toProtect, key);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    public File restoreImage(BufferedImage image,String key) throws Exception {
        Descriptor descriptor = new Descriptor();

        return descriptor.decodeTheImage(image, key);
    }
}
