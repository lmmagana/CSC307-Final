import java.util.LinkedList;

public class Instruction {

    private int x;
    private int y;
    private static String instruction;
    private static LinkedList<Instruction> repeatList;

    public Instruction(String instruction, int x, int y){
        this.x = x;
        this.y = y;
        this.instruction = instruction;
        if(instruction.equals("Repeat Until Wall") | instruction.equals("Repeat Until Color")){
           repeatList = new LinkedList<Instruction>();
        }
    }

    public static String getInstruction() {
        return instruction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
