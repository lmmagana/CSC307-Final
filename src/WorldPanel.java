import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorldPanel extends JPanel implements ActionListener, ChangeListener {

  private int gridSize = 5; // Set the size of the grid (5x5)

  public WorldPanel() {
    // left side
    // panel with the world buttons
    // JLabel gridPlaceholder = new JLabel("Grid goes here.");
    // add(gridPlaceholder);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawGrid(g);
  }

  private void drawGrid(Graphics g) {
    int cellSize = 60;
    int xOffset = 20;
    g.setColor(Color.WHITE);
    // Draw the vertical grid lines
    for (int col = 0; col <= gridSize; col++) {
      int x = xOffset + col * cellSize;
      g.drawLine(x, 0, x, gridSize * cellSize);
    }
    // Draw the horizontal grid lines
    for (int row = 0; row <= gridSize; row++) {
      int y = row * cellSize;
      g.drawLine(xOffset, y, gridSize * cellSize + xOffset, y);
    }
    g.setColor(Color.BLACK);
    // Fill each grid cell with black color
    for (int row = 0; row < gridSize; row++) {
      for (int col = 0; col < gridSize; col++) {
        int x = xOffset + col * cellSize + 1;
        int y = row * cellSize + 1;
        g.fillRect(x, y, cellSize - 1, cellSize - 1);
      }
    }
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