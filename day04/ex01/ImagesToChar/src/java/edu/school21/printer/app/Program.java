package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Error : arguments");
            System.exit(-1);
        }
        char white = args[0].charAt(0);
        char black = args[1].charAt(0);
        URL path = Program.class.getResource("/resources/it.bmp");
        System.out.println(System.getenv("PWD"));
        if (path == null) {
            System.err.println("Error : path file!");
            System.exit(-1);
        }

        BufferedImage image = null;
        try {
            image = ImageIO.read(path);
            Logic.converted(image, white, black);
        } catch (IOException e) {
            System.err.printf("Error : %s", e.getMessage());
        }
    }
}
