package edu.school21.printer.logic;

import java.awt.image.BufferedImage;

public class Logic {
    public static void convert(BufferedImage img, char one, char two) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x,y) == -1) {
                    System.out.print(one);
                } else {
                    System.out.print(two);
                }
            }
            System.out.println();
        }
    }
}