package printer;

import renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer{
    private final Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
        this.prefix = "null ";
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix + " ";
    }
    @Override
    public void print(String str) {
        renderer.printText(prefix + str);
    }
}
