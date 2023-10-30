package lab_day1.task1;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;	// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState) {
		envState = new EnvironmentState(locAState, locBState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		// TODO
		this.agent = agent;
		envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		// TODO
		if (action == Environment.SUCK_DIRT) {
			String agentLocation = envState.getAgentLocation();
			envState.setLocationState(agentLocation, LocationState.CLEAN);
		}
		else if (action == Environment.MOVE_LEFT) {
			envState.setAgentLocation(LOCATION_A);
		}
		else if (action == Environment.MOVE_RIGHT) {
			envState.setAgentLocation(LOCATION_B);
		}
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		// TODO
		String agentLocation = envState.getAgentLocation();
		// return Percept of Agent (it means Agent know where it located and state of this location).
        return new Percept(agentLocation, envState.getLocationState(agentLocation));
	}

	public void step() {
		// Env now
		envState.display();
		// Get location that agent located
		String agentLocation = this.envState.getAgentLocation();
		// Agent knows where it is by Percept and agent action based on execute() (in Action)
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN)) {
			isDone = true;		// if both squares are clean, then agent do not need to do any action
		}
		es.display();
	}

	public void step(int n) {
		for (int i = 1; i <= n; i++) {
			System.out.println("step: " + i);
			step();
			// i != n mean if i = n, it corrects the requirement so don't need to warning
			if (isDone && i != n) {
				System.out.println("Just use " +  i + " step to complete");
				break;
			}
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
