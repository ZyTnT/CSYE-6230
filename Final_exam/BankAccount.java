public class BankAccount {
    private int balance;

    public BankAccount(int initial) {
        balance = initial;
    }

    public synchronized void addMoney(int amount) {
        balance += amount;
        System.out.println("Add " + amount + ", new balance is " + balance);
    }

    public synchronized boolean takeMoney(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Take " + amount + ", new balance is " + balance);
            return true;
        }
        System.out.println("Cannot take " + amount + ", balance is only " + balance);
        return false;
    }

    public synchronized int getBalance() {
        return balance;
    }
}

