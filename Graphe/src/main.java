
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gui
 */
public class main {

    /**
     * Charge un fichier TSP et lance la resolution du laboratoire
     *
     * @param args Nom du fichier TSP a charger
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

        /* Create a list of vertices */
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

        // Stoque les distances entre les villes
        for (int i = 0; i < n; i++) {
            //System.out.println(vertices.get(i)+" "+vertices.get(i).getNode());
        }

        /* Create a list of edges */
        ArrayList<Edge> edges = new ArrayList<Edge>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i == j) {
                    continue;
                }
                Vertex a = vertices.get(i);
                Vertex b = vertices.get(j);

                /*
         * Use a simplified distance formula to calculate the distance 
         * between vertices a and b
                 */
                Edge e = new Edge(a, b, Vertex.euclideanDistance(a, b));
                edges.add(e);
            }
        }

        Kruskal kr = new Kruskal(vertices, edges);

    }

}
