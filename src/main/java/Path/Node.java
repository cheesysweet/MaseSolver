package Path;

import java.awt.*;

/**
 * This class represents a pixel from the chosen maze image, but with added data components.
 * Each node knows all the relevant information about itself.
 * @author André Timan & Anton Byström
 */

public class Node {

    private boolean bound, wall, end, start;
    private final int x, y; // x and y values.
    private Node parent; // The previous node.
    private Color color; // The nodes color.
    private boolean visited; // Visited nodes
    private int fromStart;
    private int heuristic;

    /**
     * The constructor of the node class takes in a few essential values, these values are
     * @param x X coordinate of the current pixel.
     * @param y Y coordinate of the current  pixel.
     * @param wall Boolean value if current pixel is a wall or not.
     * @param end Boolean value if current pixel is the end point or not.
     * @param start Boolean value if current pixel is the start point or not.
     */

    public Node(int x, int y, boolean wall, boolean end, boolean start){
        this.wall = wall;
        this.start = start;
        this.end = end;
        this.x = x;
        this.y = y;
    }

    /**
     * This method is used to check weather or not a node is of type wall.
     * @return Returns true if the node is the color of the wall.
     */

    public boolean isWall(){
        return wall;
    }

    /**
     * This method is used to check weather or not a node is of type end.
     * @return Returns true if the node is the color of the end point.
     */

    public boolean isEnd(){
        return end;
    }

    /**
     * This method is used to check weather or not a node is of type end.
     * @return Returns true if the node is the color of the start point.
     */

    public boolean isStart(){
        return start;
    }

    /**
     * Retrieve the value of x.
     * @return
     */

    public int getX(){
        return x;
    }

    /**
     * Retrieve the value of y.
     * @return
     */

    public int getY(){
        return y;
    }

    /**
     * Sets the parent of a node.
     * @param parent Previous node in the matrix.
     */

    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * Previous node in the matrix.
     * @return Previous node.
     */

    public Node getParent() {
        return parent;
    }

    /**
     * Retrieve the color of a node.
     * @return The color of the node.
     */

    public Color getColor() {
        return color;
    }

    /**
     * Set the color of a node.
     * @param color Color of the pixel.
     */

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets a node to visited if the algorithm has used the node.
     */

    public void setVisited() {
        this.visited = true;
    }

    /**
     * This returns a boolean depending on if a node has been visited or not.
     * @return True if the node has been visited, false if the latter.
     */

    public boolean isVisited() {
        return visited;
    }

    /**
     * Increments this value by one for each node passed.
     */

    public void setFromStart(int fromStart) {
        this.fromStart = fromStart;
    }

    /**
     * Get the value from start.
     * @return Integer value associated with each node passed.
     */

    public int getFromStart() {
        return fromStart;
    }

    /**
     * Sets the heuristic cost for a node.
     * @param heuristic A integer value from node to end.
     */

    public void setHeuristic(int heuristic ) {
        this.heuristic  = heuristic ;
    }

    /**
     * Provides the heuristic cost and node cost from the "start".
     * @return Integer cost associated with a node.
     */

    public int getFinalCost() {
        return heuristic  + fromStart;
    }

    /**
     * Checks weather a node is outside the maze's outer wall.
     * @return True if it's the case, false if not.
     */

    public boolean isBound() {
        return bound;
    }

    /**
     * Sets the bound variable to true if it's outside the maze's outer wall.
     */

    public void setBound() {
        this.bound = true;
    }

    /**
     * This is toString method is used to display all the data concerning a node.
     * @return A string of a nodes values.
     */

    @Override
    public String toString(){
        return "Node{" +
                "wall=" + wall +
                ", end=" + end +
                ", start=" + start +
                ", color=" + color +
                '}';

    }



}
