package alg;
import java.util.*;

public class AStarSearch implements IPuzzleAlgo {

    private Puzzle puzzle;
    private PriorityQueue<Node> frontier;

    public AStarSearch() {
        // You can initialize any required variables here
    }

    @Override
    public Node execute(Puzzle model) {
        this.puzzle = model;
        this.frontier = new PriorityQueue<>(Comparator.comparingInt(Node::getF));

        Node initialState = puzzle.getInitialState();
        frontier.add(initialState);

        Map<Node, Integer> gValues = new HashMap<>();
        gValues.put(initialState, 0);

        Set<Node> explored = new HashSet<>();

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();

            if (currentNode.equals(puzzle.getGoalState())) {
                // Goal state reached
                return currentNode;
            }

            explored.add(currentNode);

            List<Node> successors = puzzle.getSuccessors(currentNode);
            for (Node successor : successors) {
                int tentativeG = gValues.getOrDefault(currentNode, 0) + 1;

                if (!explored.contains(successor) && (tentativeG < gValues.getOrDefault(successor, Integer.MAX_VALUE))) {
                    gValues.put(successor, tentativeG);
                    successor.setG(tentativeG);
                    successor.setH(puzzle.computeH1(successor)); // Change to computeH2 for Case 2

                    if (!frontier.contains(successor)) {
                        frontier.add(successor);
                    }
                }
            }
        }

        // If the frontier is empty and the goal state is not reached, return null
        return null;
    }
}

