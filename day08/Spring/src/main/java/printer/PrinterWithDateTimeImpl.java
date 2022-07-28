package printer;

import java.time.LocalDateTime;
import renderer.Renderer;

public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String text) {
        renderer.printText(LocalDateTime.now() + " " + text);
    }
}
