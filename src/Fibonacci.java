import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {

    int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if(n <=1 ){
            return n;
        }

        Fibonacci task1 = new Fibonacci(n-1);
        Fibonacci task2 = new Fibonacci(n-2);

        task1.fork();
        task2.fork();

        int result1 = task1.join();
        int result2 = task2.join();

        return result1 + result2;
    }

    public static void main(String[] args) {
        int n = 5;

        ForkJoinPool pool = new ForkJoinPool();

        Fibonacci task = new Fibonacci(n);

        int result = pool.invoke(task);

        System.out.println(result);
    }
}
