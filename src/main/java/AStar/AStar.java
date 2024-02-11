package AStar;

import Path.Node;
import java.util.List;

/**
 * This class represent the A* algorithm.
 * @author André Timan & Anton Byström.
 */

public class AStar {

    public AStar(){}

    /**
     * Gets the shortest distance from current node to finish node.
     * @param node Current node.
     * @param finishNode Finish node.
     * @return The heuristic from the node to the end.
     */

    private int heuristicCost(Node node, Node finishNode) {
        return Math.abs(node.getX() - finishNode.getX()) + Math.abs(node.getY() - finishNode.getY());
    }

    /**
     * Searches for a path through the node array
     * @param nodeArr array of nodes
     * @return End node.
     */

    public Node searchPath(Node[][] nodeArr) {
        MinHeap toVisit = new MinHeap(nodeArr.length * nodeArr[0].length);

        Node start = null;
        Node finish = null;


        // sets start and finish node to first start and finish nodes found.
        for (Node[] item: nodeArr) {
            for (Node node: item) {
                if (node.isStart()) {
                    start = node;
                }
                if (node.isEnd()) {
                    finish = node;
                }
            }
        }
        assert start != null;
        start.setFromStart(0);
        toVisit.insert(start); // First node to start visiting neighbouring nodes from


        while (toVisit.getSize() > 0) { // Runs while there are nodes still to visit
            Node currentNode = toVisit.remove(); // Removes node from the array.

            List<Node> childNodes = getNeighbours(nodeArr, currentNode); // Neighbouring nodes of current node

            for (Node neighbour: childNodes) {
                neighbour.setFromStart(currentNode.getFromStart() + 1); // sets distance from start
                if (!neighbour.isWall() && !neighbour.isVisited() && !neighbour.isBound()) {
                    neighbour.setParent(currentNode);
                    if (neighbour.isEnd()) {
                        return neighbour;
                    }
                    assert finish != null;
                    neighbour.setHeuristic(heuristicCost(neighbour, finish));
                    toVisit.insert(neighbour); // inserts next node to visit in toVisit heap.
                    neighbour.setVisited();
                }
            }

        }
        return finish;
    }

    /**
     * This method provides the neighbouring nodes of the provided node.
     * @param nodeArr array of nodes to check for neighbours in
     * @param currentNode current node to check neighbours for
     * @return list of the 4 neighbouring nodes
     */
    public List<Node> getNeighbours(Node[][] nodeArr, Node currentNode) {
        List<Node> neighbours = new java.util.ArrayList<>();

        int x = currentNode.getX();
        int y = currentNode.getY();

        if (x+1 < nodeArr[0].length) { // right neighbour
            neighbours.add(nodeArr[y][x+1]);
        }
        if (x-1 > 0) {  // left neighbour
            neighbours.add(nodeArr[y][x-1]);
        }
        if (y+1 < nodeArr.length) { // bottom neighbour
            neighbours.add(nodeArr[y+1][x]);
        }
        if (y-1 > 0) {  // top neighbour
            neighbours.add(nodeArr[y-1][x]);
        }

        return neighbours;
    }
    
}
