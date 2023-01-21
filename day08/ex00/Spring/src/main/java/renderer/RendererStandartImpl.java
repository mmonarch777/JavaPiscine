package renderer;

import preprocessor.PreProcessor;

public class RendererStandartImpl  implements Renderer{
    private final PreProcessor preProcessor;

    public RendererStandartImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
    @Override
    public void printText(String str) {
        System.out.println(preProcessor.preProcess(str));
    }
}
