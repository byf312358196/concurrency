package com.byf.concurrency.example.syncContainer;

import com.byf.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class CollectionsExample1 {
    private final static int clientTotal = 5000;
    private final static int threadTotal = 200;
    private static List<Integer> list = Collections.synchronizedList(Lists.newArrayList());

    private static void update(final int count){
        list.add(1);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0; i<clientTotal;i++){
            final int count = i;
            exec.execute(()->{
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();
        log.info("size:{}",list.size());
    }
}
