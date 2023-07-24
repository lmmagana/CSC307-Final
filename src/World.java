import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
        // spider = new Spider(0, 2, Spider.Direction.NORTH); 

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
        // drawSpider(g);
    }
    private void drawSpider(Graphics g, int x, int y) {
        try {
            // Load the spider image from the file
            InputStream is = getClass().getResourceAsStream("CSC307-Final/images/spider_north.png");
            BufferedImage spiderImage = ImageIO.read(is);

            // Calculate new x and y coordinates to center the image within the cell
            int newX = x - spiderImage.getWidth() / 2;
            int newY = y - spiderImage.getHeight() / 2;

            // Draw the spider image at the new coordinates
            g.drawImage(spiderImage, newX, newY, null);
        } catch (IOException e) {
            e.printStackTrace();
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

                if (row == 1 && col == 1) {
                    drawDiamond(g, x + cellSize / 2, y + cellSize / 2, Color.RED);
                } else if (row == 2 && col == 1) {
                    drawDiamond(g, x + cellSize / 2, y + cellSize / 2, Color.GREEN);
                } else if (row == 1 && col == 3) {
                    drawDiamond(g, x + cellSize / 2, y + cellSize / 2, Color.BLUE);
                }
                else if (row == 3 && col == 1) {
                    drawSpider(g, x + cellSize / 2, y + cellSize / 2);
                }
                g.setColor(Color.WHITE); // Reset the color for the next grid cell
            }
        }
    }

    private void drawDiamond(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        int[] xPoints = {x, x + 5, x, x - 5}; // x-coordinates for diamond
        int[] yPoints = {y - 5, y, y + 5, y}; // y-coordinates for diamond
        g.fillPolygon(xPoints, yPoints, 4);
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
