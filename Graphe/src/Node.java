/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Implementation of a node, to be used with the DisjointSet tree
 * structure.
 */
class Node {
  int rank;      // the approximate count of nodes below this node
  int index;     // a unique index for each node in the tree
  Node parent;

  public Node(int r, int i, Node p) {
    this.rank = r;
    this.index = i;
    this.parent = p;
  }
}
