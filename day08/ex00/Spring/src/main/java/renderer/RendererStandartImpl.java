package renderer;

import preprocessor.PreProcessor;

public class RendererStandartImpl implements Renderer{
    public PreProcessor preProcessor;

    public RendererStandartImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void printText(String text) {
        System.out.println(preProcessor.preProcess(text));
    }
}
