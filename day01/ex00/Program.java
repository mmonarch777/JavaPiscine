package day01.ex00;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User u1 = new User("Mikle", 1000);
        User u2 = new User("Yr", 10);
        Transaction t = new Transaction(u1, u2, -100, Transaction.Category.DEBIT);

        System.out.println(u1.toString());
        System.out.println(u2.toString());
        System.out.println(t);

    }
}
