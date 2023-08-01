public class StepCommandDecorator extends CodeBlockDecorator {
    public StepCommandDecorator(CodeBlock decoratedCodeBlock) {
        super(decoratedCodeBlock);
    }

    @Override
    public void execute(Spider spider, World grid) {
        // Move the spider one step forward in the current direction.
        // You need to handle the grid boundaries to check for walls.
        int x = spider.getX();
        int y = spider.getY();
        switch (spider.getDirection()) {
            case NORTH:
                if (y > 0) {
                    spider.setY(y - 1);
                }
                break;
            case EAST:
                if (x < grid.getGridSize() - 1) {
                    spider.setX(x + 1);
                }
                break;
            case SOUTH:
                if (y < grid.getGridSize() - 1) {
                    spider.setY(y + 1);
                }
                break;
            case WEST:
                if (x > 0) {
                    spider.setX(x - 1);
                }
                break;
        }
        decoratedCodeBlock.execute(spider, grid);
    }
}
