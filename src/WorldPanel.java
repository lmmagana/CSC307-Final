import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorldPanel extends JPanel implements ActionListener, ChangeListener {

    public WorldPanel() {
        setLayout(null);

        int gridSize = 5;
        int cellSize = 60;
        int xOffset = 20;
        int yOffset = 10;

        World world = new World(gridSize);
        world.setBounds(xOffset, yOffset, gridSize * cellSize, gridSize * cellSize);
        add(world);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks or other actions if needed
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // Handle state changes of components if needed
    }
}
