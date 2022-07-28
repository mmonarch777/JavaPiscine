import java.util.Scanner;

public class Program {
    private static final int MAX_WEEK = 18;

    public static void checkWeekDay(String week, int weekNum)
    {
        if (!week.equals("Week " + weekNum))
        {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
    }

    public static int getMinGrade(Scanner sc)
    {
        int min = 9;

        System.out.print("-> ");
        for (int i = 0; i < 5; i++)
        {
            int tmp = sc.nextInt();
            if (tmp < 1 || tmp > 9)
            {
                System.out.println("IllegalArgument");
                System.exit(-1);
            }
            if (tmp < min)
                min = tmp;
        }
        sc.nextLine();
        return min;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int weekNum = 1;
        int minGrade;
        long allGrade = 0;
        System.out.print("-> ");
        String week = sc.nextLine();

        while (weekNum <= MAX_WEEK && !week.equals("42"))
        {
            checkWeekDay(week, weekNum);
            minGrade = getMinGrade(sc);
            allGrade = (allGrade * 10) + minGrade;
            System.out.print("-> ");
            week = sc.nextLine();
            weekNum++;
        }
        for (int currentWeek = 1; currentWeek < weekNum; currentWeek++)
        {
            long number = 1;
            for (int i = 1; i < weekNum - currentWeek; i++)
                number *= 10;
            long currentGrade = allGrade / number;
            System.out.print("Week " + currentWeek + " ");
            while (currentGrade > 0)
            {
                System.out.print("=");
                currentGrade--;
            }
            allGrade = allGrade % number;
            System.out.println(">");
        }
    }
}
