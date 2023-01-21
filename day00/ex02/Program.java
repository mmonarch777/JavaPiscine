package day00.ex02;

import java.util.Scanner;

public class Program {
    final static int FINISH = 42;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int count = 0;
        double number = 0;
        while (number != FINISH) {
            number = s.nextDouble();
            if (number < 2) {
                System.err.println("IllegalArgument");
                continue;
            }
            if (isPrimeNumb(number)) {
                count++;
            }
        }
        System.out.println("Count of coffee-request - " + count);
    }
    static boolean isPrimeNumb(double count) {
        int number = summ(count);
        int i = 2;
        while (i * i < number) {
            if (number % i == 0 && i != number) {
                return false;
            }
            i++;
        }
        return true;
    }

    static int summ(double number) {
        int summ = 0;
        while (number > 0) {
            summ += number % 10;
            number = number / 10;
        }
        return summ;
    }
}
