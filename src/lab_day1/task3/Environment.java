package lab_day1.task3;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Environment {
    public static final Action MOVE_LEFT = new DynamicAction("LEFT");
    public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
    public static final Action MOVE_UP = new DynamicAction("UP");
    public static final Action MOVE_DOWN = new DynamicAction("DOWN");
    public static final Action SUCK_DIRT = new DynamicAction("SUCK");
    public static ArrayList<Location> LOC_MAP;

    public enum LocationState {
        CLEAN, DIRTY
    }

    private EnvironmentState envState = new EnvironmentState();
    private boolean isDone = false;    // all squares are CLEAN
    private Agent agent = null;
    private int totalPoint = 0;
    private String checkpoint = "";

    // Set Location and LocationState at the same time in constructor
    public Environment(int rows, int cols, double rate) {
        // Init ArrayList<Location> that contains row and col of location
        LOC_MAP = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Location loc = new Location(i, j);
                LOC_MAP.add(loc);
            }
        }

        int locAfterRate = 0;
        double locRateCalculate = LOC_MAP.size() * rate;
        // At least one location is DIRTY
        if (locRateCalculate < 1) {
            locAfterRate = 1;
        } else {
            locAfterRate = (int) Math.round(locRateCalculate);
        }
        // ArrayList save the value that used to appear
        ArrayList<Integer> appeared = new ArrayList<>();
        appeared.add(-1);
        for (int i = 0; i < locAfterRate; i++) {
            // Random DIRTY location after calculate rate
            Random random = new Random();
            int index = random.nextInt(LOC_MAP.size() - 1);
            for (Integer in : appeared) {
                if (in == index) {
                    index = random.nextInt(LOC_MAP.size() - 1);
                }
                else {
                    appeared.add(index);
                    break;
                }
            }
            envState.setLocationState(LOC_MAP.get(index), LocationState.DIRTY);
        }

        // Set CLEAN for remain location
        for (Location loc : LOC_MAP) {
            if (envState != null) {
                // If map doesn't contain location (it means that containing location have already been set to DIRTY before),
                // set this location to CLEAN.
                if (!envState.getMap().containsKey(loc)) {
                    envState.setLocationState(loc, LocationState.CLEAN);
                }
                // If contains, then continue
                else continue;
            }
        }
    }

    // add an agent into the environment
    public void addAgent(Agent agent, int row, int col) {
        // TODO
        this.agent = agent;
        for (Map.Entry<Location, LocationState> entry : envState.getMap().entrySet()) {
            if (entry.getKey().getRowLocation() == row && entry.getKey().getColLocation() == col) {
                envState.setAgentLocation(entry.getKey());
            }
        }
    }

    public EnvironmentState getCurrentState() {
        return this.envState;
    }

    // Update environment state when agent do an action
    // With grid environment
    public EnvironmentState executeAction(Action action) {
        // TODO
        Location agentLocation = envState.getAgentLocation();
        int agentRowLoc = agentLocation.getRowLocation();
        int agentColLoc = agentLocation.getColLocation();
        // Get max row and max col
        int maxRow = lastLocation().getRowLocation();
        int maxCol = lastLocation().getColLocation();

        if (action == Environment.SUCK_DIRT) {
            suckPoint();
            envState.setLocationState(agentLocation, LocationState.CLEAN);
            setCheckpoint("SUCK");
        }
        // Set moving case when LocationState is CLEAN
        else if (envState.getLocationState(agentLocation).equals(LocationState.CLEAN)) {
            // Check row = 0 && col = 0
            if (envState.getAgentLocation().getRowLocation() == 0 && envState.getAgentLocation().getColLocation() == 0) {
                if (action == Environment.MOVE_UP || action == Environment.MOVE_LEFT) {
                    cantMove();
                    setCheckpoint("FAIL");
                }
            }
            // Check row = max && col = max
            else if (envState.getAgentLocation().getRowLocation() == maxRow && envState.getAgentLocation().getColLocation() == maxCol) {
                if (action == Environment.MOVE_DOWN || action == Environment.MOVE_RIGHT) {
                    cantMove();
                    setCheckpoint("FAIL");
                }
            }
            // Check row = 0 && col = max
            else if (envState.getAgentLocation().getRowLocation() == 0 && envState.getAgentLocation().getColLocation() == maxCol) {
                if (action == Environment.MOVE_UP || action == Environment.MOVE_RIGHT) {
                    cantMove();
                    setCheckpoint("FAIL");
                }
            }
            // Check row = max && col = 0
            else if (envState.getAgentLocation().getRowLocation() == maxRow && envState.getAgentLocation().getColLocation() == 0) {
                if (action == Environment.MOVE_DOWN || action == Environment.MOVE_LEFT) {
                    cantMove();
                    setCheckpoint("FAIL");
                }
            }
            // Check if row = 0;
            else if (envState.getAgentLocation().getRowLocation() == 0) {
                // Row = 0 means agent can't move up
                if (action == Environment.MOVE_UP) {
                    cantMove();
                    setCheckpoint("FAIL");
                }
                // We've already checked (0,0), so agent can move left
                if (action == Environment.MOVE_LEFT) {
                    envState.setAgentLocation(moveLeft(agentRowLoc, agentColLoc));
                }
                // (Missing info)
                else if (action == Environment.MOVE_RIGHT) {
                    envState.setAgentLocation(moveRight(agentRowLoc, agentColLoc));
                }
                // (Missing info)
                else if (action == Environment.MOVE_DOWN) {
                    envState.setAgentLocation(moveDown(agentRowLoc, agentColLoc));
                }
            }   // Done row = 0

            // Check if col = 0
            else if (envState.getAgentLocation().getColLocation() == 0) {
                // Col = 0 means agent can't move left
                if (action == Environment.MOVE_LEFT) {
                    cantMove();
                    setCheckpoint("FAIL");
                }
                // We've already checked (0,0), so agent can move up
                else if (action == Environment.MOVE_UP) {
                    envState.setAgentLocation(moveUp(agentRowLoc, agentColLoc));
                }
                // We've already check (maxRow,0) so agent can move up
                else if (action == Environment.MOVE_DOWN) {
                    envState.setAgentLocation(moveDown(agentRowLoc, agentColLoc));
                }
                else if (action == Environment.MOVE_RIGHT) {
                    envState.setAgentLocation(moveRight(agentRowLoc, agentColLoc));
                }
            }
            // Check if col = max
            else if (envState.getAgentLocation().getColLocation() == maxCol) {
                // Col = max means agent can't move right
                if (action == Environment.MOVE_RIGHT) {
                    cantMove();
                    setCheckpoint("FAIL");
                }
                // We've already checked (0, maxCol), so agent can move up
                else if (action == Environment.MOVE_UP) {
                    envState.setAgentLocation(moveUp(agentRowLoc, agentColLoc));
                }
                // We've already checked (maxRow, maxCol), so agent can move down
                else if (action == Environment.MOVE_DOWN) {
                    envState.setAgentLocation(moveDown(agentRowLoc, agentColLoc));
                }
                else if (action == Environment.MOVE_LEFT) {
                    envState.setAgentLocation(moveLeft(agentRowLoc, agentColLoc));
                }
            }
            // Check if row = max
            else if (envState.getAgentLocation().getRowLocation()== maxRow) {
                // Row = max means agent can't move down
                if (action == Environment.MOVE_DOWN) {
                    cantMove();
                    setCheckpoint("FAIL");
                }
                else if (action == Environment.MOVE_UP) {
                    envState.setAgentLocation(moveUp(agentRowLoc, agentColLoc));
                }
                // We've already checked (maxRow, maxCol), so agent can move right.
                else if (action == Environment.MOVE_RIGHT) {
                    envState.setAgentLocation(moveDown(agentRowLoc, agentColLoc));
                }
                // We've already checked (maxRow, 0), so agent can move left.
                else if (action == Environment.MOVE_LEFT) {
                    envState.setAgentLocation(moveLeft(agentRowLoc, agentColLoc));
                }
            }
            // Not in special case
            else {
                if (action == Environment.MOVE_UP) {
                    envState.setAgentLocation(moveUp(agentRowLoc, agentColLoc));
                }
                else if (action == Environment.MOVE_DOWN) {
                    envState.setAgentLocation(moveDown(agentRowLoc, agentColLoc));
                }
                else if (action == Environment.MOVE_RIGHT) {
                    envState.setAgentLocation(moveDown(agentRowLoc, agentColLoc));
                }
                else if (action == Environment.MOVE_LEFT) {
                    envState.setAgentLocation(moveLeft(agentRowLoc, agentColLoc));
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
        Percept percept = null;
        Location agentLocation = envState.getAgentLocation();
        // return Percept of Agent (it means Agent know where it located and state of this location).
        percept = new Percept(agentLocation, envState.getLocationState(agentLocation));
        return percept;
    }

    public void step() {
        // Env now
        envState.display();
        // Get location that agent located
        Location agentLocation = this.envState.getAgentLocation();
        // Agent knows where it is by Percept and agent action based on execute() (in Action)
        Action anAction = agent.execute(getPerceptSeenBy());
        EnvironmentState es = executeAction(anAction);

        System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);
        getCheckpoint();

//        if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
//                && (es.getLocationState(LOCATION_B) == LocationState.CLEAN)
//                && (es.getLocationState(LOCATION_C) == LocationState.CLEAN)
//                && (es.getLocationState(LOCATION_D) == LocationState.CLEAN)) {
//            isDone = true;        // if both squares are clean, then agent do not need to do any action
//        }
        // Check at least one square is dirty
        for (Map.Entry<Location, LocationState> entry : es.getMap().entrySet()) {
            if (entry.getValue().equals(LocationState.DIRTY)) {
                isDone = false;
                break;
            }
            else {
                isDone = true;
            }
        }
        System.out.println("Point after action: " + totalPoint);
        es.displayAfterAction();
    }

    public void step(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println("step: " + i);
            step();
            // i != n mean if i = n, it corrects the requirement so don't need to warning
            if (isDone && i != n) {
                System.out.println("Just use " + i + " step to complete");
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
        } else if (checkpoint.equals("FAIL")) {
            System.out.println("Can't move. Minus 100 points");
        } else if (checkpoint.equals("OTHER")) {
            System.out.println("Other fail detected. Minus 10 points");
        }
        // Reset checkpoint
        checkpoint = "";
    }

    public void setCheckpoint(String checkpoint) {
        this.checkpoint = checkpoint;
    }

    private Location getMovingLocation(int row, int col) {
        Location location = null;
        for (Location loc : LOC_MAP) {
            if (loc.getRowLocation() == row && loc.getColLocation() == col) {
                location = loc;
                break;
            }
        }
        return location;
    }

    private Location lastLocation() {
        Location last = null;
        // Get last element by get size() - 1 in ArrayList
        last = LOC_MAP.get(LOC_MAP.size() - 1);
        return last;
    }

    private Location moveLeft(int row, int col) {
        // Minus 1 in col
        return getMovingLocation(row, col - 1);
    }

    private Location moveRight(int row, int col) {
        // Plus 1 in col
        return getMovingLocation(row, col + 1);
    }

    private Location moveUp(int row, int col) {
        // Minus 1 in row
        return getMovingLocation(row - 1, col);
    }

    private Location moveDown(int row, int col) {
        // Plus 1 in row
        return getMovingLocation(row + 1, col );
    }
}
