import java.util.ArrayList;
import java.util.List;

public class ConcurrentBank {

    private final List<BankAccount> accounts = new ArrayList<>();

    public synchronized BankAccount createAccount(int initialBalance) {
        BankAccount account = new BankAccount(initialBalance);
        accounts.add(account);
        return account;
    }

    public void transfer(BankAccount from, BankAccount to, int amount) {

        BankAccount firstLock = from.getId() < to.getId() ? from : to;
        BankAccount secondLock = from.getId() < to.getId() ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {

                if (from.withdraw(amount)) {
                    to.deposit(amount);
                }

            }
        }
    }

    public int getTotalBalance() {

        int total = 0;

        synchronized (accounts) {
            for (BankAccount account : accounts) {
                total += account.getBalance();
            }
        }

        return total;
    }
}