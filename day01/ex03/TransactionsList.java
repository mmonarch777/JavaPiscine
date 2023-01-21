package day01.ex03;

public interface TransactionsList {
    public void addTransaction(Transaction transaction);
    public void removeTransactionById(String id) throws TransactionNotFoundException;
    public Transaction[] toArrayTrans();
}
