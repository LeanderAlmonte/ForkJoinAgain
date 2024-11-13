import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class DoubleArrayTask extends RecursiveAction {

    private static final int THRESHOLD = 20;

    private int[] array;
    private int start;
    private int end;

    // constructor

    public DoubleArrayTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }


    @Override
    protected void compute() {
        if(end - start <= THRESHOLD) {
            for(int i = start; i < end; i++){
                array[i] *= 2;
            }
        } else {
            int mid = (start + end) / 2;
            DoubleArrayTask leftTask = new DoubleArrayTask(array, start, mid);
            DoubleArrayTask rightTask = new DoubleArrayTask(array, mid, end);

            leftTask.fork();
            rightTask.compute();
            leftTask.join();

        }
    }

    public static void main(String[] args) {
        int[] array = new int[150];
        for (int i = 0; i < array.length; i++){
            array[i] = i + 1;
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        DoubleArrayTask task = new DoubleArrayTask(array,0,array.length);
        forkJoinPool.invoke(task);

        //print the updated array

        for (int value : array) {
            System.out.println(value+" ");
        }
    }
}
