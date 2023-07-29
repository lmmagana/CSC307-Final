import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

public class WorldPanel extends JPanel implements Observer {

    private int gridSize;
    private final int cellSize = 60;
    private final int xOffset = 20;
    private final int yOffset = 10;
    private World world;

    public WorldPanel() {
        setLayout(null);
        gridSize = LevelHelper.getLevels().getLevel().getGridSize();
        world = new World(LevelHelper.getLevels().getLevel());
        world.setBounds(xOffset, yOffset, gridSize * cellSize, gridSize * cellSize);
        add(world);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gridSize = LevelHelper.getLevels().getLevel().getGridSize();
        world.setBounds(xOffset, yOffset, gridSize * cellSize, gridSize * cellSize);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
