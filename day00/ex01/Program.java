import java.util.Scanner;

public class Program {
    public static void error() {
        System.out.println("IllegalArgument");
        System.exit(-1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int step = 14;
        int number = sc.nextInt();

        if (number < 2)
            error();

        int i = 2;
        while (i * i <= number)
        {
            if (number % i == 0 && number != i)
            {
                System.out.println("false " + step);
                System.exit(0);
            }
            step++;
            i++;
        }
        System.out.println("true " + step);
    }

}
