import java.util.Scanner;

public class Program {
    private static final int MAX_SIZE = 999;

    public static int realSize(int[] amountCurrentSumbol)
    {
        int i = 0;

        while(amountCurrentSumbol.length > i && amountCurrentSumbol[i] != 0)
            i++;
        return i;
    }

    public static void sortirng(char[] allSymbols, int[] amountCurSym)
    {
        char charBuf;
        int intBuf;
        int position;
        int id = -1;
        int size = realSize(amountCurSym);


        while (id < size - 1)
        {
            id++;
            position = -1;
            intBuf = amountCurSym[id];
            charBuf = allSymbols[id];
            for(int i = id; i < size; i++)
            {
                if (intBuf < amountCurSym[i])
                {
                    position = i;
                    intBuf = amountCurSym[i];
                    charBuf = allSymbols[i];
                }
            }
            if (position != -1)
            {
                amountCurSym[position] = amountCurSym[id];
                amountCurSym[id] = intBuf;
                allSymbols[position] = allSymbols[id];
                allSymbols[id] = charBuf;
            }
        }
    }

    public static int checkSymbol(char[] allSymbols, char c)
    {
        for (int i = 0; i < allSymbols.length; i++)
        {
            if (allSymbols[i] == c)
                return (i);
        }
        return (-1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.length() == 0) {
            System.out.println("Input is empty...");
            System.exit(0);
        }
        char[] charInput = input.toCharArray();
        char[] allSymbols = new char[input.length()];
        int[] amountCurSym = new int[input.length()];
        int nb = 0;
        int id = 0;
        for (char c : charInput)
        {
            id = checkSymbol(allSymbols, c);
            if (id == -1)
            {
                allSymbols[nb] = c;
                amountCurSym[nb] += 1;
                nb++;
            }
            else
                amountCurSym[id] += 1;
        }
        sortirng(allSymbols, amountCurSym);
        int size = realSize(amountCurSym);
        for (int i = 0; i < size; i++)
        {
            if (amountCurSym[i] > MAX_SIZE)
                amountCurSym[i] = MAX_SIZE;
        }

        int[] gridAmount = new int[10];
        int maxAmount = amountCurSym[0];
        for (int i = 0; i < gridAmount.length; i++){
            if (i == size)
                break;
            gridAmount[i] = (amountCurSym[i] * 10) / maxAmount;
        }
        size = realSize(gridAmount);
        int height = 11;
        System.out.println();
        while (height > 0)
        {
            for (int i = 0; i < size; i++)
            {
                if (gridAmount[i] + 1 == height)
                    System.out.print(amountCurSym[i]);
                if (gridAmount[i] >= height)
                    System.out.print("#");
                System.out.print("  ");
            }
            System.out.println();
            height--;
        }
        for (int i = 0; i < size; i++)
            System.out.print(allSymbols[i] + "  ");
        System.out.println();
    }
}
