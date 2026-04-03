import java.util.concurrent.*;

public class ComplexTaskExecutor {

    private final int numberOfTasks;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public void executeTasks(int numberOfTasks) {

        ExecutorService executor = Executors.newFixedThreadPool(numberOfTasks);

        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("All tasks reached the barrier. Combining results...");
        });

        for (int i = 0; i < numberOfTasks; i++) {

            int taskId = i;

            executor.submit(() -> {
                try {

                    ComplexTask task = new ComplexTask(taskId);

                    task.execute();

                    System.out.println(Thread.currentThread().getName()
                            + " waiting at barrier");

                    barrier.await();

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}