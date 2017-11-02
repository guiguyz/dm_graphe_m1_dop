 /*
 * Implémentation d'un noeud, à utiliser avec l'arborescence DisjointSet
 * structure.
 * Implementation of a node, to be used with the DisjointSet tree
 * structure.
 */
class Node {

    int rank;      // le nombre approximatif de noeuds sous ce noeud
    int index;     // un index unique pour chaque noeud de l'arborescence
    Node parent;

    public Node(int r, int i, Node p) {
        this.rank = r;
        this.index = i;
        this.parent = p;
    }
    
}