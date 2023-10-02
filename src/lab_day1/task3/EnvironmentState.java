package lab_day1.task3;

import lab_day1.task3.Environment.LocationState;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentState {
	private Map<Location, LocationState> state = new HashMap<>();
	private Location agentLocation = null;//

	public EnvironmentState() {
	}

	public void setAgentLocation(Location location) {
		this.agentLocation = location;
	}

	public Location getAgentLocation() {
		return this.agentLocation;
	}

	public LocationState getLocationState(Location location) {
		return this.state.get(location);
	}

	public Map<Location, LocationState> getMap() {
		return state;
	}

	public void setLocationState(Location location, LocationState locationState) {
		this.state.put(location, locationState);
	}

	public void display() {
		System.out.println("Environment state: \n\t" + this.state);
	}

	public void displayAfterAction() {
		System.out.println("Environment state after action: \n\t" + this.state);
	}
}