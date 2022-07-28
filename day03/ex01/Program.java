public class Program implements Runnable{
    private static final int START = 8;
    private static int number;
    private static int key = 0;
    final int count;
    final String string;
    final int status;

    public Program(int count, String string, int status) {
        this.count = count;
        this.string = string;
        this.status = status;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < count) {
            synchronized (Program.class) {
                if (key != status) {
                    System.out.println(string);
                    key = status;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.err.println("Bad argument!");
            System.exit(-1);
        }
        String numStr = args[0].substring(START);
        try {
            number = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            System.out.println("Bad argument!");
            System.exit(-1);
        }


        Thread eggThread = new Thread(new Program(number, "Egg", 0));
        Thread henThread = new Thread(new Program(number, "Hen", 1));

        eggThread.start();
        henThread.start();

        eggThread.join();
        henThread.join();
    }

}
