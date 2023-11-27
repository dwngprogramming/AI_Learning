package alg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Puzzle {
	public static final int MAX_ROW = 3;// 3x3: Dimension of the puzzle map
	public static final int MAX_COL = 3;
	public static final char[] operators = { 'l', 'r', 'u', 'd' };

	private Node initialState;
	private Node goalState;

	public Puzzle() {
		this.initialState = new Node(MAX_ROW, MAX_COL);
		this.goalState = new Node(MAX_ROW, MAX_COL);
	}

	// Load initial state and goal state from files
	public void readInput(String INITIAL_STATE_MAP_PATH, String GOAL_STATE_MAP_PATH) {
		try {
			// 1 - Import map
			BufferedReader bufferedReader = new BufferedReader(new FileReader(INITIAL_STATE_MAP_PATH));

			String line = null;
			int row = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tile = line.split(" ");
				for (int col = 0; col < tile.length; col++) {
					initialState.updateTile(row, col, Integer.parseInt(tile[col]));
				}
				row++;
			}

			bufferedReader.close();

			// 2 - Import goal state
			bufferedReader = new BufferedReader(new FileReader(GOAL_STATE_MAP_PATH));

			line = null;
			row = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tile = line.split(" ");
				for (int col = 0; col < tile.length; col++) {
					goalState.updateTile(row, col, Integer.parseInt(tile[col]));
				}
				row++;
			}

			bufferedReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Node moveWhiteTile(Node currentState, char operator) {
	    Node result = new Node(currentState);
	    int[] whiteTile = currentState.getLocation(0); // get white tile

	    int row = whiteTile[0];
	    int col = whiteTile[1];

	    switch (operator) {
	        case 'u':
	            if (row > 0) {
	                swapTiles(result, row, col, row - 1, col);
	                return result;
	            }
	            break;
	        case 'd':
	            if (row < MAX_ROW - 1) {
	                swapTiles(result, row, col, row + 1, col);
	                return result;
	            }
	            break;
	        case 'l':
	            if (col > 0) {
	                swapTiles(result, row, col, row, col - 1);
	                return result;
	            }
	            break;
	        case 'r':
	            if (col < MAX_COL - 1) {
	                swapTiles(result, row, col, row, col + 1);
	                return result;
	            }
	            break;
	    }

	    return null;
	}

	public int computeH1(Node currentState) {
	    int misplacedTiles = 0;

	    for (int row = 0; row < MAX_ROW; row++) {
	        for (int col = 0; col < MAX_COL; col++) {
	            if (currentState.getTile(row, col) != goalState.getTile(row, col)) {
	                misplacedTiles++;
	            }
	        }
	    }

	    return misplacedTiles;
	}

	public int computeH2(Node currentState) {
	    int totalManhattanDistance = 0;

	    for (int tile = 1; tile < MAX_ROW*MAX_COL; tile++) {
	        
	    	int[] loc1 = currentState.getLocation(tile);
			int[] loc2 = getGoalState().getLocation(tile);

			totalManhattanDistance += PuzzleUtils.manhattanDistance(loc1, loc2);

	    }

	    return totalManhattanDistance;
	}
//
//	// The total number of misplaced tiles
//		public int computeH1(Node currentState) {
//			int output = 0;
//			for (int i = 0; i < currentState.getRow(); i++) {
//				for (int j = 0; j < currentState.getColumn(); j++) {
//					int c = currentState.getTile(i, j);
//					int g = getGoalState().getTile(i, j);
//					if (c != g) {
//						output++;
//					}
//				}
//			}
//			return output;
//		}
//
//		// Using manhattanDistance above to compute H
//		public int computeH2(Node currentState) {
//			int result = 0;
//			for (int i = 0; i < currentState.getRow(); i++) {
//
//				int[] loc1 = currentState.getLocation(i);
//				int[] loc2 = getGoalState().getLocation(i);
//
//				result += PuzzleUtils.manhattanDistance(loc1, loc2);
//
//			}
//			return result;
//		}
//
//		public Node moveWhiteTile(Node currentState, char operator) {
//			Node result = new Node(currentState);
//			int[] whiteTile = currentState.getLocation(0);// get white tile
//			if (operator == 'u') {// Case-1: Move tile UP
//				// New postion of tile if move UP
//				int row = whiteTile[0] - 1;
//				int col = whiteTile[1];
//				if (row >= 0) {// Tile stands inside the map
//					int tmp = currentState.getTile(row, col);
//					result.updateTile(row, col, 0);
//					result.updateTile(whiteTile[0], whiteTile[1], tmp);
//					result.setH(computeH2(result));
//					return result;
//				}
//			}
//
//			else if (operator == 'd') {// Case-2: Move tile DOWN
//				int row = whiteTile[0]-1;
//				int col = whiteTile[1];
//				if (row >= 0) {// Tile stands inside the map
//					int tmp = currentState.getTile(row, col);
//					result.updateTile(row, col, 0);
//					result.updateTile(whiteTile[0], whiteTile[1], tmp);
//					result.setH(computeH2(result));
//					return result;
//				}
//			}
//
//			else if (operator == 'l') {// Case-3: Move tile LEFT
//				int row = whiteTile[0];
//				int col = whiteTile[1]-1;
//				if (row >= 0) {// Tile stands inside the map
//					int tmp = currentState.getTile(row, col);
//					result.updateTile(row, col, 0);
//					result.updateTile(whiteTile[0], whiteTile[1], tmp);
//					result.setH(computeH2(result));
//					return result;
//				}
//
//			}
//
//			else if (operator == 'r') {// Case-4: Move tile RIGHT
//				int row = whiteTile[0];
//				int col = whiteTile[1]+1;
//				if (row >= 0) {// Tile stands inside the map
//					int tmp = currentState.getTile(row, col);
//					result.updateTile(row, col, 0);
//					result.updateTile(whiteTile[0], whiteTile[1], tmp);
//					result.setH(computeH2(result));
//					return result;
//				}
//
//			}
//			return null;
//		}
	private void swapTiles(Node node, int row1, int col1, int row2, int col2) {
	    int tmp = node.getTile(row1, col1);
	    node.updateTile(row1, col1, node.getTile(row2, col2));
	    node.updateTile(row2, col2, tmp);
	    node.setH(computeH2(node));
	}

	public List<Node> getSuccessors(Node currentState) {
        ArrayList<Node> successors = new ArrayList<>();

        for (char operator : operators) {
            Node tmp = moveWhiteTile(currentState, operator);
            if (tmp != null) {
                successors.add(tmp);
            }
        }

        return successors;
    }

	public Node getInitialState() {
		return initialState;
	}

	public Node getGoalState() {
		return goalState;
	}
}
