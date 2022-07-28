package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.IOException;

public class Program {
    private static char one;
    private static char two;
    private static URL path;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Error!");
            System.exit(-1);
        }

        one = args[0].charAt(0);
        two = args[1].charAt(0);
        path = Program.class.getResource("/resources/it.bmp");
        BufferedImage img = null;

        try {
            assert path != null;
            img = ImageIO.read(path);
        } catch (IOException e) {
            System.err.println("Error!");
            System.exit(-1);
        }
        Logic.convert(img, one, two);
    }
}