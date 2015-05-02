import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		// Receive the starting configuration from the user
		String initialConfiguration = InputHandler.takeInput();
		// Find the shortest path to solve the puzzle
		Stack<ConfigurationNode> shortestPath = PuzzleSolver.findShortestSolution(initialConfiguration);
		// Print the moves taken to solve the puzzle
		printSolution(shortestPath);
	}
	
	// Given a stack of configurations in the best solution, prints the moves taken to get to each one.
	// This will print all moves that need to be taken to solve the puzzle in the shortest amount of moves possible.
	private static void printSolution(Stack<ConfigurationNode> solutionPath) {
		int moveCounter = 0;
		while (!solutionPath.isEmpty()) {
			moveCounter++;
			ConfigurationNode nextConfiguration = solutionPath.pop();
			String move = nextConfiguration.getMoveTaken();
			System.out.println("Move #" + moveCounter + ": " + move);
		}
		
	}

}
