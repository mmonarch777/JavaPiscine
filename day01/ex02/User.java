package day01.ex02;

public class User {
    private final Integer id;
    private String name;
    private Integer balance;

    public User(String name, Integer balance) {
        if (balance < 0) {
            balance = 0;
        }
        if (name == null || name.isEmpty()) {
            System.err.println("Wrong name");
            System.exit(-1);
        }
        this.name = name;
        this.balance = balance;
        id = UserIdsGenerator.getGenerator().generateId();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return ("\nidentifier : " + id +
                "\nname       : " + name +
                "\nbalance    : " + balance);
    }
}
