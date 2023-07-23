import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class LevelHelper {

    private static ArrayList<Level> levels;

    public LevelHelper(){
        try{
            File myLevels = new File("Levels.txt");
            Scanner readLevels = new Scanner(myLevels);
            levels = new ArrayList<Level>();

            while (readLevels.hasNextLine()){
                String[] txt = readLevels.nextLine().split(" ");
                if(txt[0].equals("-Level")){
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
}
