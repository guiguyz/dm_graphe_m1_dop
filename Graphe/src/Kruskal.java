
import java.util.*;

@SuppressWarnings("unchecked")

/**
 * Ce programme ex�cute l'algorithme de Kruskal, en utilisant une structure de
 * donn�es efficace.
 *
 * @author Benjamin Lemaitre
 * @author Guillaume Drouart
 */
public class Kruskal {

    private ArrayList<Edge> tree;

    
    /**
     * Constructeur logique.
     *
     * @param vertices ArrayList de Vertex (sommets).
     * @param edges ArrayList de Edges (ar�tes).
     */
    public Kruskal(ArrayList<Vertex> vertices, ArrayList<Edge> edges) {

        // Créer la partition d'un ensemble 
        UnionFind d = new UnionFind(vertices);

        // On crée une ArrayList d'ar�te pour stocker l'ARPM
        tree = new ArrayList<Edge>();

        // On trie les arêtes pour pouvoir les traiter 
        // par ordre croissant de poids, car les ar�tes de
        // poids faible sont plus int�ressantes(complexit� en nlog(n))
        Collections.sort(edges);

        // Dans l'ordre des poids des ar�tes, on va regarder 
        // si l'ar�te est à ajouter à l'ensemble
        for (Edge e : edges) {
            Vertex u = e.getU();
            Vertex v = e.getV();
            // On vérifie que l'arête ne relie pas deux sommets 
            // d'un même ensemble, car le r�sultat attendu est un ARPM 
            // et une telle arête créerait un cycle : 
            // ce ne serait donc plus un arbre.
            if (d.find(u.getNode()) != d.find(v.getNode())) {
                // Les sommets v et u ne sont pas dans le même composant
                // Si les deux sommets sont déjà dans le même ensemble, 
                // ils le sont grace à des arête de poids plus faible, 
                // car on les traite par ordre croissant 
                // donc il est inutile de chercher 
                // à remplacer une autre arête par celle-là.
                
                // L'arête est à ajouter à l'ARPM 
                // donc on l'ajoute à l'ensemble E
                tree.add(e);

                // On a gardé l'arête entre u et v donc 
                // on fusionne les ensembles pour représenter ce lien
                d.union(u.getNode(), v.getNode());
            }
        }
    }


    /**
     * Accesseur.
     *
     * @return On retourne l'ARPM (et on affiche ses arêtes et son coût)
     */
    public ArrayList<Edge> getARPM() {
        
        float sum = 0;
        System.out.println("");
        System.out.println("Voici l'ARPM G�n�r� par Kruskal : ");
        System.out.println("");
        for (Edge e : tree) {
            System.out.println(e.getU().getNode().index + " " + e.getV().getNode().index);
            //System.out.println(e.getU().getNode().index+" "+e.getV().getNode().index+" "+(e.getV().getNode().parent!=null?e.getV().getNode().parent.index:""));
            sum += e.getWeight();
        }

        System.out.println("");
        System.out.println("Le cout du parcours de l'ARPM est de : " + sum);

        
        return tree;
    }

}
