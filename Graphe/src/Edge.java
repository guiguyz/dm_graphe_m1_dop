
import java.util.List;


/*
 * Classe représentant une arête unique, avec des pointeurs vers les sommets. 
 * cela inclut également des facilités pour calculer les poids des arêtes.
 */
class Edge implements Comparable {

    private float weight;
    private Vertex u, v;

    public Edge(Vertex u, Vertex v) {
        this.u = u;
        this.v = v;
    }

    public Edge(Vertex u, Vertex v, float w) {
        this(u, v);
        this.weight = w;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float w) {
        this.weight = w;
    }

    public Vertex getU() {
        return this.u;
    }

    public Vertex getV() {
        return this.v;
    }

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

    public static float sum(List<Edge> edges) {
        float sum = 0;

        for (Edge e : edges) {
            sum += e.getWeight();
        }

        return sum;
    }
}
