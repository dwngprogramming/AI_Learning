package lab4;

public class TestGBFS {
	public static void main(String[] args) {
		Node S = new Node("S", 6);
		Node A = new Node("A", 4);
		Node B = new Node("B", 4);
		Node C = new Node("C", 4);
		Node D = new Node("D", 3.5);
		Node E = new Node("E", 1);
		Node F = new Node("F", 1);
		Node G = new Node("G", 0);

		S.addEdge(A);
		S.addEdge(B);
		A.addEdge(C);
		B.addEdge(C);
		B.addEdge(D);
		C.addEdge(E);
		C.addEdge(D);
		E.addEdge(G);
		D.addEdge(F);
		F.addEdge(G);

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

		IInformedSearchAlgo gbfs = new GreedyBestFirstSearch();
		Node goal = gbfs.execute(S, "G");
		System.out.println(NodeUtils.printPath(goal));
//		Node goalNStart = gbfs.execute(S, "A", "G1");
//		System.out.println(NodeUtils.printPath(goalNStart));
	}
}
