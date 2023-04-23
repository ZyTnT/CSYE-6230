public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        // 4 threads adding money
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                while (true) {
                    account.addMoney(100);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        // 5 threads taking money out
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    int amount = (int) (Math.random() * 200) + 100;
                    account.takeMoney(amount);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        // Monitor the account balance
        while (true) {
            System.out.println("Current balance is " + account.getBalance());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}