package components;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import static utils.Util.convertImage;

public class ImagePanel extends JPanel {
	private final JLabel image;
	private final JLabel header;
	private BufferedImage coverImage;

	public BufferedImage getCoverImage() {
		return coverImage;
	}

	public ImagePanel() {
		header = new JLabel();
		setHeaderString("");
		
		JPanel p1 = new JPanel();
		p1.add(header);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());

		image = new JLabel();
		p2.add(image,BorderLayout.CENTER);
		
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

	public JLabel getImage() {
		return image;
	}

	public void setHeaderString(String s) {
		if (s.equals("")) {
			header.setText("<No image>");
		} else {
			header.setText(s);
		}
	}
}
