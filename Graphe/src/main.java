
import java.util.ArrayList;

@SuppressWarnings("unchecked")

/**
 * 
 * @author Benjamin Lemaitre
 * @author Guillaume Drouart
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
        ArrayList<Edge> tree = kr.getARPM();
        
//        float sum = 0; 
//        System.out.println("ARPM");
//        for (Edge e : tree) {
//            System.out.println(e.getU().getNode().index+" "+e.getV().getNode().index);
//            //System.out.println(e.getU().getNode().index+" "+e.getV().getNode().index+" "+(e.getV().getNode().parent!=null?e.getV().getNode().parent.index:""));
//            sum += e.getWeight();
//        }
//
//        System.out.println("");
//        System.out.println("taille de l'ARPM : "+sum);
        
        DeuxApproximation DeuxApp = new DeuxApproximation(vertices,tree);
        System.out.println(DeuxApp.run());

    }

}
