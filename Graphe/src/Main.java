
import java.util.ArrayList;

@SuppressWarnings("unchecked")

/**
 *
 * @author Benjamin Lemaitre
 * @author Guillaume Drouart
 */
public class Main {

    /**
     * Point d'entree de la JVM.
     * 
     * Charge un fichier TSP 
     * Cr�e une ArrayList des sommets du fichier
     * Cr�e une ArrayList d'ar�te des sommets du fichier
     * Instancie et execute Kruskal
     * Instancie et execute DeuxApproximation
     *
     * @param args les arguments de la ligne de commandes
     *  (Nom du fichier TSP a charger)
     */
    public static void main(String[] args) {

        // Obtient le nom du fichier TSP
        String file = "data/dm.tsp";//xqg237 xqf131
        if (args.length == 1) {
            file = args[0];
        }
        if (file == null || file.trim().isEmpty() || args.length > 1) {
            System.out.println("Vous devez specifier un fichier TSP");
            return;
        }

        // Liste de sommet du graphe
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();

        try {
            vertices = Vertices.parse(file);
        } catch (Exception e) {
            vertices = null;
            e.printStackTrace();
        }
        
        // Quitte en cas d'erreur
        if (vertices == null) {
            System.err.println("Erreur lors de la lecture du fichier TSP");
            System.exit(-1);
            return;
        }

        int n = vertices.size();

        // Liste d'ar�te du graphe
        ArrayList<Edge> edges = new ArrayList<Edge>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i == j) {
                    continue;
                }
                Vertex a = vertices.get(i);
                Vertex b = vertices.get(j);

                
                // Un ar�te e contient deux sommet a et b et
                // leur distance euclidienne entre eux
                // qui est le poids de l'ar�te
                Edge e = new Edge(a, b, Vertex.euclideanDistance(a, b));
                edges.add(e);
            }
        }

        // G�n�rer un ARPM avec Kruskal
        Kruskal kr = new Kruskal(vertices, edges);
        ArrayList<Edge> tree = kr.getARPM();

        // G�n�rer une deux-approximation sur l'ARPM
        DeuxApproximation DeuxApp = new DeuxApproximation(vertices, tree);
        DeuxApp.run();

    }

}
