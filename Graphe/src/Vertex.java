
/*
 * Classe représentant un seul sommet, tenant un pointeur vers un noeud en
 * la structure de données disjoint-set. Contient également des fonctions de calcul
 * distances simples et euclidiennes.
 *
 * @author Benjamin Lemaitre
 * @author Guillaume Drouart
 */
class Vertex {

    private float x;
    private float y;
    private Node n;

    /**
     * Constructeur
     *
     * @param x première coordonné.
     * @param y deuxième coordonné.
     */
    public Vertex(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Mutateur
     */
    public void setNode(Node n) {
        this.n = n;
    }

    /**
     * Acceseur
     */
    public Node getNode() {
        return this.n;
    }

    /**
     * @param a Un vertex
     * @param b Un autre vertex
     * @return le carré de la distance euclidienne entre a et b
     */
    public static float simpleDistance(Vertex a, Vertex b) {
        return (float) (Math.pow((b.y - a.y), 2) + Math.pow((b.x - a.x), 2));
    }

    /**
     * @param a Un vertex
     * @param b Un autre vertex
     * @return la distance euclidienne entre a et b
     */
    public static float euclideanDistance(Vertex a, Vertex b) {
        return (float) Math.sqrt(Vertex.simpleDistance(a, b));
    }
}
