
import java.util.*;

@SuppressWarnings("unchecked")

/*
 * Ce programme exécute un test empirique de l'algorithme de Kruskal, 
 * en utilisant une structure de données efficace.
 *
 * Le programme génère deux graphiques aléatoires et complets G_1 = (V_1, E_1) et G_2 = (V_2, E_2) composés chacun de n sommets et de n-choix2 arêtes.
 G_1 est un graphique dont les bords E_1 sont de poids aléatoire dans la plage[0,1].
 G_2 est un graphique dont les sommets V_2 sont marqués avec des coordonnées aléatoires dans l'unité carrée, et E_2 se compose de bords dont les poids sont les distances euclidiennes entre deux sommets dans V_2.
 *
 *  Le programme génère ces graphiques et exécute la portée minimale de Kruskal.
 sur eux, en imprimant le poids total de l'arbre pour chaque test.
 *
 * 
Le programme doit être invoqué depuis la ligne de commande avec deux entiers:
 la semence qui doit être utilisée pour le générateur de nombres aléatoires, et la méthode
nombre de sommets dans les graphiques générés aléatoirement.
 */
public class Kruskal {

    private final ArrayList<Vertex> vertices;
    private final ArrayList<Edge> edges;

//    public static void main(String[] args) {
//        /* Take program arguments */
//        int seed, n;
//
//        try {
//            seed = Integer.parseInt(args[0]);
//            n = Integer.parseInt(args[1]);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("Usage: java Kruskal <seed> <number of vertices>");
//            return;
//        }
//
//        int ne = (n * (n - 1)) / 2;     // Number of edges in a complete graph
//
//        /* Run the tests for size n */
//        float test1 = randomEdgeWeightTest(seed, n, ne);
//        float test2 = randomVertexDistanceTest(seed, n, ne);
//
//        System.out.printf("Test results for size %d:\n", n);
//        System.out.printf("\trandom edge weight test: %f\n", test1);
//        System.out.printf("\trandom vertex distance test: %f\n", test2);
//    }

    public Kruskal(ArrayList<Vertex> vertices, ArrayList<Edge> edges){
        this.vertices=vertices;
        this.edges=edges;
        
        /* Create the disjoint-set data structure */
        DisjointSet d = new DisjointSet(vertices);

        /* Create a list of edges */
        ArrayList<Edge> tree = new ArrayList<Edge>();

        /* Java's modified version of mergesort guarantees nlog(n) time here */
        Collections.sort(edges);
        
        /* Kruskal's algorithm */
        for (Edge e : edges) {
            Vertex u = e.getU();
            Vertex v = e.getV();
            //System.out.println("poids de l'arrete e : "+e.getWeight()+" entre (u,v) : ("+u.getNode().index+","+v.getNode().index+")");
            if (d.find(u.getNode()) != d.find(v.getNode())) {
                /* Vertices v and u are not in the same component */
                tree.add(e);

                /* Union them in the tree */
                d.union(u.getNode(), v.getNode());
            }
        }
        
        for (Node n : d.rootNodes) {
            if(n.parent!=null){
                System.out.println(n.rank+" "+n.index+" "+n.parent.index); 
                
            }else{
                System.out.println(n.rank+" "+n.index);
            }
                       
        }

        
    }

    /*
   * Generates edges of a complete graph where each edge has a random
   * weight in [0, 1] and returns the total weight of its minimum spanning
   * tree using Kruskal's algorithm.
     */
    public static float randomEdgeWeightTest(int seed, int n, int ne) {
        /* Initialize random number generator */
        Random r = new Random(seed);

        /* Create a list of vertices */
        ArrayList<Vertex> vertices = new ArrayList<Vertex>(n);

        /* Create a list of edges */
        ArrayList<Edge> edges = new ArrayList<Edge>(ne);

        for (int i = 0; i < n; i++) {
            vertices.add(new Vertex(0, 0));
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                edges.add(new Edge(vertices.get(i), vertices.get(j), r.nextFloat()));
            }
        }

        /* Create the disjoint-set data structure */
        DisjointSet d = new DisjointSet(vertices);

        /* Create a list of edges */
        ArrayList<Edge> tree = new ArrayList<Edge>();

        /* Java's modified version of mergesort guarantees nlog(n) time here */
        Collections.sort(edges);

        /* Kruskal's algorithm */
        for (Edge e : edges) {
            Vertex u = e.getU();
            Vertex v = e.getV();
            System.out.println("poids de l'arrete e : "+e.getWeight()+" entre (u,v) : ("+u.getNode().index+","+v.getNode().index+")");
            if (d.find(u.getNode()) != d.find(v.getNode())) {
                /* Vertices v and u are not in the same component */
                tree.add(e);

                /* Union them in the tree */
                d.union(u.getNode(), v.getNode());
            }
        }

