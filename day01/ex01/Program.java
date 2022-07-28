public class Program {
    public static void main(String[] args) {
        User u1 = new User("Mikle", 10000);
        System.out.print(u1.getName() + ": identifier is ");
        System.out.println(u1.getIdentifier());
        User u2 = new User("Yrik", 1000);
        System.out.print(u2.getName() + ": identifier is ");
        System.out.println(u2.getIdentifier());
    }
}
