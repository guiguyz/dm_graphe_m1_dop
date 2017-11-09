
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

        // Cr�er la partition d'un ensemble 
        UnionFind d = new UnionFind(vertices);

        // On cr�e une ArrayList d'ar�te pour stocker l'ARPM
        tree = new ArrayList<Edge>();

        // On trie les ar�tes pour pouvoir les traiter 
        // par ordre croissant de poids, car les ar�tes de
        // poids faible sont plus int�ressantes(complexit� en nlog(n))
        Collections.sort(edges);

        // Dans l'ordre des poids des ar�tes, on va regarder 
        // si l'ar�te est � ajouter � l'ensemble
        for (Edge e : edges) {
            Vertex u = e.getU();
            Vertex v = e.getV();
            // On v�rifie que l'ar�te ne relie pas deux sommets 
            // d'un m�me ensemble, car le r�sultat attendu est un ARPM 
            // et une telle ar�te cr�erait un cycle : 
            // ce ne serait donc plus un arbre.
            if (d.find(u.getNode()) != d.find(v.getNode())) {
                // Les sommets v et u ne sont pas dans le m�me composant
                // Si les deux sommets sont d�j� dans le m�me ensemble, 
                // ils le sont gr�ce � des arr�te de poids plus faible, 
                // car on les traite par ordre croissant 
                // donc il est inutile de chercher 
                // � remplacer une autre arr�te par celle-l�.
                
                // L'arr�te est � ajouter � l'ARPM 
                // donc on l'ajoute � l'ensemble E
                tree.add(e);

                // On a gard� l'ar�te entre u et v donc 
                // on fusionne les ensembles pour repr�senter ce lien
                d.union(u.getNode(), v.getNode());
            }
        }
    }


    /**
     * Accesseur.
     *
     * @return On retourne l'ARPM (et on affiche ses ar�tes et son co�t)
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
