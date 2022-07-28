public class User {
    private final Integer identifier;
    private final String  name;
    private Integer balance;
    private TransactionsList list;

    public User(String name, Integer balance)
    {
        this.name = name;
        if (balance > 0) {
            this.balance = balance;
        }
        else {
            this.balance = 0;
            System.out.println(name + ": your balance is 0.");
        }
        this.identifier = UserIdsGenerator.getInstance().generateId();
    }

    public void setBalance(Integer balance) {
        this.balance += balance;
    }

    public int getBalance() {
        return balance;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public void setTransactionsList(TransactionsList list) {
        this.list = list;
    }
    public TransactionsList getTransactionsList() {
        return this.list;
    }


}

