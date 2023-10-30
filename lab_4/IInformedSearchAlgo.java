package lab4;

public interface IInformedSearchAlgo {
	public Node execute(Node root, String goal);
	
	public boolean isAdmissible(Node root, String goal);
	
	public Node execute(Node root, String start, String goal);

}
