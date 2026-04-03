public class BankAccount {

    private static int counter = 0;

    private final int id;
    private int balance;

    public BankAccount(int initialBalance) {
        this.id = ++counter;
        this.balance = initialBalance;
    }

    public int getId() {
        return id;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized boolean withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public synchronized int getBalance() {
        return balance;
    }
}