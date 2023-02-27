import java.util.Arrays;
import java.util.Random;

public class ArrayMultiplier {

    private static final int ARRAY_SIZE = 1000000;
    private static final int NUM_THREADS = 5;

    public static void main(String[] args) {
        double[] arr = new double[ARRAY_SIZE];
        Random r = new Random();

        // Fill the array with random values between 0 and 1
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextDouble();
        }

        long start = System.currentTimeMillis();

        // Compute the serial sum
        double serialSum = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= r.nextDouble() * 0.8 + 0.1;
        }

        for(int i=0; i<arr.length; i++){
            serialSum += arr[i];
        }

        long end = System.currentTimeMillis();
        long serialTime = end - start;


        System.out.println("Serial time: " + serialTime + " ms");

        // Compute the parallel sum
        double parallelSum = 0;

        Thread[] threads = new Thread[NUM_THREADS];
        SumThread[] sumThreads = new SumThread[NUM_THREADS];

        // Divide the array into segments and create a SumThread for each segment
        int segmentSize = ARRAY_SIZE / NUM_THREADS;
        for (int i = 0; i < NUM_THREADS; i++) {
            int startIndex = i * segmentSize;
            int endIndex = (i == NUM_THREADS - 1) ? ARRAY_SIZE : (i + 1) * segmentSize;
            sumThreads[i] = new SumThread(Arrays.copyOfRange(arr, startIndex, endIndex));
            threads[i] = new Thread(sumThreads[i]);
            threads[i].start();
        }

        start = System.currentTimeMillis();

        // Wait for all threads to finish and compute the total sum
        for (int i = 0; i < NUM_THREADS; i++) {
            try {
                threads[i].join();
                parallelSum += sumThreads[i].getSum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        long parallelTime = end - start;

        System.out.println("Parallel time: " + parallelTime + " ms");

        // Compute the time difference
        long timeDiff = parallelTime - serialTime;
        System.out.println("Time difference: " + timeDiff + " ms");
    }
}

class SumThread implements Runnable {
    private double[] segment;
    private double sum;

    public SumThread(double[] segment) {
        this.segment = segment;
    }

    public void run() {
        for (int i = 0; i < segment.length; i++) {
            segment[i] *= Math.random() * 0.8 + 0.1;
        }
        sum = Arrays.stream(segment).sum();
    }

    public double getSum() {
        return sum;
    }
}
