package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Error : arguments");
            System.exit(-1);
        }
        char white = args[0].charAt(0);
        char black = args[1].charAt(0);
        String imgPath = args[2];

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            System.err.printf("Error : %s", e.getMessage());
            System.exit(-1);
        }
        Logic.converter(img, white, black);
    }
}
