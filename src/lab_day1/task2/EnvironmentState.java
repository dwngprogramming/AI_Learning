package lab_day1.task2;

import lab_day1.task2.Environment.LocationState;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentState {
	private Map<String, LocationState> state = new HashMap<>();
	private String agentLocation = null;//

	public EnvironmentState(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
		this.state.put(Environment.LOCATION_A, locAState);
		this.state.put(Environment.LOCATION_B, locBState);
		this.state.put(Environment.LOCATION_C, locCState);
		this.state.put(Environment.LOCATION_D, locDState);
	}

	public void setAgentLocation(String location) {
		this.agentLocation = location;
	}

	public String getAgentLocation() {
		return this.agentLocation;
	}

	public LocationState getLocationState(String location) {
		return this.state.get(location);
	}

	public void setLocationState(String location, LocationState locationState) {
		this.state.put(location, locationState);
	}

	public void display() {
		System.out.println("Environment state: \n\t" + this.state);
	}

	public void displayAfterAction() {
		System.out.println("Environment state after action: \n\t" + this.state);
	}
}