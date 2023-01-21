package day01.ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList{
    private Transaction head;
    private Transaction last;
    private Integer sizeList;


    public TransactionsLinkedList() {
        this.head = null;
        this.last = null;
        this.sizeList = 0;
    }



    @Override
    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            return;
        }
        if (head == null) {
            transaction.setNext(null);
            transaction.setPrev(null);
            head = last = transaction;
        } else {
            last.setNext(transaction);
            transaction.setPrev(last);
            transaction.setNext(null);
            last = transaction;
        }
        ++sizeList;
    }

    @Override
    public void removeTransactionById(UUID id) {
        if (this.head == null) {
            System.out.println("List is empty");
            return;
        }
        Transaction cur = this.head;
        while (cur != null) {
            if (cur.getId().equals(id)) {
                break;
            }
            cur = cur.getNext();
        }
        if (cur == null) {
            throw new TransactionNotFoundException("Wrong UUID");
        }
        if (cur == head) {
            head = head.getNext();
        } else if (cur == last) {
            last = last.getPrev();
        } else {
            cur.getPrev().setNext(cur.getNext());
        }
        --sizeList;
    }

    @Override
    public Transaction[] toArrayTrans() {
        Transaction[] mass = new Transaction[getSize()];
        int i = 0;
        Transaction cur = this.head;
        while (cur != null) {
            mass[i++] = cur;
            cur = cur.getNext();
        }
        return mass;
    }

    public int getSize() {
        return this.sizeList;
    }

    public void printList() {
        Transaction cur = head;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.getNext();
        }
    }
}
