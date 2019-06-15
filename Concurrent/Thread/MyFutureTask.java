package Concurrent.Thread;

import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.CompletableFuture;

public class MyFutureTask {

    public static void handleFutureTask() {

        Callable<String> call = () -> {
            System.out.println("future task");
            return "call";
        };

        FutureTask<String> task = new FutureTask<>(call);

        ExecutorService pool = Executors.newCachedThreadPool();

        pool.submit(task);

        pool.shutdown();
    }

    public static void main(String[] args) {
        handleFutureTask();
    }
}
