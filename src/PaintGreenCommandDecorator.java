public class PaintGreenCommandDecorator extends CodeBlockDecorator {
    public PaintGreenCommandDecorator(CodeBlock decoratedCodeBlock) {
        super(decoratedCodeBlock);
    }

    @Override
    public void execute(Spider spider, World grid) {
        // Paint the cell green.
        int x = spider.getX();
        int y = spider.getY();
        Cell cell = grid.getCell(x, y);
        cell.setPainted(true);
        cell.setColor("Green");

        decoratedCodeBlock.execute(spider, grid);
    }
}
