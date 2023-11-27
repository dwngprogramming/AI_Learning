package alg;

import java.util.*;

public class DFS implements IPuzzleAlgo {
	 private int maxDepth;
    private Set<Node> explored;

    @Override
    public Node execute(Puzzle model) {
        Node initialState = model.getInitialState();
        Stack<Node> stack = new Stack<>();
        explored = new HashSet<>();

        stack.push(initialState);

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            if (currentNode.equals(model.getGoalState())) {
                return currentNode; // Goal state found
            }

            explored.add(currentNode);

            List<Node> successors = model.getSuccessors(currentNode);
            for (Node successor : successors) {
                if (!explored.contains(successor) && !stack.contains(successor)) {
                    stack.push(successor);
                }
            }
        }

        return null; // No solution found
    }
    public int getMaxDepth() {
        return maxDepth;
    }
}


