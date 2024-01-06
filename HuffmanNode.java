// Jaysheel Pandya
// CSE 143 DD with Xunmei Liu
// Homework 8
// The HuffmanNode class creates nodes for the
// HuffmanTree class that store a character and frequency
// and point to a left and right branch.

public class HuffmanNode implements Comparable<HuffmanNode> {

    public int character;
    public int frequency;
    public HuffmanNode left;
    public HuffmanNode right;

    // Constructs a Huffman node that stores a character's
    // integer value and its frequency within the given text
    public HuffmanNode(int character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    // Constructs a Huffman node that stores a character's
    // frequency within the given text and pointers to its
    // left and right branch nodes
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    // Compares the given Huffman node's frequency with the stored
    // frequency, returning a negative value if it is greater
    // than the stored frequency and positive value if it is
    // less than the stored frequency
    // Returns 0 if both frequencies are equal
    @Override
    public int compareTo(HuffmanNode n) {
        if (this.frequency < n.frequency) {
            return -1;
        }
        else if (this.frequency > n.frequency) {
            return 1;
        }
        return 0;
    }
}