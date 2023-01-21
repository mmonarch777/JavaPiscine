

public class Program {
    public static  boolean flag = false;


    public static synchronized void sayHen() {
        if (!flag) {
            try {
                Program.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Hen");
        flag = false;
        Program.class.notify();
    }

    public static synchronized void sayEgg() {
        if (flag) {
            try {
                Program.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Egg");
        flag = true;
        Program.class.notify();
    }
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.err.println("Wrong argument.");
            System.err.println("Example : --count=50");
            System.exit(-1);
        }

        int counter = 0;
        try {
            counter = Integer.parseInt(args[0].substring(8));
        } catch (NumberFormatException e) {
            System.err.println("error: " + e.getMessage());
        }
        Thread egg = new Thread(new Egg(counter), "Egg");
        Thread hen = new Thread(new Hen(counter), "Hen");

        egg.start();
        hen.start();

        try {
            egg.join();
            hen.join();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
