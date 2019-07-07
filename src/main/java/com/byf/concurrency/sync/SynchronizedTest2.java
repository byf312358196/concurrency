package com.byf.concurrency.sync;

import com.byf.concurrency.annoations.NotThreadSafe;
import com.byf.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@ThreadSafe
public class SynchronizedTest2 {

    // 修饰类
    public void test1(int j){
        synchronized (SynchronizedTest2.class){
            for (int i=0;i<10;i++){
                log.info("test1 {} -> {}",j, i);
            }
        }
    }
    // 修饰方法
    public synchronized static void test2(int j){
        for (int i=0;i<10;i++){
            log.info("test2 {} -> {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedTest2 t1  = new SynchronizedTest2();
        SynchronizedTest2 t2  = new SynchronizedTest2();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(()->{
            t1.test1(1);
        });

        exec.execute(()->{
            t2.test2(2);
        });
        exec.shutdown();
    }

}
