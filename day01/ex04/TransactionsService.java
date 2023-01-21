package day01.ex04;

import java.util.UUID;

public class TransactionsService {
    private UsersList usersList;

    public TransactionsService() {
        this.usersList = UsersArrayList.getUserList();
    }

    public UsersList getUsersList() {
        return this.usersList;
    }

    public void addUser(User user) {
        getUsersList().addUser(user);
    }

    public int getUserBalance(UUID id) {
        return getUsersList().getUserById(id).getBalance();
    }

    public void execTransaction(UUID senderId, UUID recipientId, Integer amount) {
        User sender = usersList.getUserById(senderId);
        User recipient = usersList.getUserById(recipientId);

        if (amount < 0 || sender.getBalance() < amount || senderId.equals(recipientId)) {
            throw new IllegalTransactionException("Wrong transaction");
        }

        Transaction t1 = new Transaction(sender, recipient, amount, Transaction.Category.DEBIT);
        Transaction t2 = new Transaction(sender, recipient, -amount, Transaction.Category.CREDIT);
        t1.setId(t2.getId());

        recipient.getTransactionsList().addTransaction(t1);
        sender.getTransactionsList().addTransaction(t2);

        recipient.setBalance(recipient.getBalance() + amount);
        sender.setBalance(sender.getBalance() - amount);
    }

    public Transaction[] getUserTransactions(User user) {
        return user.getTransactionsList().toArrayTrans();
    }

    public void removeUserTransactionById(UUID transId, UUID userID) {
        usersList.getUserById(userID).getTransactionsList().removeTransactionById(transId);
    }

    public Transaction[] checkTransactions() {
        TransactionsLinkedList list = new TransactionsLinkedList();
        for (int i = 0; i < usersList.getAmountUsers(); i++) {
            User user = usersList.getUserByIndex(i);
            Transaction[] tmp = user.getTransactionsList().toArrayTrans();
            for (Transaction transaction : tmp) {
                list.addTransaction(transaction);
            }
        }
        TransactionsLinkedList res = new TransactionsLinkedList();
        Transaction[] array = list.toArrayTrans();

        for (int i = 0; i < array.length; i++) {
            int count = 0;
            for(Transaction transaction : array) {
                if (array[i].getId() == transaction.getId()) {
                    count++;
                }
            }
            if (count != 2) {
                res.addTransaction(array[i]);
            }
        }
        return res.toArrayTrans();
    }
}
