package edu.school21.printer.logic;

import java.awt.image.BufferedImage;

public class Logic {
    public static void converter(BufferedImage image, char white, char black) {
        for(int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (image.getRGB(x, y) == -1) {
                    System.out.print(white);
                } else {
                    System.out.print(black);
                }
            }
            System.out.println();
        }
    }
}
