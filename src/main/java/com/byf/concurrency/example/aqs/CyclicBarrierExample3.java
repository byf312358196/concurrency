package com.byf.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierExample3 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
        log.info("callback is execute.");
    });

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            final int threadNum = i;
            Thread.sleep(1000);
            exec.execute(()->{
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.warn("exception",e);
                }
            });
        }
        exec.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(100);
        log.info("{}, ready",threadNum);
        try{
            cyclicBarrier.await();
        } catch (Exception e){
            // 如果一定要保证下面的流程执行，此处可以捕获Exception异常，和定时器Timer一样，定时器抛出异常后没有捕获，则定时器被中断
            log.warn("BarrierException", e);
        }

        log.info("{}, continue",threadNum);
    }
}
