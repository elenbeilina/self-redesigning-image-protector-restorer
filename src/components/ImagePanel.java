package components;

import java.awt.*;
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
	private JLabel outputTextArea;

	public BufferedImage getCoverImage() {
		return coverImage;
	}

	public void setText(String text){
		outputTextArea.setText(text);
	}

	public ImagePanel() {
		header = new JLabel();
		setHeaderString("");
		
		JPanel p1 = new JPanel();
		p1.add(header);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());

		image = new JLabel();
		p2.add(image, BorderLayout.CENTER);

		JPanel p3 = new JPanel();
		outputTextArea = new JLabel();
		p3.add(outputTextArea);
		
		setLayout(new BorderLayout());
		add(p2, BorderLayout.CENTER);
		add(p1, BorderLayout.NORTH);
		add(p3,BorderLayout.SOUTH);
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
