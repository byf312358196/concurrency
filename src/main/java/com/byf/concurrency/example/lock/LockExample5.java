package com.byf.concurrency.example.lock;

import com.byf.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

@Slf4j
@ThreadSafe
public class LockExample5 {
    private final static int clientTotal = 5000;
    private final static int threadTotal = 200;
    private static int count = 0;
    private final static StampedLock lock = new StampedLock();
    private static void add(){
        long stamp = lock.writeLock();
        try{
            count++;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0; i<clientTotal;i++){
            exec.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();
        log.info("count:{}",count);
    }
}
