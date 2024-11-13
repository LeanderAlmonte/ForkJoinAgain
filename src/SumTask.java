import java.util.concurrent.RecursiveTask;


public class SumTask extends RecursiveTask<Integer>{

    private static final int THRESHOLD = 20;

    private int[] array;
    private int start;
    private int end;

    // constructor

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            // if below threshold, compute the sum directly with simple loop
            int sum = 0;
            for(int i = start; i < end; i++){
                sum += array[i];
            }
            return sum;
        } else {

            // otherwise, split the task into two subtasks
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);

            // fork (execute in parallel) both left and right tasks
            leftTask.fork();
//            rightTask.fork();

            // Join (wait for completion)
            int rightResult = rightTask.compute();
            int leftResult = leftTask.join();

            //Combine both results

            return leftResult + rightResult;
        }
    }
}
