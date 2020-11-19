package frames;

import actions.*;
import components.ImagePanel;
import methods.Protector;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static constants.Parameters.*;
import static utils.Util.*;

public class MainFrame extends JFrame implements MouseListener, MouseMotionListener {

    private ImagePanel imagePanel;
    private JFileChooser fileChooser;
    private int drag_status = 0, c1, c2, c3, c4;

    public MainFrame() {
        setSize(MAINFRAME_WIDTH, MAINFRAME_HEIGHT);
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(scrSize.width / 2 - MAINFRAME_WIDTH / 2, scrSize.height / 2 - MAINFRAME_HEIGHT / 2);
        setTitle(PROGRAM_NAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowEventsHandler());
        addMouseListener(this);
        addMouseMotionListener(this);

        createComponents();
    }

    private void createComponents() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new OpenImageAction(this));
        fileMenu.addSeparator();
        fileMenu.add(new ExitAction(this));

        JMenu protectorMenu = new JMenu("Protector");
        protectorMenu.add(new ImageProtectAction(this));
        protectorMenu.add(new ImageAuthenticateAction(this));

        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(new AboutAction(this));

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(protectorMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.addChoosableFileFilter(new FileFilter());
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(false);

        imagePanel = new ImagePanel("Image: ");

        this.add(imagePanel, BorderLayout.WEST);
    }

    public void exit() {
        this.dispose();
    }

    public void openCoverImage() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            loadImageFile(fileChooser.getSelectedFile(), imagePanel);
        }
    }

    private void loadImage() throws IOException {
        File file = FileFilter.addExtension(fileChooser.getSelectedFile());

        String name = file.getName();
        String extension = name.substring(name.lastIndexOf('.') + 1);
        try {
            ImageIO.write(imagePanel.getImage(), extension, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        imagePanel.loadImage(file);
    }

    public void protectImage(Protector protector) throws IOException {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            protector.protectImage(imagePanel.getImage());
            loadImage();
            showInformationMessage(this, MESSAGE_ENCRYPTION_COMPLETED);
        }
    }

    public void authenticateImage(Protector protector) {
        protector.authenticatingImage(imagePanel.getImage());
        imagePanel.loadImage(imagePanel.getImage());
    }

    private void loadImageFile(File f, ImagePanel panel) {
        try {
            panel.loadImage(f);
        } catch (IOException e) {
            showErrorMessage(this, MESSAGE_IO_ERROR, e.getMessage());
        } catch (Exception e) {
            showErrorMessage(this, MESSAGE_UNEXPECTED_ERROR, e.getMessage());
        }

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        repaint();
        c1 = mouseEvent.getX();
        c2 = mouseEvent.getY();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        repaint();
        if (drag_status == 1) {
            c3 = mouseEvent.getX();
            c4 = mouseEvent.getY();
            try {
                draggedScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void draggedScreen() throws Exception {
        int w = c1 - c3;
        int h = c2 - c4;
        w = w * -1;
        h = h * -1;
        BufferedImage img = imagePanel.getImage().getSubimage(c1, c2, w, h);
        File save_path=new File("screen1.jpg");
        ImageIO.write(img, "JPG", save_path);
        System.out.println("Cropped image cropped.");
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        repaint();
        drag_status = 1;
        c3 = mouseEvent.getX();
        c4 = mouseEvent.getY();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        int w = c1 - c3;
        int h = c2 - c4;
        w = w * -1;
        h = h * -1;
        if (w < 0) w = w * -1;
        g.drawRect(c1, c2, w, h);
    }

    private class WindowEventsHandler extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            MainFrame.this.exit();
        }
    }
}
