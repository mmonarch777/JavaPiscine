package day01.ex04;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private Integer balance;

    private TransactionsLinkedList list;



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
        this.list = new TransactionsLinkedList();
        this.id = UUID.randomUUID();
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public TransactionsLinkedList getTransactionsList() {
        return this.list;
    }
    @Override
    public String toString() {
        return ("\nid                  : " + id +
                "\nname                : " + name +
                "\nbalance             : " + balance +
                "\namount transactions : " + list.getSize());
    }
}
