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
    
    public World(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
        initializeGrid();
        setBackground(Color.BLACK);
        spider = new Spider(1, 3, Spider.Direction.NORTH);
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

    public Spider getSpider() {
        return spider;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }
    private void drawSpider(Graphics g, int x, int y, Spider.Direction direction) {
        try {
            String spiderImageFile;
            switch (direction) {
                case NORTH:
                    spiderImageFile = "CSC307-Final/images/spider_north.png";
                    break;
                case EAST:
                    spiderImageFile = "CSC307-Final/images/spider_east.png";
                    break;
                case SOUTH:
                    spiderImageFile = "CSC307-Final/images/spider_south.png";
                    break;
                case WEST:
                    spiderImageFile = "CSC307-Final/images/spider_west.png";
                    break;
                default:
                    return;
            }

            // Load the spider image from the file
            InputStream is = getClass().getResourceAsStream(spiderImageFile);
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
                if (row == spider.getY() && col == spider.getX()) {
                    drawSpider(g, x + cellSize / 2, y + cellSize / 2, spider.getDirection());
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
