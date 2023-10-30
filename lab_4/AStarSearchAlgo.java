package lab4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class AStarSearchAlgo implements IInformedSearchAlgo {
	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByFn());
		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal))
				return current;

			explored.add(current);
			List<Edge> children = current.getChildren();
			for (int i = 0; i < children.size(); i++) {
				Edge tmp = children.get(i);
				Node child = tmp.getEnd();
				double newPathCost = current.getG() + tmp.getWeight();
				if (!frontier.contains(child) && !explored.contains(child)) {
					child.setParent(current);
					child.setG(newPathCost);
					frontier.add(child);

				} else if (frontier.contains(child) && (newPathCost < child.getG())) {
					child.setG(newPathCost);
					child.setParent(current);
				}
			}
			System.out.println(frontier.toString());
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByFn());
		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);
		boolean isStart = false;
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(start)) {
				isStart = true;
				frontier.clear();
				explored.clear();
			}
			if (current.getLabel().equals(goal) && isStart)
				return current;

			explored.add(current);
			List<Edge> children = current.getChildren();
			for (int i = 0; i < children.size(); i++) {
				Edge tmp = children.get(i);
				Node child = tmp.getEnd();
				double newPathCost = current.getG() + tmp.getWeight();
				if (!frontier.contains(child) && !explored.contains(child)) {
					frontier.add(child);
					if (isStart) {
						child.setParent(current);
						child.setG(newPathCost);
					}

				} else if (frontier.contains(child) && (newPathCost < child.getG())) {
					child.setG(newPathCost);
					child.setParent(current);
				}
			}
		}
		return null;
	}

	@Override
	public boolean isAdmissible(Node tree, String goal) {
		List<Node> explored = new ArrayList<Node>();
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			if (!current.getLabel().equals(goal)) {
				explored.add(current);
				Node g = execute(current, goal);
				// nếu như g k null và H tại g > pathcost của g thì k admissible
				if (g != null && current.getH() > g.getG()) {
					System.out.println(current.getLabel() + ", H: " + current.getH() + ", G: " + g.getG());
					return false;
				}

				// tiếp tục mở rộng các node con khác
				List<Edge> children = current.getChildren();
				for (int i = 0; i < children.size(); i++) {
					Node child = children.get(i).getEnd();
					if (!frontier.contains(child) && !explored.contains(child)) {
						frontier.add(child);
						child.setG(0);
					}
				}

			}

		}
		return true;
	}

}
