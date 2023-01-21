

public class Program {
    private static final String var = "Human";
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
        for (int i = 0; i < counter; i++) {
            System.out.println(var);
        }
    }
}
