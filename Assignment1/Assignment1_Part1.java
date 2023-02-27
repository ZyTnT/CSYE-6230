public class Assignment1_Part1 {
    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            Thread thread = new Thread(new MyRunnable(), "Thread " + i);
            thread.start();
        }
    }
}

    class MyRunnable implements Runnable {
        public void run() {
            for(int i=0; i<5; i++){
                System.out.println(Thread.currentThread().getName() + " show" + i);
            }
        }
}
