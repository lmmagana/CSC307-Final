import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.http.HttpResponse;

class DraggableStepLabel extends JLabel {
    private int mousePressedX;
    private int mousePressedY;

    DraggableStepLabel(ImageIcon text) {
        super(text);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mousePressedX = e.getX();
                mousePressedY = e.getY();
                DraggablePanel parentPanel = (DraggablePanel) getParent();

                ImageIcon imageIcon = new ImageIcon("./images/stepsmall.png");
                parentPanel.addDraggableStepLabel(imageIcon, 680, 25); // Create a new label at the initial position
                InstructionList.getInstance().addInstuction("Step");

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = getLocation().x + e.getX() - mousePressedX;
                int y = getLocation().y + e.getY() - mousePressedY;
                setLocation(x, y);
                getParent().repaint();
            }
        });
    }
}

class DraggableTurnLabel extends JLabel {
    private int mousePressedX;
    private int mousePressedY;

    DraggableTurnLabel(ImageIcon text) {
        super(text);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mousePressedX = e.getX();
                mousePressedY = e.getY();
                DraggablePanel parentPanel = (DraggablePanel) getParent();

                ImageIcon imageIcon = new ImageIcon("./images/turnsmall.png");

                parentPanel.addDraggableTurnLabel(imageIcon, 680, 75); // Create a new label at the initial position
                InstructionList.getInstance().addInstuction("Turn");

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = getLocation().x + e.getX() - mousePressedX;
                int y = getLocation().y + e.getY() - mousePressedY;
                setLocation(x, y);
                getParent().repaint();
            }
        });
    }
}

class DraggableDropdown extends JComboBox<String> { // Use JComboBox instead of JLabel
    private int mousePressedX;
    private int mousePressedY;

    DraggableDropdown(String[] items) { // Pass an array of items for the dropdown menu
        super(items);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mousePressedX = e.getX();
                mousePressedY = e.getY();
                DraggablePanel parentPanel = (DraggablePanel) getParent();

                // Pass the selected item's index to the parent panel instead of an ImageIcon
                int selectedIndex = getSelectedIndex();
                parentPanel.addDraggableDropdown(selectedIndex, 680, 125); // Create a new label at the initial position

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = getLocation().x + e.getX() - mousePressedX;
                int y = getLocation().y + e.getY() - mousePressedY;
                setLocation(x, y);
                getParent().repaint();
            }
        });
    }
}

class DraggablePanel extends JPanel {
    DraggablePanel() {
        setLayout(null);
    }

    public void addDraggableStepLabel(ImageIcon icon, int x, int y) {
        DraggableStepLabel label = new DraggableStepLabel(icon);
        label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight()); // Set the size of the label here
        add(label);
    }

    public void addDraggableTurnLabel(ImageIcon icon, int x, int y) {
        DraggableTurnLabel label = new DraggableTurnLabel(icon);
        label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight()); // Set the size of the label here
        add(label);
    }

    public void addDraggableDropdown(int selectedIndex, int x, int y) { // Receive the selected index
        String[] items = { "Red", "Blue", "Green", "Black" }; // Replace this array with your specific items
        DraggableDropdown dropdown = new DraggableDropdown(items);
        dropdown.setSelectedIndex(selectedIndex); // Set the selected index
        dropdown.setBounds(x, y, 90, 30); // Set the size of the dropdown menu here
        add(dropdown);
    }
}

public class WorkArea extends JPanel{

    private JLabel blockSpacePlaceholder;
    private Point offset;

    public WorkArea() {

        // Visuals initialization
        setLayout(new BorderLayout());

        /*
        JPanel workAreaSpace = new JPanel();
        workAreaSpace.setBackground(Color.WHITE);
        add(workAreaSpace, BorderLayout.CENTER);

        JLabel workAreaPlaceholder = new JLabel("WorkArea goes here.");
        workAreaSpace.add(workAreaPlaceholder);

        JPanel blockSpace = new JPanel();
        blockSpace.setBackground(Color.GRAY);
        add(blockSpace, BorderLayout.EAST);

        blockSpacePlaceholder = new JLabel("blocks go here.");
        blockSpace.add(blockSpacePlaceholder);
        */

        // Functionality
        //addMouseListener(this);
        //addMouseMotionListener(this);


        ImageIcon stepIcon = new ImageIcon("./images/stepsmall.png");
        ImageIcon turnIcon = new ImageIcon("./images/turnsmall.png");

        DraggablePanel dragPanel = new DraggablePanel();
        dragPanel.addDraggableStepLabel(stepIcon, 680, 25); // Set initial position to (0, 0)
        dragPanel.addDraggableTurnLabel(turnIcon, 680, 75);
        dragPanel.addDraggableDropdown(0, 660, 125);
        add(dragPanel);


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

}
