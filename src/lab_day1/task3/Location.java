package lab_day1.task3;

public class Location {
    private int rowLocation;
    private int colLocation;

    public Location(int rowLocation, int colLocation) {
        this.rowLocation = rowLocation;
        this.colLocation = colLocation;
    }

    public int getRowLocation() {
        return rowLocation;
    }

    public int getColLocation() {
        return colLocation;
    }

    @Override
    public String toString() {
        return "row: " + rowLocation + ", col: " + colLocation +"\n";
    }
}
