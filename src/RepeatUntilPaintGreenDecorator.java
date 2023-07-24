public class RepeatUntilPaintGreenDecorator extends CodeBlockDecorator{
    public RepeatUntilPaintGreenDecorator(CodeBlock decoratedCodeBlock) {
        super(decoratedCodeBlock);
    }

    @Override
    public void execute(Spider spider, World grid) {
        while (!"Green".equals(grid.getCell(spider.getX(), spider.getY()).getColor())) {
            decoratedCodeBlock.execute(spider, grid);
        }
    }
}
