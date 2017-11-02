import java.util.ArrayList;

/**
 *
 * @author GUY
 */
public class DeuxApproximation {

    private final ArrayList<Edge> tree;

    DeuxApproximation(ArrayList<Vertex> vertices,ArrayList<Edge> tree) {
        this.tree=(ArrayList<Edge>) tree.clone();
    }
    
    
    // ARPM de Kruskal en entrée
    public void run(){
    
    

    boolean amelioration = true;

        while (amelioration) {
            amelioration = false;

            for (int i = 0; i < tree.size(); i++) {
                for (int j = i + 2; j < tree.size(); j++) {

                    int iplus1 = (i + 1) % tree.size();
                    int jplus1 = (j + 1) % tree.size();

                    // Distance (i, i+1)
//                    double distance1 = tree.get(i).getWeight();
//                    System.out.println(distance1);
//                    double distanceP1 = tree.get(i+1).getWeight();
//                    System.out.println(distanceP1);
                            //tournee[i].distance(tournee[iplus1]);

//                    // Distance (j, j+1)
//                    double distance2 = tournee[j].distance(tournee[jplus1]);
//
//                    // Distance (i, j)
//                    double distanceA = tournee[i].distance(tournee[j]);
//
//                    // Distance (i+1, j+1)
//                    double distanceB = tournee[iplus1].distance(tournee[jplus1]);
//
//                    // On regarde s'il y a amelioration
//                    if (distance1 + distance2 > distanceA + distanceB + 1E-10) {
//                        // Inversion des elements entre i + 1 et j
//                        int y = j;
//
//                        for (int x = iplus1; x < y; x++) {
//                            Ville tmp2 = tournee[x];
//                            tournee[x] = tournee[y];
//                            tournee[y] = tmp2;
//                            y--;
//                        }
//                        amelioration = true;
//                    }
                }
            }
        }

        // Calcul de la longueur totale
        double longueurTotale = 0;
        for (int i = 0; i < tree.size(); i++) {
//            longueurTotale += tournee[i].distance(tournee[(i + 1)% tournee.length]);
        }

        //return longueurTotale;
    }
}
