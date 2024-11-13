import java.util.concurrent.ForkJoinPool;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int[] array = new int[200];
        for(int i = 0; i <array.length; i++){
            array[i] = i + 1;
        }

        // create a ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // create the root task for the whole array
        SumTask task =  new SumTask(array, 0,array.length);

        // start the task in the pool to get the result

        // start the task in the pool to get the result
        int result = forkJoinPool.invoke(task);
        System.out.println(" the sum is: "+result);
    }
}