import java.awt.*;
import java.util.LinkedList;

public class Instruction {
    private String instruction;
    private LinkedList<Instruction> repeatList;
    private Color paintColor;

    public Instruction(String instruction){
        this.instruction = instruction;
        if(instruction.equals("Repeat Until Wall") | instruction.equals("Repeat Until Color")){
           repeatList = new LinkedList<Instruction>();
        }
    }

    public Instruction(String instruction, Color paintColor){
        this.instruction = instruction;
        this.paintColor = paintColor;
        if(instruction.equals("Repeat Until Wall") | instruction.equals("Repeat Until Color")){
            repeatList = new LinkedList<Instruction>();
        }
    }

    public Boolean checkForLoop(){ return repeatList != null; }
    public LinkedList<Instruction> getRepeatInstructions(){ return repeatList; }
    public String getInstruction() { return instruction; }
    public Color getPaintColor(){return this.paintColor;}

    @Override
    public String toString() {
        if(repeatList == null) return instruction;
        else{
            String[] instructionlist = new String[repeatList.size()];
            for(int i = 0; i < repeatList.size(); i ++){
                instructionlist[i] = repeatList.get(i).toString();
            }
            return instruction + " {" + String.join(", ", instructionlist) + "}";
        }
    }
}
