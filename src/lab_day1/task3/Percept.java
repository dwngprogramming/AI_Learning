package lab_day1.task3;

// Percept including location and state of Environment
public class Percept {
	private Location agentLocation;
	private Environment.LocationState state;

	public Percept(Location agentLocation, Environment.LocationState state) {
		this.agentLocation = agentLocation;
		this.state = state;
	}

	public Environment.LocationState getLocationState() {
		return this.state;
	}

	public Location getAgentLocation() {
		return this.agentLocation;
	}
}