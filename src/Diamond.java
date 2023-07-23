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

    public int getX() {
        return x; }

    public void setX(int x) {
        this.x = x; }

    public int getY() {
        return y; }

    public void setY(int y) {
        this.y = y; }

    public Color getColor() {
        return color; }

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
}
