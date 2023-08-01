import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Level lvl = LevelHelper.getLevels().getLevel();
        Run run = Run.getInstance();
        this.size = lvl.getGridSize();
        this.cells = new Cell[size][size];
        initializeGrid();
        setBackground(Color.BLACK);
        if(run.getSpiderDirection() == null) spider = new Spider(lvl.getSpiderX(), lvl.getSpiderY(), lvl.getSpiderDirection());
        else{ spider = new Spider(run.getSpiderX(), run.getSpiderY(), run.getSpiderDirection()); }

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

        drawCells(g);

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
                g.setColor(Color.WHITE); // Reset the color for the next grid cell
            }
        }
        ArrayList<Diamond> dimonds = LevelHelper.getLevels().getLevel().getDiamonds();
        for (Diamond dimond : dimonds) {
            int x = ((dimond.getX() - 1) * cellSize) + cellSize / 2;
            int y = ((dimond.getY() - 1) * cellSize) + cellSize / 2;
            drawDiamond(g, x, y, dimond.getColor());
        }

        int x = ((spider.getX() - 1) * cellSize) + cellSize / 2;
        int y = ((spider.getY() - 1) * cellSize) + cellSize / 2;
        drawSpider(g, x, y, spider.getDirection());
    }

    private void drawDiamond(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        int[] xPoints = {x, x + 7, x, x - 7}; // x-coordinates for diamond
        int[] yPoints = {y - 9, y, y + 9, y}; // y-coordinates for diamond
        g.fillPolygon(xPoints, yPoints, 4);
    }

    private void drawCells(Graphics g){
        Color[] colors = Run.getInstance().getGrid();
        if(colors == null) return;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int x = col * cellSize;
                int y = row * cellSize;
                Color cellColor = colors[row * size + col];
                g.setColor(cellColor);
                g.fillRect(x, y, cellSize, cellSize);
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
