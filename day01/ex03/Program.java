package day01.ex03;

public class Program {
    public static void main(String[] args) {
        User u1 = new User("Mi", 500);
        User u2 = new User("Ur", 500);
        Transaction t1 = new Transaction(u1, u2, 100, Transaction.Category.DEBIT);
        Transaction t2 = new Transaction(u2, u1, -50, Transaction.Category.CREDIT);

        u1.getTransactionsList().addTransaction(t1);
        System.out.println(u1.getTransactionsList().getSize());
        u1.getTransactionsList().printList();

        u1.getTransactionsList().addTransaction(t2);
        System.out.println(u1.getTransactionsList().getSize());
        u1.getTransactionsList().printList();

        System.out.println("============================================");

        Transaction[] mass = u1.getTransactionsList().toArrayTrans();
        for (Transaction tr : mass) {
            System.out.println(tr.toString());
        }

        System.out.println("============================================");


        u1.getTransactionsList().removeTransactionById(t1.getId().toString());
        System.out.println(u1.getTransactionsList().getSize());
        u1.getTransactionsList().printList();

//        u1.getTransactionsList().removeTransactionById(t1.getId().toString());
//        System.out.println(u1.getTransactionsList().getSize());
//        u1.getTransactionsList().printList();

        u1.getTransactionsList().removeTransactionById(t2.getId().toString());
        System.out.println(u1.getTransactionsList().getSize());
        u1.getTransactionsList().printList();

        u1.getTransactionsList().removeTransactionById(t2.getId().toString());
        u1.getTransactionsList().printList();


        System.out.println("============================================");
        Transaction[] tmp = u2.getTransactionsList().toArrayTrans();
        for (Transaction t : tmp) {
            System.out.println(t.toString());
        }
    }
}
