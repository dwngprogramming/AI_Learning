package alg;
import java.util.*;

public class GreedyBestFirstSearch implements IPuzzleAlgo {

    private Puzzle puzzle;
    private PriorityQueue<Node> frontier;

    public GreedyBestFirstSearch() {
        // You can initialize any required variables here
    }

    @Override
    public Node execute(Puzzle model) {
        this.puzzle = model;
        this.frontier = new PriorityQueue<>(PuzzleUtils.HeuristicComparatorByH); // You can use HeuristicComparatorByG or HeuristicComparatorByF as well

        Node initialState = puzzle.getInitialState();
        frontier.add(initialState);

        Set<Node> explored = new HashSet<>();

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();

            if (currentNode.getH()==0) {
                // Goal state reached
                return currentNode;
            }

            explored.add(currentNode);

            List<Node> successors = puzzle.getSuccessors(currentNode);
            for (Node successor : successors) {
                if (!explored.contains(successor) && !frontier.contains(successor)) {
                	successor.setG(currentNode.getG()+1);
                    frontier.add(successor);
                    
                }
            }
        }

        // If the frontier is empty and the goal state is not reached, return null
        return null;
    }
}

