import java.util.Hashtable;
import java.util.Stack;


public class PuzzleSolver {
	
	// Table to store all configurations that have been found as we enumerate all possible configurations of the puzzle
	private static Hashtable<Integer,Integer> foundConfigurations = new Hashtable<Integer,Integer>();

	// Given a starting configuration, returns a stack containing the shortest possible list of configurations
	// to move through in order to solve the puzzle
	public static Stack<ConfigurationNode> findShortestSolution(String initialConfiguration) {
		
	}

}
