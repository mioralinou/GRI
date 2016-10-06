import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private int nodeName;
	private List<Node> neighboor;
	private List<Node> previous;
	
	public Node(int nodeName) {
		this.nodeName = nodeName;
		this.neighboor = new ArrayList<Node>();
		this.previous = new ArrayList<Node>();
	}
	
	public void addChild(Node child) {
		neighboor.add(child);
		child.previous.add(this);
	}

	public int getNodeName() {
		return nodeName;
	}

	public void setNodeName(int nodeName) {
		this.nodeName = nodeName;
	}
	
	public int degEntrant() {
		return previous.size();
	}
	
	public int degSortant() {
		return neighboor.size();
	}
}
