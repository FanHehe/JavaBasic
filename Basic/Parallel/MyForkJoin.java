package Parallel;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask; // RecursiveTask extends ForkJoinTask

public class MyForkJoin {

    // 参考Inter cilk 语言
    static class Task extends RecursiveTask<Integer> {

        private int from = 0;
        private int to = 0;
        private int threshold = 5;

        public Task() {}

        public Task(int from, int to) {
            this.to = to;
            this.from = from;
        }

        public Task (int to) {
            this.from = 0;
            this.to = to;
        }

        @Override
        public Integer compute() {

            int length = to - from;

            if (length <= threshold) {
                return Integer.valueOf(sum());
            }

            Task left = new Task(from, from + length / 2);
            Task right = new Task(from + length / 2 + 1, to);

            left.fork();
            return right.compute() + left.join();
            // https://www.liaoxuefeng.com/article/1146802219354112
        }

        public int sum() {
            int sum = 0;
            for (int i = from; i <= to; i++) {
                sum += i;
            }

            return sum;
        }
    }

    public static void main(String[] args) {
        MyForkJoin.Task task = new MyForkJoin.Task(0, 50);
        System.out.println(new ForkJoinPool().invoke(task));
    }
}
