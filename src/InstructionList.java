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

    public void addTurn(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Turn"));}
    public void addStep(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Step"));}
    public void addPaintRed(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Paint Red"));}
    public void addPaintBlue(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Paint Blue"));}
    public void addPaintGreen(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Paint Green"));}
    public void addPaintBlack(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Paint Black"));}
    public void addRepeatUntilWall(LinkedList<Instruction> instructionSet){instructionSet.add(new Instruction("Repeat Until Wall"));}
    public void addRepeatUntilColor(LinkedList<Instruction> instructionSet, Color color){instructionSet.add(new Instruction("Repeat Until Color", color));}
    public void removeInstructionsBelow(int pos){ if (getSize() > pos) instructions.subList(pos, getSize()).clear(); }
    public void clearInstructionList(){instructions.clear(); }

    public LinkedList<Instruction> getLastRepeat(){
        LinkedList<Instruction> ret = InstructionList.getInstructions().getInstructionLinkedList();
        while(ret.size() > 0 && ret.getLast().checkForLoop()){
            ret = ret.getLast().getRepeatInstructions();
        } return ret;
    }

    @Override
    public String toString() {
        String[] instructionlist = new String[_instance.getSize()];
        for(int i = 0; i < _instance.getSize(); i ++){
            instructionlist[i] = _instance.getInstruction(i).toString();
        }
        return "Instructions: {" + String.join(", ", instructionlist) + "}";
    }
}
