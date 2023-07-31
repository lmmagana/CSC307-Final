import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class DraggableLabel extends JLabel implements MouseListener {
    private int mousePressedX;
    private int mousePressedY;
    private boolean initialBlock = true;

    int xpos;
    int ypos;

    DraggableLabel(String text, int x, int y) {
        super(text);
        addMouseListener(this); // Add the current instance of DraggableLabel as the MouseListener
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = getLocation().x + e.getX() - mousePressedX;
                int y = getLocation().y + e.getY() - mousePressedY;
                setLocation(x, y);
                getParent().repaint();
            }
        });
    }

    public void setInitialFlag(Boolean val){
        initialBlock = val;
    }
    public int getXPos(){return xpos;}
    public int getYPos(){return ypos;}
    public void setXPos(int x){ xpos = x;}
    public void setYPos(int y){ ypos = y;}

    // Implement the mousePressed method to create a new DraggableLabel
    public void mousePressed(MouseEvent e) {
        mousePressedX = e.getX();
        mousePressedY = e.getY();
        DraggablePanel parentPanel = (DraggablePanel) getParent();
        int x = getLocation().x;
        int y = getLocation().y;

        // Create a new DraggableLabel at the initial position
        if (initialBlock) {
            DraggableLabel newLabel = new DraggableLabel(getText(), x, y);
            newLabel.setOpaque(true);
            newLabel.setBackground(Color.WHITE);
            newLabel.setBounds(x, y, 180, 35);
            parentPanel.add(newLabel);
            System.out.println("New Label added");
            parentPanel.repaint();
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {
        System.out.println("Label released: " + getText());
        DraggablePanel parentPanel = (DraggablePanel) getParent();
        // Snap to other labels if released nearby
        int snapThreshold = 50; // Adjust this value as needed for the snapping sensitivity
        Point center = new Point(getLocation().x + getWidth() / 2, getLocation().y + getHeight() / 2);
        Component[] components = parentPanel.getComponents();
        for (Component component : components) {
            if (component instanceof DraggableLabel && component != this) {
                Point otherCenter = new Point(component.getLocation().x + component.getWidth() / 2,
                    component.getLocation().y + component.getHeight() / 2);

                double distance = center.distance(otherCenter);
                if (distance <= snapThreshold) {
                    // Snap to the center of the other label
                    int x = component.getLocation().x + component.getWidth() / 2 - getWidth() / 2;
                    int y = component.getLocation().y + component.getHeight() / 2 - getHeight() / 2;
                    setLocation(x, y + 35);
                    parentPanel.repaint();
                    break;
                }
            }
        }
        xpos = getLocation().x;
        ypos = getLocation().y;
        System.out.println(xpos + " " + ypos);
        if (initialBlock){
            parentPanel.addDraggedLabel(this);
        } else if (!initialBlock) {
            Point trashIconPosition = new Point(35, 575);
            int trashIconWidth = 75;
            int trashIconHeight = 75;

            if (center.x >= trashIconPosition.x && center.x <= trashIconPosition.x + trashIconWidth &&
                center.y >= trashIconPosition.y && center.y <= trashIconPosition.y + trashIconHeight) {
                // Label dropped on the trash icon
                System.out.println("Label dropped on the trash icon.");
                // Perform any other actions you want when the label is dropped on the trash icon.
                if (InstructionList.getInstructions().getSize() > 0) {
                    InstructionList.getInstructions().removeInstructionsBelow(InstructionList.getInstructions().getSize() - 1);
                }
                parentPanel.popDraggedLabel();
                parentPanel.remove(this);
                parentPanel.repaint();
            }
        }
        if (initialBlock) {
            switch (getText()) {
                case ("Step"):
                    InstructionList.getInstructions().addStep();
                    break;
                case ("Turn"):
                    InstructionList.getInstructions().addTurn();
                    break;
                case ("Paint Red"):
                    InstructionList.getInstructions().addPaintRed();
                    break;
                case ("Paint Blue"):
                    InstructionList.getInstructions().addPaintBlue();
                    break;
                case ("Paint Green"):
                    InstructionList.getInstructions().addPaintGreen();
                    break;
                case ("Paint Black"):
                    InstructionList.getInstructions().addPaintBlack();
                    break;
            }
            System.out.println("Instruction: " + getText() + " added to InstrList");
        }
        initialBlock = false;
    }
}

class DraggablePanel extends JPanel {
    private List<DraggableLabel> draggedLabels = new ArrayList<>();
    DraggablePanel() {
        setLayout(null);
    }
    public void addDraggableLabel(String text, int x, int y, Boolean initial) {
        DraggableLabel label = new DraggableLabel(text, x, y);
        label.setInitialFlag(initial);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setBounds(x, y, 180, 35); // Set the size of the label here
        label.setXPos(x);
        label.setYPos(y);
        add(label);
    }
    public void clearBoard(){
        removeAll();
        draggedLabels.clear();
        repaint();
    }
    public void addDraggedLabel(DraggableLabel label) {
        draggedLabels.add(label);
    }
    public List<DraggableLabel> getDraggedLabels() {
        return draggedLabels;
    }
    public void popDraggedLabel(){
        for (DraggableLabel str : draggedLabels)
        {
            System.out.print(str.getXPos() + " and ");
            System.out.println(str.getYPos());
        }
        draggedLabels.remove(draggedLabels.size() - 1);
        System.out.println("Removed label from draggedLabels");
        for (DraggableLabel str : draggedLabels)
        {
            System.out.print(str.getXPos() + " and ");
            System.out.println(str.getYPos());
        }
    }
    public void popDraggableLabel(){
    }
}

public class WorkArea extends JPanel{
    private DraggablePanel dragPanel = new DraggablePanel();
    private JLabel trashLabel;
    public WorkArea() {
        // Visuals initialization
        initialize();
    }
    public void initialize(){
        setLayout(new BorderLayout());
        // DraggablePanel dragPanel = new DraggablePanel();
        dragPanel.addDraggableLabel("Step", 430, 25, true); // Set initial position
        dragPanel.addDraggableLabel("Turn", 430, 75, true);
        dragPanel.addDraggableLabel("Paint Red", 430, 125, true);
        dragPanel.addDraggableLabel("Paint Blue", 430, 175, true);
        dragPanel.addDraggableLabel("Paint Green", 430, 225, true);
        dragPanel.addDraggableLabel("Paint Black", 430, 275, true);
        dragPanel.addDraggableLabel("Loop: Repeat Until Hit Wall", 430, 325, true);
        dragPanel.addDraggableLabel("Loop: Repeat Until Color", 430, 375, true);
        dragPanel.addDraggableLabel("End Loop", 430, 425, true);

        ImageIcon trashIcon = new ImageIcon("./images/trash-bin-3.png");
        trashLabel = new JLabel(trashIcon);
        trashLabel.setBounds(35, 575, 75, 75); // Set the size of the label here
        dragPanel.add(trashLabel);
        add(dragPanel);
    }
    public JLabel getTrashLabel(){ return trashLabel; }
    public void addFromButton(String text, int x, int y){
        List<DraggableLabel> list = dragPanel.getDraggedLabels();
        for (DraggableLabel str : list)
        {
            System.out.print(str.getXPos() + " and ");
            System.out.println(str.getYPos());
        }
        if (list.size() != 0) {
            int xpos = list.get(list.size() - 1).getXPos();
            int ypos = list.get(list.size() - 1).getYPos();
            DraggableLabel labelFromButton = new DraggableLabel(text, xpos, ypos);
            labelFromButton.setInitialFlag(false);
            labelFromButton.setXPos(xpos);
            labelFromButton.setYPos((ypos + 35));
            dragPanel.addDraggedLabel(labelFromButton);
            dragPanel.addDraggableLabel(text, xpos, ypos + 35, false);
        } else {
            System.out.println("The list of DraggableLabels is empty.");
            DraggableLabel labelFromButton = new DraggableLabel(text, x, y);
            labelFromButton.setInitialFlag(false);
            labelFromButton.setXPos(180);
            labelFromButton.setYPos((25));
            dragPanel.addDraggedLabel(labelFromButton);
            dragPanel.addDraggableLabel(text, 180, 25, false);
        }
    }
    public DraggablePanel getDragPanel(){
        return dragPanel;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
