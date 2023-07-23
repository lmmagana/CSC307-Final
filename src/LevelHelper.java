import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner; // Import the Scanner class to read text files

public class LevelHelper {

    private static ArrayList<Level> levels;
    private static final int[] gridSizes = {3, 4, 5, 6, 7};

    public LevelHelper(){
        File f = new File("Levels.txt");
        try {
            System.out.println(f.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            File myLevels = new File("Levels.txt");
            Scanner readLevels = new Scanner(myLevels);
            levels = new ArrayList<Level>();

            while (readLevels.hasNextLine()){
                String[] txt = readLevels.nextLine().split(" ");
                if(txt[0].equals("-Level")){
                    System.out.println(txt[0] + " " + txt[1] + ": Generated");
                    Level currentLevel = new Level();
                    currentLevel.setGridSize(getGrid(readLevels));
                    currentLevel.setNumberOfDiamonds(getNumberOfDiamonds(readLevels));
                    for(int i = 0; i < currentLevel.getNumberOfDiamonds(); i ++){
                        currentLevel.addDiamond(getDiamond(readLevels));
                    }
                    getSpiderInitialPos(currentLevel, readLevels);
                    currentLevel.setSpiderDirection(getSpiderInitialDir(readLevels));
                    levels.add(currentLevel);
                }
                else if (txt[0].equals("DYNAMIC")){
                    level7();
                    level8();
                    level9();
                    level10();
                    level11();
                    level12();
                    level13();
                    level14();
                    level15();
                    break;
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        }
    }

    private int getGrid(Scanner readLevels){
        String[] line = readLevels.nextLine().split(" ");
        if(line.length == 3 && line[0].equals("Grid")){
            return Integer.parseInt(line[2]);
        }
        else throw new RuntimeException("File not formatted correctly when getting Grid Size");
    }

    private int getNumberOfDiamonds(Scanner readLevels){
        String[] line = readLevels.nextLine().split(" ");
        if(line.length == 2 && line[0].equals("Diamonds:")){
            return Integer.parseInt(line[1]);
        }
        else throw new RuntimeException("File not formatted correctly when getting Number of Diamonds");
    }

    private Diamond getDiamond(Scanner readLevels){
        String[] line = readLevels.nextLine().split(" ");
        Diamond diamond = new Diamond();
        if(line.length == 3 && (line[0].equals("Red") | line[0].equals("Green") | line[0].equals("Blue"))){
            diamond.setColor(line[0]);
            diamond.setX(Integer.parseInt(line[1]));
            diamond.setY(Integer.parseInt(line[2]));
            return diamond;
        } else throw new RuntimeException("File not formatted correctly when getting Diamonds");
    }

    private void getSpiderInitialPos(Level currentLevel, Scanner readLevels){
        String[] line = readLevels.nextLine().split(" ");
        if(line.length == 5 && line[2].equals("Position:")){
            currentLevel.setSpiderX(Integer.parseInt(line[3]));
            currentLevel.setSpiderY(Integer.parseInt(line[4]));
        } else throw new RuntimeException("File not fomatted correctly when getting Spider Initial Position");
    }

    private int getSpiderInitialDir(Scanner readLevels){
        String[] line = readLevels.nextLine().split(" ");
        if(line.length == 4 && line[2].equals("Direction:")){
            return switch (line[3]) {
                case "Up" -> 0;
                case "Right" -> 1;
                case "Down" -> 2;
                case "Left" -> 3;
                default -> 0;
            };
        }
        else throw new RuntimeException("File not formatted correctly when getting Initial Direction");
    }

    public Level getLevel(int level){
        return levels.get(level - 1);
    }

    public void regenerateLevel(int level){
        switch(level){
            case 7:
                level7();
                break;
            case 8:
                level8();
                break;
            case 9:
                level9();
                break;
            case 10:
                level10();
                break;
            case 11:
                level11();
                break;
            case 12:
                level12();
                break;
            case 13:
                level13();
                break;
            case 14:
                level14();
                break;
            case 15:
                level15();
                break;
            default:
                break;
        }
    }

    private void level7(){
        Level currentLevel = new Level();
        currentLevel.setGridSize(gridSizes[new Random().nextInt(gridSizes.length)]);
        dynamicSpiderPosition(currentLevel);
        currentLevel.setNumberOfDiamonds(1);
        currentLevel.addDiamond(new Diamond("Red", 2, 1));
        levels.set(6, currentLevel);
    }

    private void level8(){
        Level currentLevel = new Level();
        currentLevel.setGridSize(gridSizes[new Random().nextInt(gridSizes.length)]);
        dynamicSpiderPosition(currentLevel);
        currentLevel.setNumberOfDiamonds(currentLevel.getGridSize());
        for(int i = 2; i <= currentLevel.getGridSize(); i++){
            currentLevel.addDiamond(new Diamond("Blue", i, 1));
        }
        currentLevel.addDiamond(new Diamond("Red", currentLevel.getGridSize(), currentLevel.getGridSize()));
        levels.set(7, currentLevel);
    }

    private void level9(){
        Level currentLevel = new Level();
        currentLevel.setGridSize(gridSizes[new Random().nextInt(gridSizes.length)]);
        dynamicSpiderPosition(currentLevel);
        currentLevel.setNumberOfDiamonds(currentLevel.getGridSize());
        currentLevel.addDiamond(new Diamond("Red", currentLevel.getGridSize(), 1));
        for(int i = 1; i < currentLevel.getGridSize() - 1; i ++){
            currentLevel.addDiamond(new Diamond("Blue", currentLevel.getGridSize() - i, i + 1));
        }
        currentLevel.addDiamond(new Diamond("Red", 1, currentLevel.getGridSize()));
        levels.set(8, currentLevel);
    }

    private void level10(){
        Level currentLevel = new Level();
        currentLevel.setGridSize(gridSizes[new Random().nextInt(gridSizes.length)]);
        dynamicSpiderPosition(currentLevel);
        currentLevel.setNumberOfDiamonds(3);
        currentLevel.addDiamond(new Diamond("Red", 2, 1));
        currentLevel.addDiamond(new Diamond("Red", 2, 2));
        currentLevel.addDiamond(new Diamond("Red", 1, 2));
        levels.set(9, currentLevel);
    }

    private void level11(){
        Level currentLevel = new Level();
        currentLevel.setGridSize(gridSizes[new Random().nextInt(gridSizes.length)]);
        dynamicSpiderPosition(currentLevel);
        currentLevel.setNumberOfDiamonds((currentLevel.getGridSize() * 4) - 4);
        for(int i = 1; i <= currentLevel.getGridSize(); i ++){
            currentLevel.addDiamond(new Diamond("Red", i, 0));
            currentLevel.addDiamond(new Diamond("Red", i, currentLevel.getGridSize()));
        }
        for(int i = 2; i < currentLevel.getGridSize(); i ++){
            currentLevel.addDiamond(new Diamond("Red", 1, i));
            currentLevel.addDiamond(new Diamond("Red", currentLevel.getGridSize(), i));
        }
        levels.set(10, currentLevel);
    }

    private void level12(){
        Level currentLevel = new Level();
        currentLevel.setGridSize(5); //Not Dynamic
        dynamicSpiderPosition(currentLevel);
        currentLevel.setNumberOfDiamonds(3);
        currentLevel.addDiamond(new Diamond("Red", 4, 2));
        currentLevel.addDiamond(new Diamond("Blue", 3, 3));
        currentLevel.addDiamond(new Diamond("Green", 2, 4));
        levels.set(11, currentLevel);
    }

    private void level13(){
        Level currentLevel = new Level();
        currentLevel.setGridSize(gridSizes[new Random().nextInt(gridSizes.length)]);
        dynamicSpiderPosition(currentLevel);
        currentLevel.setNumberOfDiamonds((currentLevel.getGridSize() * 2) - 1);
        currentLevel.addDiamond(new Diamond("Red", 1, 1));
        for (int i = 2; i <= currentLevel.getGridSize(); i ++){
            currentLevel.addDiamond(new Diamond("Red", 1, i));
            currentLevel.addDiamond(new Diamond("Red", i, currentLevel.getGridSize()));
        }
        levels.set(12, currentLevel);
    }

    private void level14(){
        Level currentLevel = new Level();
        currentLevel.setGridSize(gridSizes[new Random().nextInt(gridSizes.length)]);
        dynamicSpiderPosition(currentLevel);
        currentLevel.setNumberOfDiamonds((currentLevel.getGridSize() * 3) - 2);
        for(int i = 1; i <= currentLevel.getGridSize(); i ++){
            currentLevel.addDiamond(new Diamond("Red", i, 0));
            currentLevel.addDiamond(new Diamond("Red", i, currentLevel.getGridSize()));
        }
        for(int i = 1; i < currentLevel.getGridSize() - 1; i ++){
            currentLevel.addDiamond(new Diamond("Red", currentLevel.getGridSize() - i, i + 1));
        }
        levels.set(13, currentLevel);
    }

    private void level15(){
        Level currentLevel = new Level();
        currentLevel.setGridSize(gridSizes[new Random().nextInt(gridSizes.length)]);
        dynamicSpiderPosition(currentLevel);

        for(int i = 0; i < currentLevel.getGridSize(); i ++){
            currentLevel.addDiamond(new Diamond("Blue", currentLevel.getGridSize() - i, i + 1));
        }
        for(int i = 1; i < currentLevel.getGridSize(); i ++){
            currentLevel.addDiamond(new Diamond("Red", currentLevel.getGridSize() - i, i));
        }
        levels.set(14, currentLevel);
    }

    private void dynamicSpiderPosition(Level currentlevel){
        currentlevel.setSpiderX(1);
        currentlevel.setSpiderY(1);
        currentlevel.setSpiderDirection(1);
    }
}
