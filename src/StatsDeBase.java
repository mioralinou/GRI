
public class StatsDeBase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = Graph.generateFromDotFile(args[0]);
		
		System.out.format("%d %d %d %d %d%n", g.getNbNode(), g.getNbEdge(), g.getDegreMaxSortant(), g.getDegreMaxEntrant(), g.getMaxNbNode());
	}

}
