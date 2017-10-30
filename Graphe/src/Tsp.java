
import java.text.DecimalFormat;

/**
 * Relaxation lagrangienne et voyageur de commerce
 *
 * @author Constantin Laurent
 * @author Gander Jonathan
 */
/**
 *
 * @author
 */
public class Tsp {

    /**
     * Reffectue la relaxion lagrangienne sur un graph complet. La pondreation
     * du graph est donne par les distances entre villes.
     *
     * @param villes Villes du graph.
     * @param la tournee
     */
    public static Ville[] relaxation(Ville[] villes) {

        // Choix d'alpha
        //double alpha = 1.9;
        // Initialise un 1-arbre
        Arbre arbre = new Arbre(villes);

        // Initalisation de la borne
        double borne = arbre.bornePonderee();
        double ancienneBorne = borne;

        // La mise a jour ne se fait pas a la premiere iteration
        boolean premiereIteration = true;

        // Indique si l'arbre est une tournee
        boolean estUneTournee = false;

        // Affichage
        DecimalFormat df = new DecimalFormat("########.00");

        do {
            // Des la deuxieme iteration
            if (!premiereIteration) {

                // Met a jour le 1-arbre, 
                arbre.miseAJour();

                // Calcule de la nouvelle borne
                ancienneBorne = borne;
                borne = arbre.bornePonderee();
            }

            // Mise a jour des lambdas
            /*
			 * TODO Implementer la consigne
			 * double somme = 0.0;
			 * for (int i = 0; i < villes.length; i++) {
			 * somme += ((arbre.degSommet(i) - 2) * (arbre.degSommet(i) - 2));
			 * }
			 * // Evite une division par 0
			 * if (somme == 0.0) {
			 * System.err.println("Erreur de somme");
			 * somme = 1;
			 * }
             */
            // Calcule du nouveau lambda pour tous les sommets
            for (int i = 0; i < villes.length; i++) {
                /* TODO  Implementer la consigne
				 * double increment = alpha * (arbre.borneSupp() - borne)
				 * * (arbre.degSommet(i) - 2) / somme;
				 * System.out.println("Increment : " + increment);
				 * arbre.incrementLambda(i, increment);
                 */
                arbre.incrementLambda(i, arbre.degSommet(i) - 2);
            }

            // Indique si l'arbre represente une tournee
            estUneTournee = arbre.estUneTournee();

            // Affiche les information de l'iteration courante
//			System.out.printf("Borne: %s (etait %s) tournee: %s\n", df
//					.format(borne), df.format(ancienneBorne),
//					(estUneTournee ? "oui" : "non"));
            /*
			 * Si la borne ne monte pas, on change alpha
			 * TODO Implementer la donnee
			 * if (borne <= ancienneBorne) {
			 * alpha *= 0.9;
			 * System.out.println("Alpha :" + alpha);
			 * }
             */
            premiereIteration = false;
            // Continue tant qu'on a une amelioration ou une solution non-acceptable
        } while (borne > ancienneBorne || !estUneTournee || premiereIteration);
        // Renvoie la tournee;
        return arbre.tournee();
    }

    /**
     * Charge un fichier TSP et lance la resolution du laboratoire
     *
     * @param args Nom du fichier TSP a charger
     */
    public static void main(String[] args) {
        // Obtient le nom du fichier TSP
        String file = "data/xqf131.tsp";
        if (args.length == 1) {
            file = args[0];
        }
        if (file == null || file.trim().isEmpty() || args.length > 1) {
            System.out.println("Vous devez specifier un fichier TSP");
            return;
        }
        // Parse les villes depuis le fichier TSP
        Ville[] villes;
        try {
            villes = Ville.parse(file);
        } catch (Exception e) {
            villes = null;
            e.printStackTrace();
        }
        // Quitte en cas d'erreur
        if (villes == null) {
            System.err.println("Erreur lors de la lecture du fichier TSP");
            System.exit(-1);
            return;
        }
        
        //afficher les id, les coordonnées et les distances entre les villes
        for (int i = 0; i < villes.length; i++) {

            Ville ville = villes[i];
            System.out.println("id : "+ville.getId()+" coord : ("+ ville.x + "," + ville.y+")");
            for (int j = 0; j<villes.length; j++) {
                Ville ville1 = villes[j];
                System.out.println("distance entre ville1 "+ville.getId()+" et ville2 "+ville1.getId()+" : "+ville1.distance(ville));
                
            }
            

        }

        // Lance la relaxation
//        Ville tournee[] = relaxation(villes);

        // Affiche la tournee
//        System.out.println("Tournee:");
//        for (Ville v : tournee) {
//            System.out.println(v);
//        }
    }
}
