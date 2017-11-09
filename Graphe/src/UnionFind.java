
import java.util.ArrayList;
import java.util.List;

/*
 * Une implémentation simple de la structure de données disjointe.
 * Les éléments d'ensembles disjoints sont représentés dans un arbre,
 * dans lequel chaque noeud fait référence à son parent.
 * Une stratégie d'union par rang est utilisée
 * pour optimiser la combinaison de deux arbres,
 * en veillant à toujours attacher un arbre plus petit 
 * à la racine de l'arbre le plus grand.
 *
 * @author Benjamin Lemaitre
 * @author Guillaume Drouart
 */
class UnionFind {

    private int nodeCount = 0;
    private int setCount = 0;

    ArrayList<Node> rootNodes;
    
    /**
     * Prend une liste de n sommets et crée n ensembles sur cette liste.
     * @param vertices ArrayList<Vertex> ensemble des sommets du graphe
     */
    public UnionFind(List<Vertex> vertices) {
        this.rootNodes = new ArrayList<Node>(vertices.size());
        makeSets(vertices);
    }

    /*
     * Retourne l'index de l'ensemble dans lequel n est actuellement présent.
     * L'index du noeud racine de chaque ensemble identifie de façon unique l'ensemble.
     * Ceci est utilisé pour déterminer si deux éléments sont dans le même ensemble.
     */
    public int find(Node n) {
        Node current = n;

        /* 
         * Monter le pointeur jusqu'au noeud racine 
         */
        while (current.parent != null) {
            current = current.parent;
        }

        Node root = current;

        /*
         * Remontez le pointeur jusqu'au noeud racine, 
         * mais faires en sorte que chaque noeud soit au-dessous 
         * d'un enfant directement à la racine.
         * Ceci modifie l'arborescence de sorte que 
         * les appels futurs atteindront la racine plus rapidement.
         */
        current = n;
        while (current != root) {
            Node temp = current.parent;
            current.parent = root;
            current = temp;
        }

        return root.index;
    }


    /*
     * Combine les ensembles contenant les noeuds i et j.
     */
    public void union(Node i, Node j) {
        int indexI = find(i);
        int indexJ = find(j);

        /* Ces noeuds font-ils déjà partie du même ensemble? */
        if (indexI == indexJ) {
            return;
        }

        /* Obtenir les noeuds racine de chaque ensemble (cela s'exécutera en temps constant) */
        Node a = this.rootNodes.get(indexI);
        Node b = this.rootNodes.get(indexJ);

        /* Attachez l'arbre le plus petit à la racine de l'arbre le plus grand. */
        if (a.rank < b.rank) {
            a.parent = b;
        } else if (a.rank > b.rank) {
            b.parent = a;
        } else {
            b.parent = a;
            a.rank++;
        }

        this.setCount--;
    }


    /*
     * Prendre une liste de n sommets et 
     * faire les n ensembles disjoints singleton.
     */
    public void makeSets(List<Vertex> vertices) {//creer ensemble
        for (Vertex v : vertices) {
            makeSet(v);
        }
    }


    /*
     * Crée un ensemble singleton contenant un sommet.
     */
    public void makeSet(Vertex vertex) {
        Node n = new Node(0, rootNodes.size(), null);
        vertex.setNode(n);
        this.rootNodes.add(n);
        this.setCount++;
        this.nodeCount++;
    }

    

}
