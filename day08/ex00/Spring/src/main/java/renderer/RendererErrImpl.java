package renderer;

import preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer{
    private final PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
    @Override
    public void printText(String str) {
        System.err.println(preProcessor.preProcess(str));
    }
}
