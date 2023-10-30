package lab4;

public class TestAStar {
	public static void main(String[] args) {
		Node s = new Node("S", 6);
		Node b = new Node("B", 4);
		Node a = new Node("A", 4);
		Node c = new Node("C", 4);
		Node d = new Node("D", 3.5);
		Node e = new Node("E", 1);
		Node f = new Node("F", 1);
		Node g = new Node("G", 0);

		s.addEdge(b, 3);
		s.addEdge(a, 2);
		a.addEdge(c, 3);
		b.addEdge(d, 3);
		b.addEdge(c, 1);
		c.addEdge(e, 3);
		c.addEdge(d, 1);
		d.addEdge(f, 2);
		f.addEdge(g, 1);
		e.addEdge(g, 2);

//		Node B = new Node("B", 25);
//		Node A = new Node("A", 22);
//		Node C = new Node("C", 20);
//		Node D = new Node("D", 10);
//		Node E = new Node("E", 6);
//		Node F = new Node("F", 8);
//		Node G1 = new Node("G1", 0);
//		Node G2 = new Node("G2", 0);
//		Node K = new Node("K", 26);
//		Node H = new Node("H", 16);
//		Node S = new Node("S", 30);
//
//		A.addEdge(B, 8);
//		A.addEdge(D, 15);
//		B.addEdge(A, 7);
//		B.addEdge(C, 11);
//		C.addEdge(E, 12);
//		D.addEdge(G2, 9);
//		E.addEdge(G1, 7);
//		F.addEdge(G1, 10);
//		H.addEdge(G1, 19);
//		H.addEdge(F, 7);
//		H.addEdge(S, 9);
//		K.addEdge(A,13);
//		K.addEdge(H,10);
//		S.addEdge(B,8);
//		S.addEdge(C,19);
//		S.addEdge(K,6);

		IInformedSearchAlgo aStar = new AStarSearchAlgo();
		Node res = aStar.execute(s, "G");
		System.out.println(NodeUtils.printPath(res));
//		System.out.println(aStar.isAdmissible(S, "G2"));
	}
}
