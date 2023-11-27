package alg;
import java.util.*;

public class BFS implements IPuzzleAlgo {
	private int maxB;
    private Set<Node> explored;

    @Override
    public Node execute(Puzzle model) {
        Node initialState = model.getInitialState();
        Queue<Node> queue = new LinkedList<>();
        explored = new HashSet<>();

        queue.add(initialState);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.equals(model.getGoalState())) {
                return currentNode; // Goal state found
            }

            explored.add(currentNode);

            List<Node> successors = model.getSuccessors(currentNode);
            for (Node successor : successors) {
                if (!explored.contains(successor) && !queue.contains(successor)) {
                    queue.add(successor);
                }
            }
        }

        return null; // No solution found
    }
    public int getMaxB() {
        return maxB;
    }
}