        /* Return the sum of the weights of all edges in the tree */
        return Edge.sum(tree);
    }


    /*
   * Generates vertices containing random coordinates inside the unit
   * square and calculates the weights of each edge (u, v) as the Euclidean
   * distance between the vertices u and v, and returns the total weight of the
   * graph's minimum spanning tree using Kruskal's algorithm.
     */
    public static float randomVertexDistanceTest(int seed, int n, int ne) {
        /* Initialize random number generator */
        Random r = new Random(seed);

        /* Create a list of vertices */
        ArrayList<Vertex> vertices = new ArrayList<Vertex>(n);

        /* Create a list of edges */
        ArrayList<Edge> edges = new ArrayList<Edge>(ne);

        /* Generate vertices with random x and y coordinates */
        for (int i = 0; i < n; i++) {
            vertices.add(new Vertex(r.nextFloat(), r.nextFloat()));
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                Vertex a = vertices.get(i);
                Vertex b = vertices.get(j);

                /*
         * Use a simplified distance formula to calculate the distance 
         * between vertices a and b
                 */
                Edge e = new Edge(a, b, Vertex.simpleDistance(a, b));
                edges.add(e);
            }
        }

        /* Create the disjoint-set data structure */
        DisjointSet d = new DisjointSet(vertices);

        /* Create a list of edges */
        ArrayList<Edge> tree = new ArrayList<Edge>();

        /* Java's modified version of mergesort guarantees nlog(n) time here */
        Collections.sort(edges);

        /* Kruskal's algorithm */
        for (Edge e : edges) {
            Vertex u = e.getU();
            Vertex v = e.getV();
            if (d.find(u.getNode()) != d.find(v.getNode())) {
                /* Vertices v and u are not in the same component */
                tree.add(e);

                /* Union them in the tree */
                d.union(u.getNode(), v.getNode());
            }
        }


        /*
     * Before summing, complete the final vertex distance calculation; we
     * achieve a slight speed-up here because there will be strictly less
     * than nC2 edges in the minimum spanning tree.
         */
        float sum = 0;

        for (Edge e : tree) {
            sum += Math.sqrt(e.getWeight());
        }

        /* Now return the sum */
        return sum;
    }
}


/*
 * Classe représentant un seul sommet, tenant un pointeur vers un noeud en
 * la structure de données disjoint-set. Contient également des fonctions de calcul
 * distances simples et euclidiennes.
 */
class Vertex {

    private float x;
    private float y;
    private Node n;

    public Vertex(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setNode(Node n) {
        this.n = n;
    }

    public Node getNode() {
        return this.n;
    }

    public static float simpleDistance(Vertex a, Vertex b) {
        return (float) (Math.pow((b.y - a.y), 2) + Math.pow((b.x - a.x), 2));
    }

    public static float euclideanDistance(Vertex a, Vertex b) {
        return (float) Math.sqrt(Vertex.simpleDistance(a, b));
    }
}


/*
 * Classe représentant une arête unique, avec des pointeurs vers les sommets. 
 * cela inclut également des facilités pour calculer les poids des arêtes.
 */
class Edge implements Comparable {

    private float weight;
    private Vertex u, v;

    public Edge(Vertex u, Vertex v) {
        this.u = u;
        this.v = v;
    }

    public Edge(Vertex u, Vertex v, float w) {
        this(u, v);
        this.weight = w;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float w) {
        this.weight = w;
    }

    public Vertex getU() {
        return this.u;
    }

    public Vertex getV() {
        return this.v;
    }

