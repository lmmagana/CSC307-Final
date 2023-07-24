public class PaintRedCommandDecorator extends CodeBlockDecorator {
    public PaintRedCommandDecorator(CodeBlock decoratedCodeBlock) {
        super(decoratedCodeBlock);
    }

    @Override
    public void execute(Spider spider, World grid) {
        // Paint the cell red.
        int x = spider.getX();
        int y = spider.getY();
        Cell cell = grid.getCell(x, y);
        cell.setPainted(true);
        cell.setColor("Red");

        decoratedCodeBlock.execute(spider, grid);
    }
}
