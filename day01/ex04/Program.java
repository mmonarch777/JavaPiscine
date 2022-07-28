import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("user1", 10000);
        User user2 = new User("user2", 22222);
        User user3 = new User("user3", 33333);

        TransactionsList list = new TransactionsLinkedList();
        user1.setTransactionsList(list);
        user2.setTransactionsList(list);
        user3.setTransactionsList(list);

        Transaction trans1 = Transaction.makeTr(user1, user2, Transaction.TransactionType.CREDIT, -1000);
        Transaction trans2 = Transaction.makeTr(user2, user3, Transaction.TransactionType.DEBIT, 4342);
        Transaction trans3 = Transaction.makeTr(user3, user1, Transaction.TransactionType.DEBIT, 500);
        Transaction trans4 = Transaction.makeTr(user2, user1, Transaction.TransactionType.DEBIT, 100);


        System.out.println(trans1.getIdentifier());
        list.add(trans2);
        System.out.println(trans2.getIdentifier());
        list.add(trans3);
        System.out.println(trans3.getIdentifier());
        list.add(trans4);
        System.out.println(trans4.getIdentifier());

        for (Transaction array : list.toArray()) {
            System.out.println(array);
        }
        System.out.println("---------------------------");
        list.removeById(trans3.getIdentifier());
        System.out.println("---------------------------");
        for (Transaction array : list.toArray()) {
            System.out.println(array);
        }
        System.out.println("---------------------------");
        UUID x = UUID.randomUUID();
        list.removeById(x);
        list.removeById(trans1.getIdentifier());
    }
}
