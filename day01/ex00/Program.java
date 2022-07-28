public class Program {
    public static void main(String[] args) {
        User mikle = new User(1, "Mikle", 10000);
        User yrik = new User(2, "Yrick", 1000);
        User user = new User(3, "User", -1);

        Transaction t1 = Transaction.makeTr(yrik, mikle, Transaction.TransactionType.DEBIT, 1000);
        System.out.println(t1.getIdentifier());
        System.out.println(t1.getTransactionType());
        System.out.println("Mikle balance: " + mikle.getBalance());
        System.out.println("Yrik balance: " + yrik.getBalance());
        Transaction t2 = Transaction.makeTr(yrik, mikle, Transaction.TransactionType.CREDIT, -1000);
        System.out.println(t2.getIdentifier());
        System.out.println(t2.getTransactionType());
        System.out.println("Mikle balance: " + mikle.getBalance());
        System.out.println("Yrik balance: " + yrik.getBalance());
        Transaction t3 = Transaction.makeTr(yrik, mikle, Transaction.TransactionType.DEBIT, -1000);
        Transaction t4 = Transaction.makeTr(yrik, mikle, Transaction.TransactionType.CREDIT, +1000);
        Transaction t5 = Transaction.makeTr(yrik, mikle, Transaction.TransactionType.CREDIT, -10000);
    }
}
