import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, world");

        int n = 10;

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            FactorialTask factorialTask = new FactorialTask(n);

            long result = forkJoinPool.invoke(factorialTask);

            System.out.println("Факториал " + n + "! = " + result);
        }
    }
}