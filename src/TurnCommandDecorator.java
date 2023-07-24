public class TurnCommandDecorator extends CodeBlockDecorator {
    private Spider.TurnDirection turnDirection;

    public TurnCommandDecorator(CodeBlock decoratedCodeBlock, Spider.TurnDirection turnDirection) {
        super(decoratedCodeBlock);
        this.turnDirection = turnDirection;
    }

    @Override
    public void execute(Spider spider, World grid) {
        switch (turnDirection) {
            case LEFT:
                spider.turnLeft();
                break;
            case RIGHT:
                spider.turnRight();
                break;
        }

        decoratedCodeBlock.execute(spider, grid);
    }
}
