import java.awt.*;

public class Diamond {

    private int x;
    private int y;
    private Color color;

    public Diamond(){}

    public Diamond(String color, int x, int y){
        setX(x);
        setY(y);
        setColor(color);
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public Color getColor() { return color; }

    public void setColor(String color) {
        switch (color){
            case("Red"):
                this.color = Color.RED;
                break;
            case("Green"):
                this.color = Color.GREEN;
                break;
            case("Blue"):
                this.color = Color.BLUE;
                break;
            default:
                throw new RuntimeException("Diamond Color Does Not Exist");
        }
    }

    @Override
    public String toString() {
        if(getColor() == Color.RED) return "Diamond{Color: Red, " + "X: " + getX() + ", Y: " + getY() + "}";
        if(getColor() == Color.BLUE) return "Diamond{Color: Blue, " + "X: " + getX() + ", Y: " + getY() + "}";
        if(getColor() == Color.GREEN) return "Diamond{Color: Green, " + "X: " + getX() + ", Y: " + getY() + "}";
        else return "Color: " + getColor() + ", X: " + getX() + ", Y: " + getY() + "}";
    }
}
