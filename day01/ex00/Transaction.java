package day01.ex00;


import java.util.UUID;

public class Transaction {
    enum Category {
        DEBIT, CREDIT;
    }

    private final UUID identifier;
    private User recipient;
    private User sender;
    private final Category category;
    private Integer amount;

    public Transaction(User send, User rec, Integer amount, Category category) {
        if (send == null || rec == null) {
            System.err.println("Wrong User");
            System.exit(-1);
        }
        this.category = category;
        setAmount(amount);
        identifier = UUID.randomUUID();
        this.recipient = rec;
        this.sender = send;

    }
    private void setAmount(Integer amount) {
        if (category == Category.CREDIT) {
            this.amount = amount > 0 ? 0 : amount;
        } else if (category == Category.DEBIT) {
            this.amount = amount < 0 ? 0: amount;
        } else {
            System.err.println("Wrong category");
            System.exit(-1);
        }
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return ("\nidentifier : " + identifier +
                "\nsender     : " + sender.getName() +
                "\nrecipient  : " + recipient.getName() +
                "\ncategory   : " + category.toString() +
                "\namount     : " + amount);
    }
}

