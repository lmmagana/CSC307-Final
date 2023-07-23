import java.util.ArrayList;

public class Level {

    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    private int gridSize;
    private int numberOfDiamonds;
    private ArrayList<Diamond> diamonds;
    private int spiderX;
    private int spiderY;
    private int spiderDirection;

    public Level(){ }

    public int getGridSize() {
        return gridSize; }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize; }

    public int getNumberOfDiamonds() {
        return numberOfDiamonds; }

    public void setNumberOfDiamonds(int numberOfDiamonds) {
        this.numberOfDiamonds = numberOfDiamonds; }

    public ArrayList<Diamond> getDiamonds() {
        return diamonds; }

    public Diamond getDiamond(int pos){
        return diamonds.get(pos);
    }

    public void addDiamond(Diamond diamond){
        if(this.diamonds == null){
            this.diamonds = new ArrayList<Diamond>();
        }
        this.diamonds.add(diamond); }

    public int getSpiderX() {
        return spiderX; }

    public void setSpiderX(int spiderX) {
        this.spiderX = spiderX; }

    public int getSpiderY() {
        return spiderY; }

    public void setSpiderY(int spiderY) {
        this.spiderY = spiderY; }

    public int getSpiderDirection() {
        return spiderDirection; }

    public void setSpiderDirection(int spiderDirection) {
        this.spiderDirection = spiderDirection; }
}
