package n_queen.student;

public class HillClimbingQueenAlgo implements IAlgorithm {
    @Override
    public Node execute(Node initialState) {
        Node current = initialState;
        Node neighbor = null;
        while (true) {
            neighbor = current.getBestCandidate();
            if (neighbor.getH() < current.getH()) {
                current = neighbor;
            } else {
                return current;
            }
        }
    }
}