    public int compareTo(Object o) {
        Edge other = (Edge) o;

        if (this.getWeight() < other.getWeight()) {
            return -1;
        } else if (this.getWeight() > other.getWeight()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static float sum(List<Edge> edges) {
        float sum = 0;

        for (Edge e : edges) {
            sum += e.getWeight();
        }

        return sum;
    }
}


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


/*
 * Une implémentation simple de la structure de données disjointe.
 * Les éléments d'ensembles disjoints sont représentés dans un arbre,
 * dans lequel chaque noeud fait référence à son parent.
 * Une stratégie d'union par rang est utilisée
 * pour optimiser la combinaison de deux arbres,
 * en veillant à toujours attacher un arbre plus petit 
 * à la racine de l'arbre le plus grand.
 */
class DisjointSet {

    private int nodeCount = 0;
    private int setCount = 0;

    ArrayList<Node> rootNodes;

    /*
     * Retourne l'index de l'ensemble dans lequel n est actuellement présent.
     * L'index du noeud racine de chaque ensemble identifie de façon unique l'ensemble.
     * Ceci est utilisé pour déterminer si deux éléments sont dans le même ensemble.
     */
    public int find(Node n) {
        Node current = n;

        /* Monter le pointeur jusqu'au noeud racine  */
        while (current.parent != null) {
            current = current.parent;
        }

        Node root = current;

        /*
         * Remontez le pointeur jusqu'au noeud racine, 
         * mais faires en sorte que chaque noeud soit au-dessous 
         * d'un enfant directement à la racine.
         * Ceci modifie l'arborescence de sorte que 
         * les appels futurs atteindront la racine plus rapidement.
         */
        current = n;
        while (current != root) {
            Node temp = current.parent;
            current.parent = root;
            current = temp;
        }

        return root.index;
    }


    /*
     * Combine les ensembles contenant les noeuds i et j.
     */
    public void union(Node i, Node j) {
        int indexI = find(i);
        int indexJ = find(j);

        /* Ces noeuds font-ils déjà partie du même ensemble? */
        if (indexI == indexJ) {
            return;
        }

        /* Obtenir les noeuds racine de chaque ensemble (cela s'exécutera en temps constant) */
        Node a = this.rootNodes.get(indexI);
        Node b = this.rootNodes.get(indexJ);

        /* Attachez l'arbre le plus petit à la racine de l'arbre le plus grand. */
        if (a.rank < b.rank) {
            a.parent = b;
        } else if (a.rank > b.rank) {
            b.parent = a;
        } else {
            b.parent = a;
            a.rank++;
        }

        this.setCount--;
    }


    /*
     * Prend une liste de n sommets et crée n ensembles disjoints singleton.
     */
    public void makeSets(List<Vertex> vertices) {
        for (Vertex v : vertices) {
            makeSet(v);
        }
    }


    /*
     * Crée un ensemble singleton contenant un sommet.
     */
    public void makeSet(Vertex vertex) {
        Node n = new Node(0, rootNodes.size(), null);
        vertex.setNode(n);
        this.rootNodes.add(n);
        this.setCount++;
        this.nodeCount++;
    }

    /*
     * Prend une liste de n sommets et appel makeSets sur cette liste.
     */
    public DisjointSet(List<Vertex> vertices) {
        this.rootNodes = new ArrayList<Node>(vertices.size());
        makeSets(vertices);
    }
    
    public void getVal(){
   
        
        
        
    }
}

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author GUY
// */
//
//import java.util.Scanner;
//import java.util.Arrays;
//
//class Kruskal{
//	Trio[] graph;
//	int[] cmp;
//	int[] siz;
//
//	
//	void begin(){
//		Scanner l = new Scanner(System.in);
//		int V,E;
//		
//		int x,y;
//		double w;
//		
//		
//		System.out.println("Introduce la cantidad de V E: ");
//		V = l.nextInt();
//		E = l.nextInt();
//		
//		graph = new Trio[E];
//		cmp = new int[V];
//		siz = new int[V];
//		
//		for(int i=0;i<E;i++){
//			x = l.nextInt();
//			y = l.nextInt();
//			w = l.nextDouble();
//			
//			graph[i] = new Trio(x,y,w);
// 		}
//		
//		w = kruskal(graph, V, E);
//		System.out.println("MST: "+w);
//	}
//	
//	double kruskal(Trio[] graph,int V,int E){
//		Trio temp;
//		int x,y;
//		double w, mst = 0.0;
//		
//		init(V);
//		
//		//do you even sort, bro?
//		Arrays.sort(graph);
//		
//		for(int i=0;i<E;i++){
//			temp = graph[i];
//			x = temp.x;
//			y = temp.y;
//			w = temp.w;
//			
//			//Need to compare if they are in the same component , disjoint-set, attacks!
//			mst+= (union(x,y)==1)?w:0;
//		}
//		
//		return mst;
//	}
//	
//	int find(int x){
//		//Classic find, featuring path-compression
//		return (x == cmp[x])?x:(cmp[x] = find(cmp[x]));
// 	}
//	
//	int union(int x,int y){
//		int cmpx, cmpy;
//		
//		cmpx = find(x);
//		cmpy = find(y);
//		if(cmpx == cmpy) return 0;
//		else{
//			if(siz[cmpx] > siz[cmpy]){
//				//The tree at component X is clearly larger
//				cmp[cmpx] = cmpy;
//				siz[cmpy] += siz[cmpx];
//			}
//			else{
//				cmp[cmpy] = cmpx;
//				siz[cmpx] += siz[cmpy];
//			}
//		}
//		return 1;
//	}
//	
//	void init(int V){
//		for(int i=0;i<V;i++){
//			cmp[i] = i;
//			siz[i] = 1;
//		}
//	}
//}
//
//class Trio implements Comparable<Trio>{
//	Trio(int _x, int _y, double _w){
//		x = _x;
//		y = _y;
//		w = _w;
//	}
//	
//	public int compareTo(Trio otro){
//		return (this.w > otro.w)?1:((this.w == otro.w)?0:-1);
//	}
//	int x,y;
//	double w;
//}
