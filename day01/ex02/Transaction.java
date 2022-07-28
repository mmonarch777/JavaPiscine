import java.util.UUID;

public class Transaction {
    static enum TransactionType {
        DEBIT,
        CREDIT
    }

    private final String identifier = UUID.randomUUID().toString();
    private final User recipient;
    private final User sender;
    private final TransactionType transactionType;
    private final Integer transferAmount;

    Transaction (User recipient, User sender, TransactionType type, Integer transferAmount) {
        this.recipient = recipient;
        this.sender = sender;
        this.transactionType = type;
        this.transferAmount = transferAmount;
    }

    public static Transaction makeTr(User recipient, User sender, TransactionType type, Integer transferAmount)
    {
        if (type == TransactionType.CREDIT) {
            if (transferAmount < 0) {
                if (sender.getBalance() >= (transferAmount * (-1))) {
                    sender.setBalance(transferAmount);
                    recipient.setBalance(transferAmount * (-1));
                    return new Transaction(recipient, sender, type, transferAmount);
                } else {
                    System.out.println(sender.getName() +": not enough money!");
                    return null;
                }
            }
        } else {
            if (transferAmount > 0) {
                if (sender.getBalance() >= transferAmount) {
                    sender.setBalance(transferAmount * (-1));
                    recipient.setBalance(transferAmount);
                    return new Transaction(recipient, sender, type, transferAmount);
                } else {
                    System.out.println(sender.getName() +": not enough money!");
                    return null;
                }
            }
        }
        System.out.println("Wrong transfer amount!");
        return null;
    }

    public String getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public  Integer getTransferAmount() {
        return transferAmount;
    }
}
