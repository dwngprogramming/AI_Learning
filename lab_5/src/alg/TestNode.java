package alg;

import java.util.Arrays;
import java.util.List;

//public class TestNode {
//
//	public static void main(String[] args) {
//		Puzzle p = new Puzzle();
//		p.readInput("txt/PuzzleMap.txt", "txt/PuzzleGoalState.txt");
//
//		Node initialState = p.getInitialState();
//		Node tmp = new Node(initialState);
//		System.out.println(initialState.equals(tmp));
////		Node goalState = p.getGoalState();
////		System.out.println(p.getInitialState());
//		System.out.println("H: "+initialState.getH());
////		System.out.println(Arrays.toString(initialState.getWhiteTilePosition()));
////		System.out.println(p.getGoalState());
//		Node re = p.moveTile(initialState, 'r');
////
//		System.out.println(re);
//		System.out.println(re.getH());
//		System.out.println(initialState);
////		System.out.println(Arrays.toString(re.getWhiteTilePosition()));
////		System.out.println(p.computeH(init, goal));
//
//		// System.out.println(p.getInitialState());
//		// System.out.println(p.getGoalState());
//		//
//		// List<Node> children = p.getSuccessors(initialState);
//		// System.out.println("Size: "+children.size());
//		// for (Node child : children) {
//		// System.out.println(child.getH()+" "+p.computeH(child) );
//		// }
//	}
//}
public class TestNode {

    public static void main(String[] args) {
        Puzzle p = new Puzzle();
        p.readInput("txt/PuzzleMap.txt", "txt/PuzzleGoalState.txt");

        System.out.println("Task 1 : ");
        Node initialState = p.getInitialState();
        Node tmp = new Node(initialState);
        System.out.println("Initial State equals tmp: " + initialState.equals(tmp));
        System.out.println("H: " + initialState.getH());

        // Uncomment the next line to print the goalState and check if it matches your expectation
        // System.out.println("Goal State: " + p.getGoalState());

        Node result = p.moveWhiteTile(initialState, 'r');
        System.out.println("Result after moving right: "+"\n" + result);
        System.out.println("H of result: " + result.getH());
        System.out.println("Initial State: "+"\n"  + initialState);
        
        System.out.println("Task 2 : Greedy Best First Search ");

        // Additional test: Check if the result is a valid successor of the initial state
        List<Node> successors = p.getSuccessors(initialState);
        System.out.println("\n"+"Is result a valid successor? " + successors.contains(result));

        // Additional test: Check if the computed heuristics match your expectations
        System.out.println("H1 of result: " + p.computeH1(result));
        System.out.println("H2 of result: " + p.computeH2(result));
        
        
        System.out.println("\n"+"Task 3 : A* Search ");

        // Test A* Search with h1
        AStarSearch solverH1 = new AStarSearch();
        p.computeH1(p.getInitialState()); // Compute H1 for the initial state
        Node solutionH1 = solverH1.execute(p);
        System.out.println("\n"+"A* Search with h1:");
        printSolution(solutionH1);

        // Test A* Search with h2
        AStarSearch solverH2 = new AStarSearch();
        p.computeH2(p.getInitialState()); // Compute H2 for the initial state
        Node solutionH2 = solverH2.execute(p);
        System.out.println("A* Search with h2:");
        printSolution(solutionH2);
        
        System.out.println("\n"+"Task 4 : Advanced ");
//        System.out.println("Dfs search: ");
//        
//        System.out.println("Bfs search: ");
//
//        System.out.println("HillClimbing : ");

        // Testing DFS Algorithm
        System.out.println("Testing DFS Algorithm");
        long startTimeDFS = System.currentTimeMillis();
        DFS dfsSolver = new DFS();
        Node solutionDFS = dfsSolver.execute(p);
        int maxDepthDFS = dfsSolver.getMaxDepth();
        long endTimeDFS = System.currentTimeMillis();
        printResultss("DFS", solutionDFS, endTimeDFS - startTimeDFS, maxDepthDFS);

        // Testing BFS Algorithm
        System.out.println("Testing BFS Algorithm");
        long startTimeBFS = System.currentTimeMillis();
        BFS bfsSolver = new BFS();
        Node solutionBFS = bfsSolver.execute(p);
        int maxDepthBFS = bfsSolver.getMaxB();
        long endTimeBFS = System.currentTimeMillis();
        printResultss("BFS", solutionBFS, endTimeBFS - startTimeBFS, maxDepthBFS);

        // Testing Hill Climbing Algorithm
        System.out.println("\nTesting Hill Climbing Algorithm");
        long startTimeHillClimbing = System.currentTimeMillis();
        HillClimbing hillClimbingSolver = new HillClimbing();
        Node solutionHillClimbing = hillClimbingSolver.execute(p);
        long endTimeHillClimbing = System.currentTimeMillis();
        printResults("Hill Climbing", solutionHillClimbing, endTimeHillClimbing - startTimeHillClimbing);
    }
    private static void printResults(String algorithm, Node solution, long runningTime) {
        System.out.println("Algorithm: " + algorithm);
        if (solution != null) {
            System.out.println("Solution found in " + runningTime + " milliseconds:");
            System.out.println("Path:");
            System.out.println(solution); // In ra đường đi của giải pháp
            System.out.println("Number of steps: " + solution.getG());
        } else {
            System.out.println("No solution found.");
        }
        System.out.println("-------------------------");
    }


    private static void printResultss(String algorithm, Node solution, long runningTime, int maxDepth) {
        System.out.println("Algorithm: " + algorithm);
        if (solution != null) {
            System.out.println("Solution found in " + runningTime + " milliseconds:");
            System.out.println("Path:");
            System.out.println(solution); // In ra đường đi của giải pháp
            System.out.println("Number of steps: " + solution.getG());
        } else {
            System.out.println("No solution found.");
        }
        System.out.println("Maximum Depth Reached: " + maxDepth);
        System.out.println("-------------------------");
    }
    
    private static void printSolution(Node solution) {
        if (solution != null) {
            System.out.println("Solution found:");
            System.out.println("Path: " + solution);
            System.out.println("Number of steps: " + solution.getG());
        } else {
            System.out.println("No solution found.");
        }
        System.out.println("-------------------------");
    }
    }
   
  
    



