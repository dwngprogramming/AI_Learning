package lab_day1.task3;

public class TestSimpleReflexAgent {
	public static void main(String[] args) {
		Environment env = new Environment(3, 4, 0.2);
		Agent agent = new Agent(new AgentProgram());
		// Do not new Location because it makes a difference reference
		env.addAgent(agent, new Location(1, 1));

		env.stepUntilDone();
	}
}
