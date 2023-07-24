import java.awt.*;
import java.util.LinkedList;
import java.util.Observable;

public class InstructionList extends Observable{

    private static InstructionList _instance;
    private LinkedList<Instruction> instructions;

    private InstructionList(){instructions = new LinkedList<Instruction>();}

    public static InstructionList getInstructions(){
        if(_instance == null) _instance = new InstructionList();
        return _instance;
    }

    public Instruction getInstruction(int pos){
        return instructions.get(pos);
    }
    public Instruction getLast(){return instructions.getLast();}
    public int getSize(){return instructions.size();}

    public void addTurn(){ instructions.add(new Instruction("Turn")); }
    public void addTurn(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Turn"));}
    public void insertTurn(int pos){
        instructions.add(pos, new Instruction("Turn"));
    }

    public void addStep(){ instructions.add(new Instruction("Step")); }
    public void addStep(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Step"));}
    public void insertStep(int pos){
        instructions.add(pos, new Instruction("Step"));
    }

    public void addPaintRed(){ instructions.add(new Instruction("Paint Red")); }
    public void addPaintRed(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Paint Red"));}
    public void insertPaintRed(int pos){
        instructions.add(pos, new Instruction("Paint Red"));
    }

    public void addPaintBlue(){ instructions.add(new Instruction("Paint Blue")); }
    public void addPaintBlue(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Paint Blue"));}
    public void insertPaintBlue(int pos){
        instructions.add(pos, new Instruction("Paint Blue"));
    }

    public void addPaintGreen(){ instructions.add(new Instruction("Paint Green")); }
    public void addPaintGreen(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Paint Green"));}
    public void insertPaintGreen(int pos){
        instructions.add(pos, new Instruction("Paint Green"));
    }

    public void addPaintBlack(){ instructions.add(new Instruction("Paint Black")); }
    public void addPaintBlack(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Paint Black"));}
    public void insertPaintBlack(int pos){
        instructions.add(pos, new Instruction("Paint Black"));
    }

    public void addRepeatUntilWall(){ instructions.add(new Instruction("Repeat Until Wall")); }
    public void addRepeatUntilWall(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Repeat Until Wall"));}
    public void insertRepeatUntilWall(int pos){
        instructions.add(pos, new Instruction("Repeat Until Wall"));
    }

    public void addRepeatUntilColor(){ instructions.add(new Instruction("Repeat Until Color")); }
    public void addRepeatUntilColor(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Repeat Until Color"));}
    public void insertRepeatUntilColor(int pos){
        instructions.add(pos, new Instruction("Repeat Until Wall"));
    }

    public void removeInstructions(LinkedList<Instruction> instructions, int pos){}

    public void clearInstructionList(){instructions.clear(); }
    
    private void addToRepeatHelper(Instruction instruction){
        for(int i = instructions.size() - 1; i >= 0; i --){
            Instruction current = instructions.get(i);
            if(current.checkForLoop()){
                current.addToRepeat(instruction);
                return;
            }
        }
        throw new RuntimeException("No loop to add Instruction To");
    }
    

    private void insertHelper(int pos, int currentInx, LinkedList<Instruction> instList, Instruction instruction) {
        int indxWithinLoop = 0;
        while (currentInx != pos && indxWithinLoop < instList.size()) {
            if (instList.get(indxWithinLoop).checkForLoop()) {
                insertHelper(pos, currentInx + 1, instList.get(indxWithinLoop).getRepeatInstructions(), instruction);
            }
            currentInx++;
            indxWithinLoop++;
        }
        if (currentInx == pos) instList.add(indxWithinLoop, instruction);
    }
}
