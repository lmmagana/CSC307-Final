import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class World extends JPanel {

    private Cell[][] cells;
    private int size;
    private int cellSize = 60;
    private Spider spider;

    private BufferedImage spiderImage; // Add a BufferedImage to store the spider image

    public World(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
        initializeGrid();
        setBackground(Color.BLACK); // Set the background color to black
        spider = new Spider(2, 2, Spider.Direction.NORTH); 

        try {
            // Load the spider image from the "images" folder
            spiderImage = ImageIO.read(new File("images/spider_north.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        drawSpider(g);
    }
    private void drawSpider(Graphics g) {
        int x = spider.getX() * cellSize;
        int y = spider.getY() * cellSize;

        // Draw the spider image at its current position (north-facing) in the first cell
        if (spider.getX() == 2 && spider.getY() == 4) {
            g.drawImage(spiderImage, x + 10, y + 10, cellSize - 20, cellSize - 20, null);
        }
    }


    private void drawGrid(Graphics g) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int x = col * cellSize;
                int y = row * cellSize;

                g.setColor(Color.WHITE);
                g.drawRect(x, y, cellSize, cellSize);

                if (cells[row][col].isPainted()) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x + 1, y + 1, cellSize - 1, cellSize - 1);
                }

                if (row == 1 && col == 1) { // Check if it's the second grid cell
                    drawDiamond(g, x + cellSize / 2, y + cellSize / 2, Color.RED); // Draw red diamond
                } else if (row == 2 && col == 1) { // Check if it's the third grid cell
                    drawDiamond(g, x + cellSize / 2, y + cellSize / 2, Color.GREEN); // Draw green diamond
                } else if (row == 1 && col == 3) { // Check if it's the fourth grid cell
                    drawDiamond(g, x + cellSize / 2, y + cellSize / 2, Color.BLUE); // Draw green diamond
                }

                g.setColor(Color.WHITE); // Reset the color for the next grid cell
            }
        }
    }

    private void drawDiamond(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        int[] xPoints = {x, x + 5, x, x - 5}; // x-coordinates for diamond
        int[] yPoints = {y - 5, y, y + 5, y}; // y-coordinates for diamond
        g.fillPolygon(xPoints, yPoints, 4); // Draw the diamond
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
