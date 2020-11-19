package methods;

import exceptions.EncodeException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Protector {

    public void protectImage(BufferedImage image, BufferedImage partToProtect) {
        Encryptor encryptor = new Encryptor();
        try {
            ImageIO.write(partToProtect, "png", new File("protected.png"));
            File toProtect = new File("protected.png");
            encryptor.hideTheMessage(image, toProtect);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    public File restoreImage(BufferedImage image) throws Exception {
        Descriptor descriptor = new Descriptor();

        return descriptor.decodeTheImage(image);
    }
}
