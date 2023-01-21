package day01.ex04;

public class Program {
    public static void main(String[] args) {
        User u1 = new User("one", 200);
        User u2 = new User("two", 200);
        User u3 = new User("three", 200);

        TransactionsService service = new TransactionsService();
        service.addUser(u1);
        service.addUser(u1);
        service.addUser(u2);
        service.addUser(u3);
        System.out.println(service.getUsersList().getAmountUsers());

        service.execTransaction(u1.getId(), u2.getId(), 50);
        System.out.println(u1.getBalance());
        System.out.println(u2.getBalance());

        System.out.println(u1.getTransactionsList().toArrayTrans()[0].toString());
        System.out.println(u2.getTransactionsList().toArrayTrans()[0].toString());
        System.out.println("========================================================");
        service.execTransaction(u2.getId(), u1.getId(), 50);
        System.out.println(u1.getBalance());
        System.out.println(u2.getBalance());

        System.out.println(u1.getTransactionsList().toArrayTrans()[1].toString());
        System.out.println(u2.getTransactionsList().toArrayTrans()[1].toString());

        System.out.println("========================================================");

        for (Transaction tr : service.checkTransactions()) {
            System.out.println(tr.toString());
        }

        System.out.println("========================================================");

        u1.getTransactionsList().addTransaction(new Transaction(u2, u3, 100, Transaction.Category.DEBIT));
        for (Transaction tr : service.checkTransactions()) {
            System.out.println(tr.toString());
        }

        System.out.println("========================================================");
        u1.setBalance(-100);
        System.out.println(service.getUserBalance(u1.getId()));
        service.execTransaction(u1.getId(), u2.getId(), 100);
    }
}
