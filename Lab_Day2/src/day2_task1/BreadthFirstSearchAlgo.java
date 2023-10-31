package day2_task1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo {
    private Queue<Node> frontier = new LinkedList<>();
    private List<Node> explorer = new ArrayList<>();

    @Override
    public Node execute(Node root, String goal) {
        Node goalNode = null;
        frontier.add(root);
        explorer.add(root);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.getLabel().equals("G")) {
                goalNode = current;
                System.out.println(NodeUtils.printPath(current));
                frontier.clear();
                break;
            }
            explorer.add(current);
            List<Edge> children = current.getChildren();
            for (Edge child : children) {
                if (!frontier.contains(child.getEnd()) && !explorer.contains(child.getEnd())) {
                    frontier.add(child.getEnd());
                    child.getEnd().setParent(current);
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
