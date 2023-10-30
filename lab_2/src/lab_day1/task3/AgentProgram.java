package lab_day1.task3;

import java.util.Random;

public class AgentProgram {
	private final Action[] moveActions = new Action[] {Environment.MOVE_UP, Environment.MOVE_DOWN, Environment.MOVE_LEFT, Environment.MOVE_RIGHT};
	// execute return the action of Agent
	public Action execute(Percept p) {	// location, status
		// Create random with array to random index
		Random random = new Random();
		int index = random.nextInt(moveActions.length);
		if (p.getLocationState().equals(Environment.LocationState.DIRTY)) {
			return Environment.SUCK_DIRT;
		}
		else {
			return moveActions[index];
		}
	}
}