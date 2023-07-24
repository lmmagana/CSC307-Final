public class RepeatUntilPaintRedDecorator extends CodeBlockDecorator{
    public RepeatUntilPaintRedDecorator(CodeBlock decoratedCodeBlock) {
        super(decoratedCodeBlock);
    }

    @Override
    public void execute(Spider spider, World grid) {
        while (!"Red".equals(grid.getCell(spider.getX(), spider.getY()).getColor())) {
            decoratedCodeBlock.execute(spider, grid);
        }
    }
}
