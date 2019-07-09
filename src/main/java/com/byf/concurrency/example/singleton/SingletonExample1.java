package com.byf.concurrency.example.singleton;

import com.byf.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class SingletonExample1 {
    private SingletonExample1(){

    }
    private volatile static SingletonExample1 instance = null;

    public static SingletonExample1 getInstance(){
        if (instance == null){
            synchronized (SingletonExample1.class){
                if (instance == null){
                    instance = new SingletonExample1();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        SingletonExample1 singletonExample1 = new SingletonExample1();
    }

}
