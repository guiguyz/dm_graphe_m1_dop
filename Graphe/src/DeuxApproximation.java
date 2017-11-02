
import java.util.ArrayList;

@SuppressWarnings("unchecked")

/**
 * 
 * 
 * @author Benjamin Lemaitre
 * @author Guillaume Drouart
 */
public class DeuxApproximation {

    private final ArrayList<Edge> tree;
    private final ArrayList<Vertex> vertices;
    private ArrayList<Node> travelledNode;

    /**
     *
     * @param vertices ArrayList<Vertex> ensemble des sommets du graphe
     * @param tree ArrayList<Edge> ARPM du graphe (généré par Kruskal ici)
     */
    DeuxApproximation(ArrayList<Vertex> vertices, ArrayList<Edge> tree) {
        this.tree = (ArrayList<Edge>) tree.clone();
        this.vertices = vertices;
    }

    
    public void run() {

        travelledNode = new ArrayList<>();//  liste des points une fois parcouru

        /* On appelle la fonction travel.
         * On fixe le point de départ du parcours au point 0 
         * qui est "a" dans le DM et qui est le premier noeud 
         * renseigner dans le fichier dm.tsp        
         */
        travel(vertices.get(0).getNode());

        ArrayList<Edge> edges = new ArrayList<Edge>();

        System.out.println("Voici le cycle 2-approximation du graphe");
        // On construit la liste des arêtes du cycle
        for (int i = 0; i < travelledNode.size(); i++) {

            Vertex a = vertices.get(travelledNode.get(i).index);
            Vertex b = vertices.get(travelledNode.get((i + 1) % travelledNode.size()).index);
            
            // pour chaque noeud a on créer une arête avec son successeur b
            // on a b = a + 1 % travelledNode.size() car 
            // % travelledNode.size() permet de relier le dernier noeud 
            // au premier pour créer un cycle

            Edge e = new Edge(a, b, Vertex.euclideanDistance(a, b));
            edges.add(e);
            System.out.println(e.getU().getNode().index+" "+e.getV().getNode().index);
        }
        
        System.out.println("");
        System.out.println("le cout du parcours du cycle 2-approximation est de ");
        System.out.println(Edge.sum(edges));

    }

    /* 
     * Fonction récursive pour parcourir l'arbre 
     * et renseigner les noeuds parcourus        
     */
    private void travel(Node n) {
        // On rajoute le noeud n a la liste de ceux parcourus
        travelledNode.add(n);
        // On applique la même fonction sur les noeuds ayant un lien avec n
        // et n'etant pas encore parcouru
        
        //pour chaque noeud on regarde si n est lié a ce noeud dans l'ARPM 
        // et si ce noeud n'est pas déja parcouru
        for (Edge e : tree) {
            // Cas ou n est la première variables de l'edge
            if (e.getU().getNode() == n && !travelledNode.contains(e.getV().getNode())) {
                travel(e.getV().getNode());
            }
            // Cas ou c'est le deuxième
            if (e.getV().getNode() == n && !travelledNode.contains(e.getU().getNode())) {
                travel(e.getU().getNode());
            }

        }
        
        // Au final on a construit l'ArrayList travelNode 
        // Ou l'ordre représente le cycle de l'algorithme
        // 2-Approximation
    }
}
