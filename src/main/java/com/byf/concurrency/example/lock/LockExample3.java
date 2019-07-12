package com.byf.concurrency.example.lock;

import com.byf.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@ThreadSafe
public class LockExample3 {
    private final Map<String, Date> map = new TreeMap<>();
    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final static Lock readLock = lock.readLock();
    private final static Lock writeLock = lock.writeLock();

    public Date get(String key){
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Date put(String key, Date value){
        writeLock.lock();
        try{
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
}
