
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * 
 * @author Benjamin Lemaitre
 * @author Guillaume Drouart
 */
public class Vertices {

    /**
     * Parse un fichier de la TSPLIB
     *
     * @param FILENAME Nom du fichier
     * @return ArrayList des villes
     * @throws IOException
     */
    public static ArrayList<Vertex> parse(String FILENAME) throws IOException {
        // Lecture du fichier
        BufferedReader br = new BufferedReader(new FileReader(FILENAME));
        // ArrayList de Sommet
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        // Ligne lue
        String strLine = null;
        // Lit le fichier ligne par ligne
        int i = 0;
        while ((strLine = br.readLine()) != null && !strLine.equals("EOF")) {
            // Separe avec les espaces
            String[] element = strLine.split(" ");

            // Ignore les autres commentaires
            if (element.length != 3 || !Pattern.matches("[0-9].*", element[0])) {
                continue;
            }
            // Ligne OK, on parse les valeurs
            int id = Integer.parseInt(element[0]);
            float x = Float.parseFloat(element[1]);
            float y = Float.parseFloat(element[2]);
            // Ajout de la ville au tableau
            if (vertices == null) {
                break;
            }

            Vertex v = new Vertex(x, y);

            vertices.add(i, v);

            // S'assure que la ville ayant l'id "i" soit dans tab[i]
            if (id - 1 != i) {
                br.close();
                throw new IllegalArgumentException("Erreur de numerotation");
            }
            i++;

        }
        // Ferme le descripteur de fichier
        br.close();
        // Retourne l'ArrayList
        return vertices;
    }

}
