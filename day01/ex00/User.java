public class User {
    private final Integer identifier;
    private final String  name;
    private Integer balance;

    public User(Integer id, String name, Integer balance)
    {
        this.identifier = id;
        this.name = name;
        if (balance > 0) {
            this.balance = balance;
        }
        else {
            this.balance = 0;
            System.out.println(name + ": your balance is 0.");
        }
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
}
