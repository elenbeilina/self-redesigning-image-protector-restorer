package components;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ImagePanel extends JPanel {
	private final JLabel image;
	private final JLabel header;
	private JLabel txt;

	public ImagePanel() {
		header = new JLabel();
		setHeaderString("");
		
		JPanel p1 = new JPanel();
		p1.add(header);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());

		JPanel p3 = new JPanel();
		txt = new JLabel();
		txt.setVisible(false);
		p3.add(txt);

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
		image.setIcon(new ImageIcon(loadedImage));
		setHeaderString(f.getName());
	}

	public JLabel getImage() {
		return image;
	}

	public void setTxt(String txt) {
		this.txt.setText(txt);
	}

	public void setHeaderString(String s) {
		if (s.equals("")) {
			header.setText("<No image>");
		} else {
			header.setText(s);
		}
	}

	public String getTxt(String s) {
		return txt.getText();
	}
}
