package printer;

import renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    private Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
//        this.prefix = "def ";
        this.prefix = null + " ";
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix + " ";
    }

    @Override
    public void print(String text) {
        renderer.printText(prefix + text);
    }
}
