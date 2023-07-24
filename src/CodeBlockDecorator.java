public class CodeBlockDecorator implements CodeBlock{
    protected CodeBlock decoratedCodeBlock;

    public CodeBlockDecorator(CodeBlock decoratedCodeBlock) {
        this.decoratedCodeBlock = decoratedCodeBlock;
    }

    @Override
    public void execute(Spider spider, World grid) {

    }
}
