public class RepeatUntilWallDecorator extends CodeBlockDecorator {

    public RepeatUntilWallDecorator(CodeBlock decoratedCodeBlock) {
        super(decoratedCodeBlock);
    }

    @Override
    public void execute(Spider spider, World grid) {
        while (true) {
            if(spider.getX() != grid.getGridSize()+1){
                break;
            } else if (spider.getY() != grid.getGridSize()+1) {
                break;
            }else{
                decoratedCodeBlock.execute(spider, grid);
            }
        }
    }
}
