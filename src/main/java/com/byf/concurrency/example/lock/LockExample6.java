package com.byf.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockExample6 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        new Thread(()->{
           reentrantLock.lock();
           log.info("{},wait signal",Thread.currentThread().getName());
            try {
                condition.await();
            } catch (InterruptedException e) {
                log.warn("exception", e);
            }
            log.info("{},get signal",Thread.currentThread().getName());
            reentrantLock.unlock();
        }).start();
        new Thread(()->{
            reentrantLock.lock();
            log.info("{},get lock",Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.warn("exception", e);
            }
            log.info("{},send signal",Thread.currentThread().getName());
            condition.signal();
            reentrantLock.unlock();
        }).start();
    }
}
