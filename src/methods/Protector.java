package methods;

import components.Block;

import java.awt.image.BufferedImage;

public class Protector {

    private static final int n = 3;

    public void protectImage(BufferedImage image) {

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
                    Hamming comparison = new Hamming();
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

    public void authenticatingImage(BufferedImage image){
        int blockWidth = image.getWidth() / n;
        int blockHeight = image.getHeight() / n;

        int x;
        int y = 0;

        for (int i = 0; i < n; i++) {
            x = 0;
            for (int j = 0; j < n; j++) {
                try {
                    System.out.println("authenticating block: " + i + " " + j);

                    BufferedImage subImage = image.getSubimage(x, y, blockWidth, blockHeight);
                    Hamming comparison = new Hamming();
                    String hash = comparison.getHash(subImage);

                    Descriptor descriptor = new Descriptor();
                    String decryptedHash = descriptor.decodeTheImage(subImage).substring(0,64);

                    int distance = comparison.distance(hash, decryptedHash);
                    System.out.println("distance =" + distance);

                    x += blockWidth;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            y += blockHeight;
        }
    }
}
