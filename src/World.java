import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class World extends JPanel {

    private Cell[][] cells;
    private int size;
    private int cellSize = 60;
    private Spider spider;
    
    public World(Level lvl) {
        this.size = lvl.getGridSize();
        this.cells = new Cell[size][size];
        initializeGrid();
        setBackground(Color.BLACK);
        spider = new Spider(lvl.getSpiderX(), lvl.getSpiderY(), lvl.getSpiderDirection());
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

        Level lvl = LevelHelper.getLevels().getLevel();
        this.size = lvl.getGridSize();
        this.cells = new Cell[size][size];
        initializeGrid();
        setBackground(Color.BLACK);
        spider = new Spider(lvl.getSpiderX(), lvl.getSpiderY(), lvl.getSpiderDirection());

        drawGrid(g);
    }
    private void drawSpider(Graphics g, int x, int y, Spider.Direction direction) {
        try {
            String spiderImageFile;
            switch (direction) {
                case NORTH:
                    spiderImageFile = "./images/spider_north.png";
                    break;
                case EAST:
                    spiderImageFile = "./images/spider_east.png";
                    break;
                case SOUTH:
                    spiderImageFile = "./images/spider_south.png";
                    break;
                case WEST:
                    spiderImageFile = "./images/spider_west.png";
                    break;
                default:
                    return;
            }

             //Load the spider image from the file
//            InputStream is = getClass().getResourceAsStream(spiderImageFile);
//            BufferedImage spiderImage = ImageIO.read(is);
            BufferedImage spiderImage = ImageIO.read(new File(spiderImageFile));

            // Calculate new x and y coordinates to center the image within the cell
            int newX = x - spiderImage.getWidth() / 2;
            int newY = y - spiderImage.getHeight() / 2;

            // Draw the spider image at the new coordinates
            g.drawImage(spiderImage, newX, newY, null);
            ImageIcon sp = new ImageIcon(spiderImageFile);
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
//                if (row == 1 && col == 1) {
//                    drawDiamond(g, x + cellSize / 2, y + cellSize / 2, Color.RED);
//                } else if (row == 2 && col == 1) {
//                    drawDiamond(g, x + cellSize / 2, y + cellSize / 2, Color.GREEN);
//                } else if (row == 1 && col == 3) {
//                    drawDiamond(g, x + cellSize / 2, y + cellSize / 2, Color.BLUE);
//                }

//                if (row == spider.getY() - 1 && col == spider.getX() - 1) {
//                    drawSpider(g, x + cellSize / 2, y + cellSize / 2, spider.getDirection());
//                }
                g.setColor(Color.WHITE); // Reset the color for the next grid cell
            }
        }
        ArrayList<Diamond> dimonds = LevelHelper.getLevels().getLevel().getDiamonds();
        for (Diamond dimond: dimonds){
            int x = ((dimond.getX() - 1) * cellSize) + cellSize/ 2;
            int y = ((dimond.getY() - 1) * cellSize) + cellSize/ 2;
            drawDiamond(g, x, y, dimond.getColor());
        }
        int x = ((spider.getX() - 1) * cellSize) + cellSize/ 2;
        int y = ((spider.getY() - 1) * cellSize) + cellSize/ 2;
        drawSpider(g, x, y, spider.getDirection());
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
