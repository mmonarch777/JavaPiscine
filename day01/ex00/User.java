package day01.ex00;

import java.util.UUID;

public class User {
    private UUID identifier;
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
        identifier = UUID.randomUUID();
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public UUID getIdentifier() {
        return identifier;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return ("\nidentifier : " + identifier +
                "\nname       : " + name +
                "\nbalance    : " + balance);
    }
}
