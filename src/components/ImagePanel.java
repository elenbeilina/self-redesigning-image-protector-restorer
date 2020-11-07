package components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static utils.Util.convertImage;

public class ImagePanel extends JPanel {
    private final JLabel image;
    private final JLabel imageName;
    private BufferedImage coverImage;

    public BufferedImage getCoverImage() {
        return coverImage;
    }

    public ImagePanel(String name) {
        imageName = new JLabel();
        imageName.setText(name);
        setHeaderString("");

        JPanel p1 = new JPanel();
        p1.add(imageName);
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        image = new JLabel();
        p2.add(image, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(p2, BorderLayout.CENTER);
        add(p1, BorderLayout.NORTH);
    }

    public void loadImage(File f) throws IOException {
        if (f == null) {
            image.setIcon(null);
            return;
        }
        BufferedImage loadedImage = ImageIO.read(f);
        coverImage = convertImage(loadedImage);
        image.setIcon(new ImageIcon(loadedImage));
        setHeaderString(f.getName());
    }

    public void setHeaderString(String s) {
        String text = imageName.getText();
        if (s.equals("")) {
            imageName.setText(text + "<No image>");
        } else {
            imageName.setText(text + s);
        }
    }
}
