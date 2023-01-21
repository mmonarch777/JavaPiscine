

public class Hen implements Runnable {
    private final int counter;

    public Hen(int counter) {
        this.counter = counter;
    }
    @Override
    public void run() {
        for (int i = 0; i < counter; i++) {
            Program.sayHen();
        }
    }
}
