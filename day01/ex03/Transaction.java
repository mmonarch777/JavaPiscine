package day01.ex03;

import java.util.UUID;

public class Transaction {
    enum Category {
        DEBIT, CREDIT;
    }

    private final UUID id;
    private User recipient;
    private User sender;
    private final Transaction.Category category;
    private Integer amount;

    private Transaction next = null;
    private Transaction prev = null;

    public Transaction(User send, User rec, Integer amount, Transaction.Category category) {
        if (send == null || rec == null) {
            System.err.println("Wrong User");
            System.exit(-1);
        }
        this.category = category;
        setAmount(amount);
        id = UUID.randomUUID();
        this.recipient = rec;
        this.sender = send;

    }
    public void setAmount(Integer amount) {
        if (category == Transaction.Category.CREDIT) {
            this.amount = amount > 0 ? 0 : amount;
        } else if (category == Transaction.Category.DEBIT) {
            this.amount = amount < 0 ? 0: amount;
        } else {
            System.err.println("Wrong category");
            System.exit(-1);
        }
    }

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Transaction.Category getCategory() {
        return category;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setNext(Transaction transaction) {
        this.next = transaction;
    }
    public Transaction getNext() {
        return this.next;
    }
    public void setPrev(Transaction transaction) {
        this.prev = transaction;
    }
    public Transaction getPrev() {
        return this.prev;
    }

    @Override
    public String toString() {
        return ("\nid         : " + id +
                "\nsender     : " + sender.getName() +
                "\nrecipient  : " + recipient.getName() +
                "\ncategory   : " + category.toString() +
                "\namount     : " + amount);
    }
}
