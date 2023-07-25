import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class DraggableLabel extends JLabel implements MouseListener {
    private int mousePressedX;
    private int mousePressedY;

    DraggableLabel(String text) {
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

    // Implement the mousePressed method to create a new DraggableLabel
    public void mousePressed(MouseEvent e) {
        mousePressedX = e.getX();
        mousePressedY = e.getY();
        DraggablePanel parentPanel = (DraggablePanel) getParent();
        int x = getLocation().x;
        int y = getLocation().y;

        // Create a new DraggableLabel at the initial position
        DraggableLabel newLabel = new DraggableLabel(getText());
        newLabel.setOpaque(true);
        newLabel.setBackground(Color.WHITE);
        newLabel.setBounds(x, y, 75, 35);
        parentPanel.add(newLabel);
        parentPanel.repaint();
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {
        System.out.println("Label released: " + getText());

        DraggablePanel parentPanel = (DraggablePanel) getParent();
        parentPanel.addDraggedLabel(this);

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

        switch(getText()){
            case("Step"):
                InstructionList.getInstructions().addStep();
                break;
            case("Turn"):
                InstructionList.getInstructions().addTurn();
                break;
            case("Paint Red"):
                InstructionList.getInstructions().addPaintRed();
                break;
            case("Paint Blue"):
                InstructionList.getInstructions().addPaintBlue();
                break;
            case("Paint Green"):
                InstructionList.getInstructions().addPaintGreen();
                break;
            case("Paint Black"):
                InstructionList.getInstructions().addPaintBlack();
                break;
        }
    }
}



class DraggablePanel extends JPanel {
    private List<DraggableLabel> draggedLabels = new ArrayList<>();
    DraggablePanel() {
        setLayout(null);
    }

    public void addDraggableLabel(String text, int x, int y) {
        DraggableLabel label = new DraggableLabel(text);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setBounds(x, y, 75, 35); // Set the size of the label here
        add(label);
    }

    public void addDraggedLabel(DraggableLabel label) {
        draggedLabels.add(label);
    }

    public List<DraggableLabel> getDraggedLabels() {
        return draggedLabels;
    }
}

public class WorkArea extends JPanel{

    public WorkArea() {

        // Visuals initialization
        setLayout(new BorderLayout());
        DraggablePanel dragPanel = new DraggablePanel();
        dragPanel.addDraggableLabel("Step", 650, 25); // Set initial position
        dragPanel.addDraggableLabel("Turn", 650, 75);
        dragPanel.addDraggableLabel("Paint Red", 650, 125);
        dragPanel.addDraggableLabel("Paint Blue", 650, 175);
        dragPanel.addDraggableLabel("Paint Green", 650, 225);
        dragPanel.addDraggableLabel("Paint Black", 650, 275);
        add(dragPanel);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

}
