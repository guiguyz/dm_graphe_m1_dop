
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Class Verices pour créer une ArrayList de sommets
 * 
 * @author Benjamin Lemaitre
 * @author Guillaume Drouart
 */
public class Vertices {

    /**
     * Parser un fichier de la TSPLIB
     *
     * @param FILENAME Nom du fichier
     * @return ArrayList des sommets
     * @throws IOException
     */
    public static ArrayList<Vertex> parse(String FILENAME) throws IOException {
        // Lecture du fichier
        BufferedReader br = new BufferedReader(new FileReader(FILENAME));
        // On instancie une ArrayList de Sommet
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        // Ligne lue
        String strLine = null;
        // On lit le fichier ligne par ligne jusqu'a EOF (End Of File)
        int i = 0;
        while ((strLine = br.readLine()) != null && !strLine.equals("EOF")) {
            
            // On separe avec les espaces
            String[] element = strLine.split(" ");

            // Tant que on a pas une suite de trois chiffres avec trois espaces
            // on va a ligne suivante
            if (element.length != 3 || !Pattern.matches("[0-9].*", element[0])) {
                continue;
            }
            
            // On parse les valeurs
            int id = Integer.parseInt(element[0]);
            float x = Float.parseFloat(element[1]);
            float y = Float.parseFloat(element[2]);
            
            // On ajoute le sommet dans l'ArrayList
            if (vertices == null) {
                break;
            }

            Vertex v = new Vertex(x, y);

            vertices.add(i, v);

            // On s'assure que la ville ayant l'id "i" soit dans l'ArrayList
            if (id - 1 != i) {
                br.close();
                throw new IllegalArgumentException("Erreur de numerotation");
            }
            i++;

        }
        // On ferme le descripteur de fichier
        br.close();
        // On retourne l'ArrayList
        return vertices;
    }

}
