import java.util.ArrayList;

public class Level {

    private int gridSize;
    private int numberOfDiamonds;
    private ArrayList<Diamond> diamonds;
//    private int spiderX;
//    private int spiderY;
//    private int spiderDirection;
    private Spider spider;

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
        return this.spider.getX(); }

    public void setSpiderX(int spiderX) {
        this.spider.setX(spiderX); }

    public int getSpiderY() {
        return this.getSpiderY(); }

    public void setSpiderY(int spiderY) {
        this.spider.setY(spiderY); }

    public Spider.Direction getSpiderDirection() {
        return this.spider.getDirection(); }

    public void setSpiderDirection(Spider.Direction direction) {
        this.spider.setDirection(direction); }

    public void addSpider(Spider spider){
        this.spider = spider;
    }
}
