package methods;

import components.Block;

import java.awt.image.BufferedImage;

public class Protector {

    public void protectImage(BufferedImage image, int n) {

        int blockWidth = image.getWidth() / n;
        int blockHeight = image.getHeight() / n;

        int x;
        int y = 0;

        for (int i = 0; i < n; i++) {
            x = 0;
            for (int j = 0; j < n; j++) {
                try {
                    System.out.println("protecting block: " + i + " " + j);

                    BufferedImage subImage = image.getSubimage(x, y, blockWidth, blockHeight);
                    ImageComparison comparison = new ImageComparison();
                    String hash = comparison.getHash(subImage);

                    Block block = new Block(x, y, blockWidth, blockHeight);
                    Encryptor encryptor = new Encryptor();
                    encryptor.encryptHash(image, block, hash);

                    x += blockWidth;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            y += blockHeight;
        }
    }
}
