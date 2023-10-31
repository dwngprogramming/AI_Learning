package day2_task1;

import java.util.*;

public class DepthFirstSearchAlgo implements ISearchAlgo {
    private Stack<Node> frontier = new Stack<>();
    private List<Node> explorer = new ArrayList<>();

    @Override
    public Node execute(Node root, String goal) {
        Node goalNode = null;
        frontier.add(root);
        explorer.add(root);

        while (!frontier.isEmpty()) {
            Node current = frontier.pop();
            if (current.getLabel().equals("G")) {
                goalNode = current;                frontier.clear();
                System.out.println(NodeUtils.printPath(current));
                break;
            }
            explorer.add(current);
            List<Edge> children = current.getChildren();
            for (int i = children.size() - 1; i >= 0; i--) {
                if (!frontier.contains(children.get(i).getEnd()) && !explorer.contains(children.get(i).getEnd())) {
                    frontier.add(children.get(i).getEnd());

                    children.get(i)
                            .getEnd()
                            .setParent(current);
                }
            }
        }
        return goalNode;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        return null;
    }
}
