package edu.grinnell.csc207.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Trees intended to be used in storing mappings between fixed-length
 * sequences of bits and corresponding values.
 *
 * @author Myles Bohrer-Purnell
 */
public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** Starting node for the tree. */
  BitTreeNode startNode;

  /** Expected length of the input. */
  int num;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a new bit tree with expected input size n.
   * @param n the expected input size
   */
  public BitTree(int n) {
    this.num = n;
    this.startNode = new BitTreeNode(null, null, null);
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+
  /**
   * Recursive implementation of searching and printing the values in a BitTree.
   * @param pen PenWriter for printing elements in tree
   * @param curNode the current node that is being checked
   * @param curResultOrig the cumulative path to the current node
   */
  public void dumpHelper(PrintWriter pen, BitTreeNode curNode, String curResultOrig) {
    String curResult = curResultOrig;
    if (curNode != null) {
      if (curNode.getValue() != null) {
        pen.println(curResult + "," + curNode.getValue());
      } else {
        curResult = curResultOrig.concat("0");
        dumpHelper(pen, curNode.getLeft(), curResult);
        curResult = curResultOrig.concat("1");
        dumpHelper(pen, curNode.getRight(), curResult);
      } // if/else
    } // if
  } // dumpHelper

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+


  /**
   * Follows the path through the tree given by bits,
   * adding nodes as appropriate and adding or replaceing the
   * value at the end with value.
   * @param bits the path that the value will be added at the end of
   * @param value the value to be added at the end
   */
  public void set(String bits, String value) throws IndexOutOfBoundsException {
    if (bits.length() != this.num) {
      throw new IndexOutOfBoundsException("Wrong Lengthed Path Input");
    } // if
    BitTreeNode curNode = startNode;
    BitTreeNode prevNode = startNode;
    for (int i = 0; i < this.num; i++) {
      if (bits.charAt(i) == '0') {
        // Move left
        prevNode = curNode;
        curNode = curNode.getLeft();
        // handle null conditions for the new node
        if (curNode == null) {
          curNode = new BitTreeNode(null, null, null);
        } // if
        prevNode.setLeft(curNode);
      } else if (bits.charAt(i) == '1') {
        // Move right
        prevNode = curNode;
        curNode = curNode.getRight();
        // handle null conditions for the new node
        if (curNode == null) {
          curNode = new BitTreeNode(null, null, null);
        } // if
        prevNode.setRight(curNode);
      } else {
        throw new IndexOutOfBoundsException("Input Path Does Not Only Include 0s and 1s");
      } // if/else
    } // for
    curNode.setValue(value);
  } // set(String, String)

  /**
   * Follows the path through the tree given by bits, returning
   * the value at the end.
   * @param bits the path to the desired value
   * @return the value at the end of the path or an exception if incorrect path
   */
  public String get(String bits) {
    if (bits.length() != this.num) {
      throw new IndexOutOfBoundsException("Wrong Lengthed Path Input");
    } // if
    BitTreeNode curNode = startNode;
    for (int i = 0; i < this.num; i++) {
      if (bits.charAt(i) == '0') {
        // Move left
        curNode = curNode.getLeft();
      } else if (bits.charAt(i) == '1') {
        // Move right
        curNode = curNode.getRight();
      } else {
        // input path value is not 0 or 1
        throw new IndexOutOfBoundsException("Input Path Does Not Only Include 0s and 1s");
      } // if/else

      if (curNode == null) {
        // if the path does not exist because node is null
        throw new IndexOutOfBoundsException("Input Path Does Not Exist");
      } // if
    } // for
    return curNode.getValue();
  } // get(String, String)

  /**
   * Prints out the contents of the tree in CSV format.
   * @param pen pen for printing output
   */
  public void dump(PrintWriter pen) {
    BitTreeNode curNode = startNode;
    dumpHelper(pen, curNode.getRight(), "1");
    dumpHelper(pen, curNode.getLeft(), "0");
  } // dump(PrintWriter)

  /**
   * Reads a series of lines of
   * the form bits,value and stores them in the tree.
   * @param source stream of input to read
   */
  public void load(InputStream source) {
    InputStreamReader isr = new InputStreamReader(source);
    BufferedReader br = new BufferedReader(isr);
    String line;
    String[] bitVal;
    try {
      while ((line = br.readLine()) != null) {
        bitVal = line.split(",");
        set(bitVal[0], bitVal[1]);
      } // while
    } catch (Exception eof) {
      // end the proccess if issue
    } // try/catch
  } // load(InputStream)

} // class BitTree
