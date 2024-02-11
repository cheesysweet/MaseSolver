package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class implements a JPanel since we want to display an unsolved and a solved image on it.
 * @author André Timan & Anton Byström
 */

public class ImagePanel extends JPanel {
    private BufferedImage image;

    /**
     * The constructor of this class takes ina string which is the selected string inside the JComboBox.
     * This string is used to create an url to fetch the appropriate image.
     * @param fileName Maze name.
     */


    public ImagePanel(String fileName){
        setImage(fileName);
    }

    /**
     * This method is used to fetch the image selected for solving.
     * @param fileName The url used to fetch current image.
     */

    public void setImage(String fileName) {
        String url = "src/main/resources/images/";
        try {
            this.image = ImageIO.read(new File(url + fileName + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to fetch the solved image version.
     * @param fileName The url used to fetch current solved image.
     */

    public void setSolvedImage(String fileName) {
        String url = "src/main/resources/solved/";
        try {
            this.image = ImageIO.read(new File(url + fileName + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to draw the currently selected image onto the panel.
     * @param g2d Graphics object.
     */

    @Override
    public void paint(Graphics g2d){
        g2d.drawImage(image, 0, 0, this);
    }
}
