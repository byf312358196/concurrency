package com.byf.concurrency.example.aqs;

import com.byf.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@ThreadSafe
public class CountDownLatchExample1 {
    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i=0;i<threadCount;i++){
            final int threadNum = i;
            exec.execute(()->{
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("exception",e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        // countDownLatch在等待指定时间，超过则不再等待
        // countDownLatch.await(10, TimeUnit.MILLISECONDS);
        countDownLatch.await();
        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("threadNum:{}",threadNum);
        Thread.sleep(100);
    }
}
