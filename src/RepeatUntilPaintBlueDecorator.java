public class RepeatUntilPaintBlueDecorator extends CodeBlockDecorator{
    public RepeatUntilPaintBlueDecorator(CodeBlock decoratedCodeBlock) {
        super(decoratedCodeBlock);
    }

    @Override
    public void execute(Spider spider, World grid) {
        while (!"Blue".equals(grid.getCell(spider.getX(), spider.getY()).getColor())) {
            decoratedCodeBlock.execute(spider, grid);
        }
    }
}

