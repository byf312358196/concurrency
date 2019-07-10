package com.byf.concurrency.example.syncContainer;

import com.byf.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

@Slf4j
@NotThreadSafe
public class VectorExample3 {

    // java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> vector){ // foreach
        for (Integer i : vector) {
            // vector.get(i).equals(3)不会抛出 java.util.ConcurrentModificationException
            if (i.equals(3)) {
                vector.remove(i);
            }
        }
    }

    private static void test2(Vector<Integer> vector){ // iterator
        Iterator iterator = vector.iterator();
        while (iterator.hasNext()) {
            /*if (iterator.next().equals(3)) {
                iterator.remove();
            } // 不会抛出 java.util.ConcurrentModificationException*/
            Integer i = (Integer) iterator.next();
            if (i.equals(3)){
                vector.remove(i);
            }
        }
    }
    // success
    private static void test3(Vector<Integer> vector){ // for
        for (int i=0;i<vector.size();i++) {
            if (vector.get(i).equals(3)){
                vector.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test1(vector);
        log.info("size:{}",vector.size());
    }
}
