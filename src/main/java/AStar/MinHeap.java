package AStar;

import Path.Node;

/**
 * MinHeap used to store neighbouring nodes that should be searched next.
 * Checks nodes that are closest to the end first.
 * @author André Timan & Anton Byström
 */
public class MinHeap {
    private final innerNode[] Heap; // stores the nodes
    private int size; // size of the heap
    private final int maxsize; // The length of the node array.
    private static final int FRONT = 1; // Initializing front as static with unity.

    /**
     * InnerClass to store nodes and key value in present inside the heap.
     */

    private static class innerNode {
        private final int key;
        private final Node value;

        public innerNode(int key, Node value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * constructor
     * @param maxsize size of heap
     */
    public MinHeap(int maxsize)
    {// This keyword refers to current object itself
        this.maxsize = maxsize;
        this.size = 0;

        Heap = new innerNode[this.maxsize + 1];
    }

    /**
     * Returning the position of the parent node for the current node
     * @param pos node position
     * @return parent position
     */
    private int parent(int pos) { return (pos-1) / 2; }

    /**
     * Returning the position of the left child node for the current node
     * @param pos node position
     * @return left child position
     */
    private int leftChild(int pos) { return (2 * pos); }

    /**
     * Returning the position of the right child node for the current node
     * @param pos node position
     * @return right child position
     */
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }

    /**
     * Checks if the node is a Leaf.
     * @param pos node position
     * @return True if it's a Leaf
     */
    private boolean isLeaf(int pos){
        return pos > (size / 2) && pos <= size;
    }

    /**
     * Swap position of nodes by taking their position and saving them into each other.
     * This is done by using a temporary variable holding the value of the first position until It's assigned to second position.
     * @param fpos first position
     * @param spos second position
     */
    private void swap(int fpos, int spos) {
        innerNode tmp;
        tmp = Heap[fpos];

        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    /**
     * Heapify at the current node.
     * This is done by traversing up through the list whenever the node has parented and out of order / parent bigger
     * @param pos current node
     */
    private void minHeapify(int pos)
    {

        // Checks whether the node is a not a leaf-node and greater than any of its child
        if (!isLeaf(pos) && size -1 > 0) {
            // If current position is key value is greater than the children key value.
            if (Heap[pos].key > Heap[leftChild(pos)].key
                    || Heap[pos].key > Heap[rightChild(pos)].key) {

                // Swap with the left child and heapify the left child
                if (Heap[leftChild(pos)].key < Heap[rightChild(pos)].key){
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }

                // Swap with the right child and heapify the right child
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }

    /**
     * This method handles insertion into the heap.
     * @param element New node.
     */

    public void insert(Node element){
        // key value and value is assigned to the new node.
        innerNode newNode = new innerNode(element.getFinalCost(), element);
        if (size >= maxsize){
            return;
        }

        Heap[++size] = newNode; // Increase size of the heap
        int current = size;

        // If the heap is empty add new node to the first position.
        if (size-1 <= 0) {
            Heap[0] = newNode;
            return;
        }

        while (Heap[current].key < Heap[parent(current)].key) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    /**
     * Removes a node from heap.
     * @return removed node
     */

    public Node remove(){
        innerNode popped = Heap[FRONT];  // Takes the node at the top position inside the heap
        Heap[FRONT] = Heap[size--]; // Removes that node
        minHeapify(FRONT); // Adjust the heap so its in order.
        return popped.value;
    }

    /**
     * The size of the heap
     * @return size
     */

    public int getSize() {
        return size;
    }
}
