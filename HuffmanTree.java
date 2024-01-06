// Jaysheel Pandya
// CSE 143 DD with Xunmei Liu
// Homework 8
// The HuffmanTree class encodes text into a series of bits
// based on the frequency of each character, effectively reducing
// the number of bits required for more common characters. It can also
// encode the code into a compressed file and decode the compressed file
// back into text format.

import java.io.*;
import java.util.*;

public class HuffmanTree {

    private HuffmanNode overallRoot;

    // Constructs a Huffman tree using the given frequencies for each
    // character corresponding to its index
    public HuffmanTree(int[] count) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (int i = 0; i < count.length; i++) {
            HuffmanNode n = new HuffmanNode(i, count[i]);
            if (n.frequency > 0) {
                pq.add(n);
            }
        }
        pq.add(new HuffmanNode(count.length, 1));
        while (pq.size() > 1) {
            HuffmanNode n1 = pq.remove();
            HuffmanNode n2 = pq.remove();
            overallRoot = new HuffmanNode(n1.frequency + n2.frequency, n1, n2);
            pq.add(overallRoot);
        }
    }

    // Reconstructs a Huffman tree by reading a given file that
    // stores a Huffman tree in standard format
    public HuffmanTree(Scanner input) {
        overallRoot = new HuffmanNode(-1, -1);
        while (input.hasNext()) {
            int character = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            HuffmanNode curr = overallRoot;
            while (!code.isEmpty()) {
                if (code.charAt(0) == '0') {
                    code = code.substring(1);
                    if (curr.left == null) {
                        curr.left = new HuffmanNode(-1, -1);
                    }
                    curr = curr.left;
                }
                else {
                    code = code.substring(1);
                    if (curr.right == null) {
                        curr.right = new HuffmanNode(-1, -1);
                    }
                    curr = curr.right;
                }
            }
            curr.character = character;
        }
    }

    // Writes the current tree to the given output stream
    // in standard format
    public void write(PrintStream output) {
        String code = "";
        writeHelper(output, overallRoot, code);
    }

    // Creates the codes for the paths of where the characters are located
    private void writeHelper(PrintStream output, HuffmanNode root, String code) {
        if (root.left == null && root.right == null) {
            output.println(root.character);
            output.println(code);
        }
        else if (root.right == null) {
            writeHelper(output, root.left, code + "0");
        }
        else if (root.left == null) {
            writeHelper(output, root.right, code + "1");
        }
        else {
            writeHelper(output, root.left, code + "0");
            writeHelper(output, root.right, code + "1");
        }
    }

    // Navigates to each character in the tree by reading the given
    // input's bits, writing each to the output until the end-of-file
    // character is reached
    // Input stream contains legal encoding of characters
    public void decode(BitInputStream input, PrintStream output, int eof) {
        HuffmanNode curr = overallRoot;
        int bit = input.readBit();
        while (curr.character != eof) {
            while (curr.left != null && curr.right != null) {
                if (bit == 0) {
                    curr = curr.left;
                }
                else {
                    curr = curr.right;
                }
                bit = input.readBit();
            }
            if (curr.character != eof) {
                output.write(curr.character);
            }
            curr = overallRoot;
        }
    }
}