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

    public Instruction getInstruction(int pos){ return instructions.get(pos); }
    public LinkedList<Instruction> getInstructionLinkedList(){ return instructions; }
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

    public void addRepeatUntilColor(Color color){ instructions.add(new Instruction("Repeat Until Color", color)); }
    public void addRepeatUntilColor(LinkedList<Instruction> instructionSet, Color color){instructionSet.add(new Instruction("Repeat Until Color", color));}
    public void insertRepeatUntilColor(int pos){
        instructions.add(pos, new Instruction("Repeat Until Wall"));
    }

    public void removeInstruction(int pos){ instructions.remove(pos); }
    public void removeInstruction(LinkedList<Instruction> instructionSet, int pos){ instructionSet.remove(pos); }

    public void removeInstructionsBelow(int pos){ if (getSize() > pos) instructions.subList(pos, getSize()).clear(); }
    public void removeInstructionsBelow(LinkedList<Instruction> instructionSet, int pos){
        if (getSize() > pos) instructionSet.subList(pos, getSize()).clear(); }

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

    public Instruction getLast(){ return instructions.getLast();}

    public LinkedList<Instruction> getLastRepeat(){
        LinkedList<Instruction> ret = InstructionList.getInstructions().getInstructionLinkedList();
        while(ret.size() > 0 && ret.getLast().checkForLoop()){
            ret = ret.getLast().getRepeatInstructions();
        }
        return ret;
    }

    @Override
    public String toString() {
        String[] instructionlist = new String[_instance.getSize()];
        for(int i = 0; i < _instance.getSize(); i ++){
            instructionlist[i] = _instance.getInstruction(i).toString();
        }
        return "Instructions: {" + String.join(", ", instructionlist) + "}";
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
