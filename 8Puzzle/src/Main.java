//A* search algorithm to solve a puzzle the 8-puzzle, by finding the optimal path from an initial board state to a goal state. 
//The AStar class manages the search process, using a tree or graph and a priority queue to explore potential paths based on a selected 
//heuristic — Manhattan distance, misplaced tiles, or uniform cost search. The PartialSolution class calculates and stores the cost of
//each path, combining the path cost and heuristic estimate to prioritize exploration. The search continues until the goal state is reached,
//at which point the optimal solution path is returned.

public class Main {

	public static void main(String[] args) {
		
		Board board = new Board(args[0]);
		char type = args[1].charAt(0);
		String heuristics = args[2];
		System.out.println("heuristics is : " + heuristics + ", type is: " + type );
		System.out.println("puzzle:");
		board.print();
		

		
		PartialSolution ps = AStar.search(board, type, heuristics);
		System.out.println("solution:");
		ps.print();
	}

}
