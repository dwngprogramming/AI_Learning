package alg;

import java.util.*;

public class HillClimbing implements IPuzzleAlgo {
	

    @Override
    public Node execute(Puzzle model) {
        Node currentState = model.getInitialState();

        while (true) {
            List<Node> successors = model.getSuccessors(currentState);
            Node bestSuccessor = null;

            for (Node successor : successors) {
                if (bestSuccessor == null || model.computeH1(successor) < model.computeH1(bestSuccessor)) {
                    bestSuccessor = successor;
                }
            }

            if (model.computeH1(bestSuccessor) >= model.computeH1(currentState)) {
                return currentState; // Local maximum reached
            }

            currentState = bestSuccessor;
        }
    }
   
}
