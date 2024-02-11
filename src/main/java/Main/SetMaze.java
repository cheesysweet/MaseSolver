package Main;

import AStar.AStar;
import Path.Node;
import Path.NodeColor;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Loads the unsolved image and solves the maze
 * @author André Timan & Anton Byström
 */
public class SetMaze {
    private static final NodeColor color = new NodeColor();

    public void readFile(String file) throws IOException {

        BufferedImage image = ImageIO.read(new File("src/main/resources/images/" + file + ".jpg"));

        Node[][] result = getMatrix(image);
        setBound(result);

        AStar aStar = new AStar();
        Node lastNode = aStar.searchPath(result);
        solveMaze(lastNode);

        DrawMatrix drawMatrix =  new DrawMatrix(result, image.getWidth(), image.getHeight(), file);
        drawMatrix.draw();

    }

    /**
     * prints the path from the last node to first by checking parent node
     * @param lastNode end of the maze
     */
    private static void solveMaze(Node lastNode) {
        Node node = lastNode;

        if (node.getParent() == null) {
            System.out.println("No path found");
            return;
        }

        while (!node.isStart()) {
            node.setColor(color.pathColor());
            node = node.getParent();
        }
    }

    /**
     * transforms image into matrix
     * @param image image to transform
     * @return list of nodes
     */

    public static Node[][] getMatrix(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Node[][] nodeArray = new Node[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                if (color.wall(new Color(image.getRGB(x, y)))) {
                    Node newNode = new Node(x, y, true, false, false);
                    newNode.setColor(new Color(image.getRGB(x,y)));
                    nodeArray[y][x] = newNode;
                } else if(color.start(new Color(image.getRGB(x,y)))){
                    Node newNode = new Node(x, y, false, false, true);
                    newNode.setColor(new Color(image.getRGB(x,y)));
                    nodeArray[y][x] = newNode;
                } else if(color.end(new Color(image.getRGB(x,y)))){
                    Node newNode = new Node(x, y, false, true, false);
                    newNode.setColor(new Color(image.getRGB(x,y)));
                    nodeArray[y][x] = newNode;
                } else {
                    Node newNode = new Node(x, y, false, false, false);
                    newNode.setColor(new Color(image.getRGB(x,y)));
                    nodeArray[y][x] = newNode;
                }
            }
        }
        return nodeArray;
    }

    /**
     * gets the topLeft and bottomRight corner of the picture
     * @param result array of the nodes
     */
    public static void setBound(Node[][] result) {
        int top = 0;
        int bot = 0;


        // gives a value to each position at the first wall position
        outer: for (Node[] nodes : result) {
            for (Node node : nodes) {

                if (node.isWall()) {
                    top = node.getY();
                    bot = node.getY();
                    break  outer;
                }
            }
        }

        // sets the corner values
        for (Node[] nodes : result) {
            for (Node node : nodes) {
                if (node.isWall()) {
                    if (node.getY() > bot) {
                        bot = node.getY();
                    }
                }
            }
        }

        // sets upper and lower bounds to the image
        for (Node[] nodes : result) {
            for (Node node : nodes) {
                if (node.getY() < top || node.getY() > bot) {
                    node.setBound();
                }
            }
        }
    }
}
