import java.util.Scanner;

public class Program {
    public static int count(int number)
    {
        int sum = 0;

        while (number > 0)
        {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    public static boolean check(int number)
    {
        int i = 2;

        while (i * i <= number)
        {
            if (number % i == 0)
                return false;
            i++;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        int amount = 0;

        while (number != 42)
        {
            number = sc.nextInt();
            if (number < 2)
            {
                System.out.println("IllegalArgument");
                continue;
            }
            if (check(count(number)))
                amount++;
        }
        System.out.println("Count of coffee-request - " + amount);
    }
}
