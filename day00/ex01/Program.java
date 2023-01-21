package day00.ex01;


import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        try {
            double num = s.nextDouble();
            if (num <= 1) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            primeNumber(num);
        } catch (Exception exception) {
            System.err.println("It'll work out next time...");
        }
    }
    static void primeNumber(double num) {
        int i = 2;
        while (i * i <= num) {
            if (num % i == 0 && num != i) {
                System.out.println("false " + (i - 1));
                System.exit(0);
            }
            i++;
        }
        System.out.println("true " + (i - 1));
    }
}
