
public class Parcours {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = Graph.generateFromDotFile(args[0]);
		
		int nodeName = Integer.parseInt(args[1]);
		Node current = g.getAdjencyList()[nodeName];
		
		System.out.println(current.degSortant() + 1);
		
		
	}

}
