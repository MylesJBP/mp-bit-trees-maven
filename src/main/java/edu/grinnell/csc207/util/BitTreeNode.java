package edu.grinnell.csc207.util;

/**
 * A linked-list node to store elements in a BitTree.
 *
 * @author Myles Bohrer-Purnell
 */
public class BitTreeNode {

  /** Child node to left of the current node. */
  BitTreeNode leftNode;

  /** Child node to the right of the current node. */
  BitTreeNode rightNode;

  /** The letter that the previous path led to. */
  String value;

  /**
   * Constructs a new current node, with pointers to the nodes on either side of it.
   * @param lNode the left child
   * @param rNode the right child
   * @param val String value to store in the node
   */
  public BitTreeNode(BitTreeNode lNode, BitTreeNode rNode, String val) {
    this.leftNode = lNode;
    this.rightNode = rNode;
    this.value = val;
  } // BitTreeInterorNode

  /**
   * Changes the node on the right of the current node.
   * @param newRight new right child node
   */
  public void setRight(BitTreeNode newRight) {
    this.rightNode = newRight;
  } // setRight

  /**
   * Changes the node on the left side of the current node.
   * @param newLeft new left child node
   */
  public void setLeft(BitTreeNode newLeft) {
    this.leftNode = newLeft;
  } // setLeft

  /**
   * Returns the node to the right of the current node.
   * @return the node on the right
   */
  public BitTreeNode getRight() {
    return this.rightNode;
  } // getRight

  /**
   * Returns the node to the left of the current node.
   * @return the node on the left
   */
  public BitTreeNode getLeft() {
    return this.leftNode;
  } // getLeft

    /**
   * Returns the leaf's String value.
   * @return the value at the leaf
   */
  public String getValue() {
    return this.value;
  } // getValue

  /**
   * Sets the value at the leaf to a new value.
   * @param newVal the new value for the leaf
   */
  public void setValue(String newVal) {
    this.value = newVal;
  } // setValue
} // BitTreeNode
