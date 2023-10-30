package lab_day1.task2;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;	// all squares are CLEAN
	private Agent agent = null;
	private int totalPoint = 0;
    private String checkpoint = "";

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	// add an agent into the environment
	public void addAgent(Agent agent, String location) {
		// TODO
		this.agent = agent;
		envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update environment state when agent do an action
	// With A, B, C, D
	public EnvironmentState executeAction(Action action) {
		// TODO
		String agentLocation = envState.getAgentLocation();
		if (action == Environment.SUCK_DIRT) {
			suckPoint();
			envState.setLocationState(agentLocation, LocationState.CLEAN);
            setCheckpoint("SUCK");
		}
		else if (envState.getLocationState(agentLocation).equals(LocationState.CLEAN)) {
			if (envState.getAgentLocation().equals(LOCATION_A)) {
				if (action == Environment.MOVE_UP || action == Environment.MOVE_LEFT) {
					cantMove();
                    setCheckpoint("FAIL");
				} else if (action == Environment.MOVE_RIGHT) {
					envState.setAgentLocation(LOCATION_B);
				} else if (action == Environment.MOVE_DOWN) {
					envState.setAgentLocation(LOCATION_D);
				}
			}
			else if (envState.getAgentLocation().equals(LOCATION_B)) {
				if (action == Environment.MOVE_UP || action == Environment.MOVE_RIGHT) {
					cantMove();
                    setCheckpoint("FAIL");
				} else if (action == Environment.MOVE_LEFT) {
					envState.setAgentLocation(LOCATION_A);
				} else if (action == Environment.MOVE_DOWN) {
					envState.setAgentLocation(LOCATION_C);
				}
			}
			else if (envState.getAgentLocation().equals(LOCATION_C)) {
				if (action == Environment.MOVE_DOWN || action == Environment.MOVE_RIGHT) {
					cantMove();
                    setCheckpoint("FAIL");
				} else if (action == Environment.MOVE_LEFT) {
					envState.setAgentLocation(LOCATION_D);
				} else if (action == Environment.MOVE_UP) {
					envState.setAgentLocation(LOCATION_B);
				}
			}
			else if (envState.getAgentLocation().equals(LOCATION_D)) {
				if (action == Environment.MOVE_DOWN || action == Environment.MOVE_LEFT) {
					cantMove();
                    setCheckpoint("FAIL");
				} else if (action == Environment.MOVE_RIGHT) {
					envState.setAgentLocation(LOCATION_C);
				} else if (action == Environment.MOVE_UP) {
					envState.setAgentLocation(LOCATION_A);
				}
			}
		}
		return envState;
	}

	private void suckPoint() {
		totalPoint += 500;
	}

	private void cantMove() {
		totalPoint -= 100;
	}

	private void others() {
		totalPoint -= 10;
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
        getCheckpoint();

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_C) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_D) == LocationState.CLEAN)) {
			isDone = true;		// if both squares are clean, then agent do not need to do any action
		}
		System.out.println("Point after action: "  + totalPoint);
		es.displayAfterAction();
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
			System.out.println("------------------------------------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 1;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
            System.out.println("-------------------------");
		}
	}

    public void getCheckpoint() {
        if (checkpoint.equals("SUCK")) {
            System.out.println("Sucking complete. Plus 500 points");
        }
        else if (checkpoint.equals("FAIL")) {
            System.out.println("Can't move. Minus 100 points");
        }
        else if (checkpoint.equals("OTHER")) {
            System.out.println("Other fail detected. Minus 10 points");
        }
        // Reset checkpoint
        checkpoint = "";
    }

    public void setCheckpoint(String checkpoint) {
        this.checkpoint = checkpoint;
    }
}
