package com.byf.concurrency.sync;

import com.byf.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@NotThreadSafe
public class SynchronizedTest1 {

    // 修饰代码块
    public void test1(int j){
        synchronized (this){
            for (int i=0;i<10;i++){
                log.info("test1 {} -> {}",j, i);
            }
        }
    }
    // 修饰方法
    public synchronized void test2(int j){
        for (int i=0;i<10;i++){
            log.info("test2 {} -> {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedTest1 t1  = new SynchronizedTest1();
        SynchronizedTest1 t2  = new SynchronizedTest1();
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
