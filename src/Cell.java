public class Cell {
    private boolean painted;
    private String color;

    public Cell() {
        this.painted = false;
        this.color = "None"; // Default color is None, indicating that the cell is not painted.
    }

    public boolean isPainted() {
        return painted;
    }
    public void setPainted(boolean painted) {
        this.painted = painted;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "painted=" + painted +
                ", color='" + color + '\'' +
                '}';
    }
}
