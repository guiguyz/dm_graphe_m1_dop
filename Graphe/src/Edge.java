
import java.util.List;


/**
 * Classe représentant une arête unique, avec des pointeurs vers les sommets. 
 * cela inclut également des facilités pour calculer les poids des arêtes.
 *
 * @author Benjamin Lemaitre
 * @author Guillaume Drouart
 */
class Edge implements Comparable {

    private float weight;
    private Vertex u, v;

    /**
     * Constructeur logique.
     *
     * @param u Première extrémité de l'arête.
     * @param v Seconde extrémité de l'arête.
     */
    public Edge(Vertex u, Vertex v) {
        this.u = u;
        this.v = v;
    }

    /**
     * Constructeur.
     *
     * @param u Première extrémité de l'arête.
     * @param v Seconde extrémité de l'arête.
     * @param w le poids de l'arête.
     */
    public Edge(Vertex u, Vertex v, float w) {
        this(u, v);
        this.weight = w;
    }

    /**
     * Acceseur
     */
    public float getWeight() {
        return this.weight;
    }

    /**
     * Mutateur
     */
    public void setWeight(float w) {
        this.weight = w;
    }

    /**
     * Acceseur
     */
    public Vertex getU() {
        return this.u;
    }

    /**
     * Acceseur
     */
    public Vertex getV() {
        return this.v;
    }


    /**
     *
     * @param o un autre Edge avec lequel le comparer
     * @return en int : -1 si l'autre Edge est de poids supérieur, 1 s'il est de poids inférieur et 0 si les poids sont égaux
     */
    public int compareTo(Object o) {
        Edge other = (Edge) o;

        if (this.getWeight() < other.getWeight()) {
            return -1;
        } else if (this.getWeight() > other.getWeight()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     *
     * @param edges une liste d'Edge
     * @return la somme des poids des edges de la liste
     */
    public static float sum(List<Edge> edges) {
        float sum = 0;

        for (Edge e : edges) {
            sum += e.getWeight();
        }

        return sum;
    }
}
