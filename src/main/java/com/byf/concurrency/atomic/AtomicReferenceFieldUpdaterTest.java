package com.byf.concurrency.atomic;

import com.byf.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicReferenceFieldUpdaterTest {

    @Getter
    private volatile int count = 100;
    private static AtomicIntegerFieldUpdater<AtomicReferenceFieldUpdaterTest> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicReferenceFieldUpdaterTest.class, "count");

    private static AtomicReferenceFieldUpdaterTest test = new AtomicReferenceFieldUpdaterTest();
    public static void main(String[] args) {
        if (updater.compareAndSet(test,100,200)){
            log.info("count{}", test.getCount());
        }

        if (updater.compareAndSet(test,100,200)){
            log.info("update success count:{}", test.getCount());
        } else {
            log.error("update failed count:{}", test.getCount());
        }
    }
}
