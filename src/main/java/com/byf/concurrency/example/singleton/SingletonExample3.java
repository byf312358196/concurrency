package com.byf.concurrency.example.singleton;

import com.byf.concurrency.annoations.Recommend;
import com.byf.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
@Recommend
public class SingletonExample3 {
    // 私有构造函数
    private SingletonExample3(){

    }

    public static SingletonExample3 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample3 singleton = null;
        // JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonExample3();
        }
        private SingletonExample3 getInstance(){
            return singleton;
        }
    }

    public static void main(String[] args) {
        System.out.println(SingletonExample3.getInstance());
    }
}
