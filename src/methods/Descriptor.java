package methods;

import java.awt.image.BufferedImage;

public class Descriptor {
    public static String b_msg = "";
    public static int len = 0;

    public String decodeTheMessage(BufferedImage imageToDecode) {
        int currentBitEntry = 0;
        StringBuilder bx_msg = new StringBuilder();

        for (int x = 0; x < imageToDecode.getWidth(); x++) {
            for (int y = 0; y < imageToDecode.getHeight(); y++) {
                if (x == 0 && y < 8) {
                    bx_msg.append(processImageByBlue(imageToDecode, x, y));
                    len = Integer.parseInt(bx_msg.toString(), 2);

                } else if (currentBitEntry < len * 8) {
                    b_msg += processImageByBlue(imageToDecode, x, y);

                    currentBitEntry++;
                }
            }
        }
        return bx_msg.toString();
    }

    private char processImageByBlue(BufferedImage imageToDecode,
                                    int x, int y) {
        int blue = imageToDecode.getRGB(x, y);
        blue = blue & 255;
        String x_s = Integer.toBinaryString(blue);
        return x_s.charAt(x_s.length() - 1);
    }
}