public class ComplexTask implements Runnable {

    private final int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() +
                " executing task " + taskId);

        try {
            Thread.sleep(500); // имитация сложной работы
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        execute();
    }
}