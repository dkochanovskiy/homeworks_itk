import java.util.concurrent.*;

public class ComplexTaskExecutor {

    private final int numberOfTasks;
    private final CyclicBarrier barrier;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
        
        this.barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("All tasks reached the barrier. Combining results...");
        });
    }

    public void executeTasks(int numberOfTasks) {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfTasks);
        
        for (int i = 0; i < numberOfTasks; i++) {
            final int taskId = i;
            
            executor.submit(() -> {
                try {
                    ComplexTask task = new ComplexTask(taskId);
                    task.execute();
                    
                    System.out.println(Thread.currentThread().getName() 
                            + " waiting at barrier");
                    
                    barrier.await();
                    
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
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