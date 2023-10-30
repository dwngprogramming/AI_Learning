package lab_day1.task1;

public class AgentProgram {

	// execute return the action of Agent
	public Action execute(Percept p) {// location, status
		//TODO
		if (p.getLocationState() == Environment.LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		}
		else if (p.getAgentLocation().equals(Environment.LOCATION_A)) {
			return Environment.MOVE_RIGHT;
		}
		else if (p.getAgentLocation().equals(Environment.LOCATION_B)) {
			return Environment.MOVE_LEFT;
		}
		else {
			return NoOpAction.NO_OP;
		}
	}
}