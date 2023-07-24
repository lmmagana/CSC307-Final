public class PaintBlueCommandDecorator extends CodeBlockDecorator {
    public PaintBlueCommandDecorator(CodeBlock decoratedCodeBlock) {
        super(decoratedCodeBlock);
    }

    @Override
    public void execute(Spider spider, World grid) {
        // Paint the cell blue.
        int x = spider.getX();
        int y = spider.getY();
        Cell cell = grid.getCell(x, y);
        cell.setPainted(true);
        cell.setColor("Blue");

        decoratedCodeBlock.execute(spider, grid);
    }
}
