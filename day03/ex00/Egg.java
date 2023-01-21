

public class Egg implements Runnable {
    private final int counter;

    public Egg(int counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < counter; i++) {
            String var = "Egg";
            System.out.println(var);
        }
    }
}
