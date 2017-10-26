/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GUY
 */
class Vertex {
  private float x;
  private float y;
  private Node n;

  public Vertex(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public void setNode(Node n) { this.n = n; }
  public Node getNode() { return this.n; }

  public static float simpleDistance(Vertex a, Vertex b) {
    return (float) (Math.pow((b.y - a.y), 2) + Math.pow((b.x - a.x), 2));
  }

  public static float euclideanDistance(Vertex a, Vertex b) {
    return (float) Math.sqrt(Vertex.simpleDistance(a, b));
  }
}
