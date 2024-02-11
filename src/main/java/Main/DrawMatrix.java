package Main;

import Path.Node;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is responsible for recreating the maze image that was used and adding the A* algorithm path onto that image.
 * @author André Timan & Anton Byström
 */

public class DrawMatrix {

    private final Node[][] nodeList; // List containing all the nodes.
    private final BufferedImage bufferedImage; // Constructs a BufferedImage of one of the predefined image types.
    private final Graphics g2d;
    private final String mazeName;
    /**
     * The constructor of this class receives the nodes and the width and height of the maze in order to recreate it.
     * @param nodeList All the nodes.
     * @param width The width of the original picture.
     * @param height The height of the original picture.
     */

    public DrawMatrix(Node[][] nodeList, int width, int height, String name){
        this.mazeName = name;
        this.nodeList = nodeList;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g2d = bufferedImage.createGraphics();
    }

    /**
     * This method recreates the picture that was broken down into pixels.
     * This is done by checking the type of every node if it's a wall, background, start and end.
     * Then taking the color of that node and drawing that color on the pixel of the new image,
     * and finally saving it as a jpg.
     */

    public void draw() {
        for (Node[] nodes : nodeList) {
            for (Node node : nodes) {
                if (node.isStart()) {
                    g2d.setColor(node.getColor()); // Red.
                    // Using the same coordinates on the second point as well,
                    // because we only want to draw on the current pixel not the next one
                    g2d.drawLine(node.getX(), node.getY(), node.getX(), node.getY());
                } else if (node.isWall()) {
                    g2d.setColor(node.getColor()); // Black.
                    g2d.drawLine(node.getX(), node.getY(), node.getX(), node.getY());
                } else if (node.isEnd()) {
                    g2d.setColor(node.getColor()); // Cyan.
                    g2d.drawLine(node.getX(), node.getY(), node.getX(), node.getY());
                } else {
                    g2d.setColor(node.getColor()); // White.
                    g2d.drawLine(node.getX(), node.getY(), node.getX(), node.getY());
                }

            }
        }
        saveFile(bufferedImage);
    }

    /**
     * This method receives the recreated maze image and saves it as a jpg for display purposes.
     * @param image The new recreated maze image.
     */

    public void saveFile(BufferedImage image) {
        try {
            ImageIO.write(image, "JPG", new File("src/main/resources/solved/" + mazeName + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



