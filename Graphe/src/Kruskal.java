
import java.util.*;

@SuppressWarnings("unchecked")

/**
 * Ce programme exécute l'algorithme de Kruskal, en utilisant une structure de
 * données efficace.
 *
 * @author Benjamin Lemaitre
 * @author Guillaume Drouart
 */
public class Kruskal {

    private ArrayList<Edge> tree;

    public Kruskal(ArrayList<Vertex> vertices, ArrayList<Edge> edges) {

        // Créer la partition d’un ensemble 
        UnionFind d = new UnionFind(vertices);

        // On crée une ArrayList d'arête pour stocker l'ARPM
        tree = new ArrayList<Edge>();

        /* 
        Java's modified version of mergesort guarantees nlog(n) time here */
        // On trie les sommets 
        Collections.sort(edges);

        /* Kruskal's algorithm */
        for (Edge e : edges) {
            Vertex u = e.getU();
            Vertex v = e.getV();
            //System.out.println("poids de l'arrete e : "+e.getWeight()+" entre (u,v) : ("+u.getNode().index+","+v.getNode().index+")");
            if (d.find(u.getNode()) != d.find(v.getNode())) {
                /* Vertices v and u are not in the same component */
                tree.add(e);

                /* Union them in the tree */
                d.union(u.getNode(), v.getNode());
            }
        }
    }

    public ArrayList<Edge> getARPM() {
        
        float sum = 0;
        System.out.println("");
        System.out.println("Voic l'ARPM Généré par Kruskal : ");
        System.out.println("");
        for (Edge e : tree) {
            System.out.println(e.getU().getNode().index + " " + e.getV().getNode().index);
            //System.out.println(e.getU().getNode().index+" "+e.getV().getNode().index+" "+(e.getV().getNode().parent!=null?e.getV().getNode().parent.index:""));
            sum += e.getWeight();
        }

        System.out.println("");
        System.out.println("Le cout des éléments de l'ARPM est de : " + sum);

        
        return tree;
    }

}
