import javax.swing.*;
import java.awt.*;

public class World extends JPanel {

    private Cell[][] cells;
    private int size;
    private int cellSize = 60;

    public World(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
        initializeGrid();
        setBackground(Color.BLACK);
    }

    private void initializeGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public int getGridSize() {
        return size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int x = col * cellSize;
                int y = row * cellSize;
                g.setColor(Color.WHITE);
                g.drawRect(x, y, cellSize, cellSize);
                if (cells[row][col].isPainted()) {
                    g.fillRect(x + 1, y + 1, cellSize - 1, cellSize - 1);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder gridString = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gridString.append(cells[i][j].isPainted() ? "P " : "- ");
            }
            gridString.append("\n");
        }
        return gridString.toString();
    }
}
