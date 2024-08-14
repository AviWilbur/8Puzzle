import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;


public class AStar {
	private static Board goal = new Board("012345678");
	private static List<Board> closed = new ArrayList();
	private static Graph myGraph = new Graph();
	private static Tree myTree = new Tree();

	
	public static PartialSolution search(Board board, char type, String heuristics) {
		if (type == 'g') {
			myGraph.addBoard(board);
		}
		if (type == 't') {
			Node root = new Node(board);
			myTree.setRoot(root);
			myTree.addToTree(root);
		}
		//initial frontier
		PriorityQueue<PartialSolution> frontier = new PriorityQueue<PartialSolution>();
		//INITIALIZATION CODE...
		PartialSolution start = new PartialSolution(heuristics);
		start.addPs(board);
		board.GetZero();
		frontier.add(start);
		
		while (!board.getString().equals(goal.getString())) {
			//A* IMPLEMENTATION
			PartialSolution current = frontier.poll();      //take out first ps
			int i = current.getList().size() -1;
			board = current.getList().get(i);
			if (board.checkIfIn(closed)) {
				closed.add(board);           // add it to closed set
				board.GetZero();
				getNieghbors(board,type, board.getZeroI(), board.getZeroJ());
			}
			if(type == 'g') {
				for (Board a : myGraph.getEdges(board)) {	// each edge gets added to a new list with the whole path then add list to frontier
						 PartialSolution ps = new PartialSolution(heuristics);
						 ps.getList().addAll(current.getList());		// add old list to new list
						 ps.setM(heuristics, current.getM(heuristics));
						 ps.addPs(a);
						 frontier.add(ps);
				}
			}
			if (type == 't') {
				for(Board x: myTree.getList(myTree.getNode(board))) {
					 PartialSolution ps = new PartialSolution(heuristics);
					 ps.getList().addAll(current.getList());		
					 ps.setM(heuristics,current.getM(heuristics));
					 ps.addPs(x);
					 frontier.add(ps);
				}
			}
			int z = frontier.peek().getList().size(); 
			board = frontier.peek().getList().get(z -1) ;
		}
		PartialSolution solution = frontier.poll();
		return solution;
	}
	
	
	public static void getNieghbors(Board b,char type, int x, int y) {
		for (int i = 0 ; i<3; i++) {
			for (int j= 0 ; j<3; j++) {
				if ((i == x && (Math.abs(j - y) == 1)) || (j == y && (Math.abs(i - x) == 1))) {
					Board child = new Board(b.getString());
					child.alternate(i, j,x,y);
					if (child.checkIfIn(closed) && !(child.getString().equals(b.getString()))) {
						if(type == 'g') {
							myGraph.addBoard(child);
							myGraph.addEdge(b, child);
						}
						if (type == 't') {
							Node son = new Node(child);
							myTree.getNode(b).setChild(son);
							myTree.addChild(myTree.getNode(b),son.getData());
							myTree.addToTree(son);
						}
					} 
				}
			}
		} 
	}  
}
