import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Graph {

	private Node adjencyList[];
	private int nbEdge;
	private int degreMaxEntrant;
	private int maxNbNode;

	public Node[] getAdjencyList() {
		return adjencyList;
	}

	/**
	 * 
	 * @param n Nombre de sommets
	 */
	public Graph(int n) {
		this.adjencyList = new Node[n+1];
	}
	
	public static int[] readLine(String line) {
		int result[] = new int[2];
		line = line.replace(';', ' ').trim();
		String edgeData[] = line.split("->");
		
		if (line.contains("->")) {
			// Extraction du sommet de départ
			result[0] = Integer.parseInt(edgeData[0].trim());
			
			// Extraction du sommet d'arrivée
			result[1] = Integer.parseInt(edgeData[1].trim());
		} else {
			result[0] = Integer.parseInt(edgeData[0].trim());
			result[1] = -1;
		}
		
		return result;
	}
	
	public static int getNodeMaxNumber(String filename) {
		int result = 0;
		
		try (Scanner scanner =  new Scanner(new FileInputStream(filename), StandardCharsets.UTF_8.name())){
	    	// Positionnement du scanner sur la deuxième ligne
	    	if (scanner.hasNextLine()){
	    		scanner.nextLine();
	    	}
	    	
	        while (scanner.hasNextLine()){
	        	String line = scanner.nextLine();
	        	if(line.trim().equals("}")){
	        		break;
	        	}
	        	
	        	int edge[] = readLine(line);
	        	if (edge[0] > result) {
	        		result = edge[0];
	        	}
	        	if (edge[1] > result) {
	        		result = edge[1];
	        	}
	          
	        }      
	      } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Load graph from file
	 * 
	 * @param filename
	 * @return Graph return graph object
	 */
	public static Graph generateFromDotFile(String filename) {
		int maxNbNode = getNodeMaxNumber(filename);
		Graph graph = new Graph(maxNbNode);
		// Spécifier le numéro maximum de nodes du graphe
		graph.maxNbNode = maxNbNode;
		
		try (Scanner scanner =  new Scanner(new FileInputStream(filename), StandardCharsets.UTF_8.name())){
	    	// Positionnement du scanner sur la deuxième ligne
	    	if (scanner.hasNextLine()){
	    		scanner.nextLine();
	    	}
	    	
	        while (scanner.hasNextLine()){
	        	String line = scanner.nextLine();
	        	if(line.trim().equals("}")){
	        		break;
	        	}
	        	
	        	int edge[] = readLine(line);
	        	// Tester si sommet de départ est déjà dans le graphe
	        	// Si oui, récupérer
	        	// Si non, l'ajouter au graphe et le récupérer
	        	Node startNode = graph.insertNode(edge[0]);
	        	
	        	// Faire pareil pour sommet d'arrivée
	        	if (edge[1] != -1) {
	        		Node endNode = graph.insertNode(edge[1]);
	        		// Ajouter le sommet d'arrivée en tant que voisin du sommet de départ
	        		startNode.addChild(endNode);
	        		// Incrémenter le nombre d'arcs
	        		graph.nbEdge++;
	        	}
	        	
	        }      
	      } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return graph;
	}
	
	/**
	 * 
	 * @param n Numéro de sommet
	 * @return
	 */
	public Node insertNode(int n) {
		if (this.adjencyList[n] == null) {
			this.adjencyList[n] = new Node(n);
		}
		
		return this.adjencyList[n];
	}

	public int getNbNode() {
		int nbNode = 0;
		for (int i = 0; i < adjencyList.length; i++) {
			if (adjencyList[i] != null) {
				nbNode++;
			}
		}
		
		return nbNode;
	}

	public int getNbEdge() {
		return nbEdge;
	}

	public int getDegreMaxSortant() {
		int max = 0;
		for (int i = 0; i < adjencyList.length; i++) {
			if (adjencyList[i] != null) {
				if (adjencyList[i].degSortant() > max) {
					max = adjencyList[i].degSortant() ;
				}
			}
		}
		
		return max;
	}

	public int getDegreMaxEntrant() {
		int max = 0;
		for (int i = 0; i < adjencyList.length; i++) {
			if (adjencyList[i] != null) {
				if (adjencyList[i].degEntrant() > max) {
					max = adjencyList[i].degEntrant() ;
				}
			}
		}		
		return max;
	}

	public int getMaxNbNode() {
		return maxNbNode;
	}
}
