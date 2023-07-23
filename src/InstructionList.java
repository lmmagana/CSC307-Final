import java.awt.*;
import java.util.LinkedList;
import java.util.Observable;

public class InstructionList extends Observable{

    private static final int SIZEOFCODEBLOCK = 10;

    private static InstructionList _instance;
    private LinkedList<Instruction> instructions;

    private InstructionList(){instructions = new LinkedList<Instruction>();}

    public static InstructionList getInstance(){
        if(_instance == null) _instance = new InstructionList();
        return _instance;
    }

    //TURN INSTRUCTIONS
    public void addInstuction(String str){
        instructions.add(new Instruction(str,
                instructions.getLast().getX() + SIZEOFCODEBLOCK,
                instructions.getLast().getY()));}

    public void addInstuction(String str, int x, int y){
        instructions.add(new Instruction(str, x, y));}

    public void addInstuction(String str, int position){
        instructions.add(position, new Instruction(str,
                instructions.get(position).getX(),
                instructions.get(position).getY()));
        shiftDown(position);}

    //REPEAT INSTRUCTIONS
    public void addRepeatTillWall(int position, int x, int y){
        instructions.add(new Instruction("Repeat Until Wall", x, y));
        //update positions (x & y) of
    }

    public void addRepeatTillColor(int position, int x, int y){
        instructions.add(new Instruction("Repeat Until Color", x, y));
        //update positions (x & y) of
    }

    private void shiftDown(int position){

    }
    
}
