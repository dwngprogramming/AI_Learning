package lab4;

import java.util.Comparator;

public class NodeComparatorByFn implements Comparator<Node> {
	@Override
	public int compare(Node o1, Node o2) {
		Double fn1 = o1.getH() + o1.getG();
		Double fn2 = o2.getH() + o2.getG();
		int result = fn1.compareTo(fn2);
		if (result == 0)
			return o1.getLabel().compareTo(o2.getLabel());
		else
			return result;
	}

}
