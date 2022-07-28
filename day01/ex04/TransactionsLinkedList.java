import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private List start;
    private List end;
    private Integer amount;

    public TransactionsLinkedList() {
        this.start = new List(null);
        this.end = new List(null);

        start.next = end;
        end.prev = start;
        this.amount = 0;
    }

    @Override
    public void add(Transaction transaction) {
        (new List(transaction)).addNew(end);
        amount++;
    }

    @Override
    public void removeById(UUID id) throws TransactionNotFoundException {
        List current = start.next();

        while (current != end) {
            if (current.getTransaction().getIdentifier().equals(id)) {
                current.delList();
                amount--;
                return;
            }
            current = current.next;
        }
        throw new TransactionNotFoundException("Transaction with this ID don't exist..");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transactions = new Transaction[amount];

        List current = start.next();
        for (int i = 0; i < amount; i++) {
            transactions[i] = current.getTransaction();
            current = current.next();
        }
        return transactions;
    }

    private static class List {
        private Transaction transaction;
        private List next;
        private List prev;

        public List(Transaction transaction) {
            this.transaction = transaction;
        }

        public void delList() {
            next.prev =prev;
            prev.next = next;
            next = null;
            prev = null;
        }

        public void addNew(List end)
        {
            prev = end.prev;
            prev.next = this;
            end.prev = this;
            next = end;
        }

        public Transaction getTransaction() {
            return transaction;
        }
        public List next() {
            return next;
        }
    }

}

class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String str) {
        super(str);
    }
}