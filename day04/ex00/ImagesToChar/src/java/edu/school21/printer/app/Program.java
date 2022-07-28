package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program {
    private static char one;
    private static char two;
    private static String imgPath;

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Error!");
            System.exit(-1);
        }
        one = args[0].charAt(0);
        two = args[1].charAt(0);
        imgPath = args[2];

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            System.err.println("Error!");
            System.exit(-1);
        }
        Logic.convert(img, one, two);
    }
}