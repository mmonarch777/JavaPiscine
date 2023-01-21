

import java.util.Random;

public class Program {
    public static int getSize(String str) {
        String string = str.substring(12);
        int size = 0;

        try {
            size = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            System.out.println("Bad argument!");
            System.exit(-1);
        }
        if (size < 0) {
            size = size * (-1);
        }
        if (size > 2000000) {
            System.out.println("Bad argument!");
            System.exit(-1);
        }
        return size;
    }

    public static int getCount(String str){
        String string = str.substring(15);
        int count = 0;

        try {
            count = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            System.out.println("Bad argument!");
            System.exit(-1);
        }
        if (count < 0) {
            count = count * (-1);
        }
        if (count == 0) {
            System.out.println("Bad argument!");
            System.exit(-1);
        }
        return count;
    }

    public static void main(String[] args) {
        int arraySize;
        int threadCount;
        int sum = 0;
        int threadsSum = 0;

        if (args.length != 2 || !args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")) {
            System.out.println("Bad argument!");
            System.exit(-1);
        }

        arraySize = getSize(args[0]);
        threadCount = getCount(args[1]);
        if (arraySize <= threadCount) {
            System.out.println("Bad argument!");
            System.exit(-1);
        }
        int[] array = new int[arraySize];
        Random random = new Random();

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(1000);
            sum += array[i];
        }
        System.out.println("Sum: " + sum);
        MyThread[] myThread = new MyThread[threadCount];
        int steps = arraySize/threadCount;
        for (int i = 0; i < threadCount; i++) {
            if ((threadCount - 1) > i) {
                int end = (i + 1) * steps - 1;
                myThread[i] = new MyThread(i, steps, end, array);
            } else {
                myThread[i] = new MyThread(i, steps, arraySize - 1, array);
            }
            myThread[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            try {
                myThread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < threadCount; i++) {
            threadsSum += myThread[i].getThreadSum();
        }
        System.out.println("Sum threads: " + threadsSum);
    }
}
