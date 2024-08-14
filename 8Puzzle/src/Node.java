import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Node {
	
	private Board data;
	private Node child;
	
	
	public Node(Board data) {
		this.data = data;
	}
	
	public Board getData() {
		return data;
	}
	
	public void setChild(Node child) {
		this.child = child;
	}

}

class Tree{
	private Node root;
	private HashMap<Node, List<Board>> treeMap = new HashMap();

	
	public Tree() {
		this.root = null;
	}
	
	public void setRoot(Node r) {
		this.root = r;
	}
	
	public HashMap<Node, List<Board>> getTreeMap(){
		return treeMap;
	}
	
	public void addToTree(Node n) {
		treeMap.putIfAbsent(n,  new ArrayList<>());
	}
	
	public void addChild(Node a,Board b) {
		treeMap.get(a).add(b);
	}
	
	public Node getNode(Board b){
		for (Entry<Node, List<Board>> e : treeMap.entrySet()) {
			if(e.getKey().getData().getString().equals(b.getString())){
				return e.getKey();
			}
		}
		return null;
	}
	
	public List<Board> getList(Node n){
		return this.treeMap.get(n);
	}
	
}
