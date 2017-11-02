
import java.util.ArrayList;

/**
 *
 * @author GUY
 */
public class DeuxApproximation {

    private final ArrayList<Edge> tree;
    private final ArrayList<Vertex> vertices;
    private ArrayList<Node> travelledNode;

    DeuxApproximation(ArrayList<Vertex> vertices, ArrayList<Edge> tree) {
        this.tree = (ArrayList<Edge>) tree.clone();
        this.vertices = vertices;
    }

    // ARPM de Kruskal en entrée
    public void run() {

        travelledNode = new ArrayList<>();//  liste des points une fois parcouru

        travel(vertices.get(0).getNode());

        /* Create a list of edges */
        ArrayList<Edge> edges = new ArrayList<Edge>();

        // Calcul de la longueur totale
        double longueurTotale = 0;
        for (int i = 0; i < travelledNode.size(); i++) {

            Vertex a = vertices.get(travelledNode.get(i).index);
            Vertex b = vertices.get(travelledNode.get((i + 1)%travelledNode.size()).index);

            Edge e = new Edge(a, b, Vertex.euclideanDistance(a, b));
            edges.add(e);
            //System.out.println(e.getU().getNode().index+" "+e.getV().getNode().index+" "+e.getWeight());

        }

        System.out.println(Edge.sum(edges));
        //return edges;
    }

    //parcourir noeud
    private void travel(Node n) {
        travelledNode.add(n);
        for (Edge e : tree) {
            if (e.getU().getNode() == n && !travelledNode.contains(e.getV().getNode())) {
                travel(e.getV().getNode());
            }
            if (e.getV().getNode() == n && !travelledNode.contains(e.getU().getNode())) {
                travel(e.getU().getNode());
            }

        }
    }
}
