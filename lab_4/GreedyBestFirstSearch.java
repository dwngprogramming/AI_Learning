package lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import lab4.Edge;
import lab4.Node;
import lab4.NodeComparatorByHn;

public class GreedyBestFirstSearch implements IInformedSearchAlgo {
	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal))
				return current;

			explored.add(current);
			List<Edge> children = current.getChildren();
			for (Edge e : children) {
				Node child = e.getEnd();
				double newPathCode = current.getG() + e.getWeight();
				if (!frontier.contains(child) && !explored.contains(child)) {
					child.setParent(current);
					child.setG(newPathCode);
					frontier.add(child);
					
				}
			}
			System.out.println(frontier.toString());
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
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

				if (!frontier.contains(child) && !explored.contains(child)) {
					frontier.add(child);
					if (isStart)
						child.setParent(current);

				}
			}
		}
		return null;
	}
	@Override
	public boolean isAdmissible(Node root, String goal) {
		// TODO Auto-generated method stub
		return false;
	}
}
