package com.byf.concurrency.example.aqs;

import com.byf.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
@ThreadSafe
public class SemaphoneExample4 {
    private static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);
        for (int i=0;i<threadCount;i++){
            final int threadNum = i;
            exec.execute(()->{
                try {
                    if(semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)){ // 允许时间范围内执行
                        test(threadNum);
                        semaphore.release();
                    }

                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("threadNum:{}",threadNum);
        Thread.sleep(1000);
    }
}
