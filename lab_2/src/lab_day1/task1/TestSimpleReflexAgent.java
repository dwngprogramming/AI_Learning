package lab_day1.task1;

import lab_day1.task1.Agent;
import lab_day1.task1.AgentProgram;
import lab_day1.task1.Environment;

public class TestSimpleReflexAgent {
	public static void main(String[] args) {
		Environment env = new Environment(Environment.LocationState.DIRTY, Environment.LocationState.DIRTY);
		Agent agent = new Agent(new AgentProgram());
		env.addAgent(agent, Environment.LOCATION_A);

		env.step(5);
	}
}
