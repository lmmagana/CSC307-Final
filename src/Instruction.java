import java.util.LinkedList;

public class Instruction {
    private  String instruction;
    private  LinkedList<Instruction> repeatList;

    public Instruction(String instruction){
        this.instruction = instruction;
        if(instruction.equals("Repeat Until Wall") | instruction.equals("Repeat Until Color")){
           repeatList = new LinkedList<Instruction>();
        }
    }

    public Boolean checkForLoop(){ return repeatList != null; }
    public LinkedList<Instruction> getRepeatInstructions(){ return repeatList; }
    public int getRepeatSize(){return repeatList.size();}
    public String getInstruction() { return instruction; }
    public void addToRepeat(Instruction instruction){repeatList.add(instruction);}
    public void insertRepeat(int pos, Instruction instruction){repeatList.add(pos, instruction);}

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
