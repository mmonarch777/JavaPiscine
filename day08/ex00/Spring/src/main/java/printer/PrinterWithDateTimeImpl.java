package printer;

import renderer.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer{
    private final Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }
    @Override
    public void print(String str) {
        renderer.printText(LocalDateTime.now() + " " + str);
    }
}
