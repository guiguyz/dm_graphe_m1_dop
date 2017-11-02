
import java.util.*;

@SuppressWarnings("unchecked")

/*
 * Ce programme exécute l'algorithme de Kruskal, 
 * en utilisant une structure de données efficace.
 *
 */
public class Kruskal {

    private final ArrayList<Vertex> vertices;
    private final ArrayList<Edge> edges;

    public Kruskal(ArrayList<Vertex> vertices, ArrayList<Edge> edges){
        this.vertices=vertices;
        this.edges=edges;
        
        /* Create the disjoint-set data structure */
        DisjointSet d = new DisjointSet(vertices);

        /* Create a list of edges */
        ArrayList<Edge> tree = new ArrayList<Edge>();

        /* Java's modified version of mergesort guarantees nlog(n) time here */
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
        
      
        float sum = 0;
        

        System.out.println("ARPM");
        for (Edge e : tree) {
            System.out.println(e.getU().getNode().index+" "+e.getV().getNode().index);
            sum += e.getWeight();
        }

        System.out.println("");
        System.out.println("taille de l'ARPM : "+sum);

        
    }

}


