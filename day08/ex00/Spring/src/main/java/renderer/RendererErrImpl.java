package renderer;

import preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer{
    public PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void printText(String text) {
        System.err.println(preProcessor.preProcess(text));
    }
}
