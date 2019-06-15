package Concurrent.Thread;


import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;

public class MyCallable {
    public static void main(String[] args) {

        Callable<String> call = () -> {
            return "string";
        };

        ExecutorService pool = Executors.newFixedThreadPool(1);

        // 支持提交 runnable callbale FutureTask
        Future<String> result = pool.submit(call);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {}

        pool.shutdown(); // 不shutdown 会一直跑着
    }
}
