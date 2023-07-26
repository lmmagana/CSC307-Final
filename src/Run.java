import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class Run implements Observer{

    private int spiderX;
    private int spiderY;
    private Spider.Direction spiderDirection;
    private Level lvl;
    private InstructionList instructions;
    private Color[] grid; //1D array for Grid
    private int speed;

    public Run(){
        speed = LevelHelper.getLevels().getRunSpeed();
        lvl = LevelHelper.getLevels().getLevel();
        instructions = InstructionList.getInstructions();
        grid = new Color[lvl.getGridSize() * lvl.getGridSize()];
        Arrays.fill(grid, Color.BLACK);
        spiderX = lvl.getSpiderX();
        spiderY = lvl.getSpiderY();
        spiderDirection = lvl.getSpiderDirection();
    }

    public boolean execute(){
        recursiveLoop(instructions.getInstructionLinkedList(), "Main List", null); //Populates grid with Colors
        return checkResult();
    }

    private void recursiveLoop(LinkedList<Instruction> instructionList, String flg, Color clr){
        for(int i = 0; i < instructionList.size(); i++){
            Instruction inst = instructionList.get(i);
            if(inst.checkForLoop()) recursiveLoop(inst.getRepeatInstructions(), inst.getInstruction(), inst.getPaintColor());
            else {
                try { Thread.sleep(speed);
                } catch (InterruptedException e) { e.printStackTrace(); }
                computeInstruction(inst);
            }
            if(flg.equals("Repeat Until Wall") && i == instructionList.size() - 1 && hitWallHelper()) break;
            if(flg.equals("Repeat Until Color") && i == instructionList.size() - 1 && colorDetectHelper(clr)) break;
            if(flg.split(" ")[0].equals("Repeat") && i == instructionList.size() - 1) i = -1;
        }
    }

    private void computeInstruction(Instruction instruction){
        switch (instruction.getInstruction()){
            case "Step":
                switch (spiderDirection) {
                    case NORTH -> spiderY--;
                    case EAST -> spiderX++;
                    case SOUTH -> spiderY++;
                    case WEST -> spiderX--;
                    //VISUALLY MOVE SPIDER
                }
                break;
            case "Turn":
                switch (spiderDirection){
                    case EAST -> spiderDirection = Spider.Direction.SOUTH;
                    case WEST -> spiderDirection = Spider.Direction.NORTH;
                    case SOUTH -> spiderDirection = Spider.Direction.WEST;
                    case NORTH -> spiderDirection = Spider.Direction.EAST;
                    //VISUALLY UPDATE SPIDER DIRECTION
                }
                break;
            case "Paint Red":
                setgridSpaceColor(spiderX, spiderY, Color.RED);
                //VISUALLY UPDATE GRID SPACE COLOR
                break;
            case "Paint Blue":
                setgridSpaceColor(spiderX, spiderY, Color.BLUE);
                //VISUALLY UPDATE GRID SPACE COLOR
                break;
            case "Paint Green":
                setgridSpaceColor(spiderX, spiderY, Color.GREEN);
                //VISUALLY UPDATE GRID SPACE COLOR
                break;
            case "Paint Black":
                setgridSpaceColor(spiderX, spiderY, Color.BLACK);
                //VISUALLY UPDATE GRID SPACE COLOR
                break;
        }
    }

    private boolean hitWallHelper(){
        return switch (spiderDirection) {
            case NORTH -> spiderY - 1 <= 0;
            case EAST -> spiderX + 1 > LevelHelper.getLevels().getLevel().getGridSize();
            case SOUTH -> spiderY + 1 > LevelHelper.getLevels().getLevel().getGridSize();
            case WEST -> spiderX - 1 <= 0;
        };
    }

    private boolean checkResult(){
        for(int i = 0; i < lvl.getNumberOfDiamonds(); i ++){
            Diamond check = lvl.getDiamond(i);
            Color gridColor = getGridSpaceColor(check.getX(), check.getY());
            if(gridColor != check.getColor()) return false;
        }
        return true;
    }

    private boolean colorDetectHelper(Color color){
        return getGridSpaceColor(spiderX, spiderY) == color; }

    private void setgridSpaceColor(int x, int y, Color clr){
        grid[(x - 1) + (y - 1) * lvl.getGridSize()] = clr; }

    private Color getGridSpaceColor(int x, int y){
        return grid[(x - 1) + (y - 1) * lvl.getGridSize()]; }

    @Override
    public void update(Observable o, Object arg) {
        speed = LevelHelper.getLevels().getRunSpeed();
        lvl = LevelHelper.getLevels().getLevel();
        grid = new Color[lvl.getGridSize() * lvl.getGridSize()];
        Arrays.fill(grid, Color.BLACK);
        spiderX = lvl.getSpiderX();
        spiderY = lvl.getSpiderY();
        spiderDirection = lvl.getSpiderDirection();
    }
}
