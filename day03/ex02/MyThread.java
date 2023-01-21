

public class MyThread extends Thread {
    private int start;
    private int end;
    private int steps;
    private int[] array;
    private long threadSum;

    public MyThread(int start, int steps, int end, int[] array) {
        this.start = start;
        this.end = end;
        this.steps = steps;
        this.array = array;
        threadSum = 0;
    }

    public long getThreadSum() {
        return threadSum;
    }

    @Override
    public void run() {
        for (int i = start * steps; i <= end; i++) {
            threadSum += array[i];
        }
        System.out.println("Thread " + (start + 1) + ": from "
                            + (start * steps) + " to " + end
                            + " sum is " + threadSum);
    }
}
