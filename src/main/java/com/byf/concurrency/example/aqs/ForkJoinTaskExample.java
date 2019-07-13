package com.byf.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer> {
    public static final int threshold = 10;
    private int start;
    private int end;
    public ForkJoinTaskExample(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        int sum = 0;

        // 如果任务足够小，就计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute){
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果子任务大于阈值，就分裂成两个种子任务计算；
            int middle = (start + end) / 2;
            ForkJoinTaskExample leftTask = new ForkJoinTaskExample(start, middle);
            ForkJoinTaskExample rightTask = new ForkJoinTaskExample(middle +1, end);
            leftTask.fork();
            rightTask.fork();
            // 等待任务执行结束合并结果；
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务，计算1+2+3+4
        ForkJoinTaskExample task = new ForkJoinTaskExample(1,100);
        // 执行一个任务
        Future<Integer> future = forkJoinPool.submit(task);
        try{
            log.info("result:{}",future.get());
        } catch (Exception e){
            log.error("exception", e);
        }
    }
}
