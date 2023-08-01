public class Spider {
    private int x; // x position
    private int y; // y position
    private Direction direction; // facing direction

    // Enum representing the four possible directions
    public enum Direction {NORTH, EAST, SOUTH, WEST}

    public Spider(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; }

    @Override
    public String toString() {
        return "Spider{" +
                "X: " + x +
                ", Y: " + y +
                ", Direction: " + direction +
                '}';
    }
}
