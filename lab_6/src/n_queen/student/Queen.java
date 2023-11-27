package n_queen.student;

public class Queen {
	private int row;
	private int column;

	public Queen(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	// Move the queen by 1 row
	public void move() {
		// Enter your code here
		// Check if queen out of board
		if (row == 0) {
			row++;
		}
		else if (row == (Node.N - 1)) {
			row--;
		}
		else {
			row++;
		}
	}

	// check whether this Queen can attack the given Queen (q)
	public boolean isConflict(Queen q) {
		// Enter your code here
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
}
