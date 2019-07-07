package com.byf.concurrency.singleton;

import com.byf.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class SingletonExample2 {
    private SingletonExample2(){

    }
    // 静态代码块的初始化顺序，从上往下；
    private static SingletonExample2 instance = null;

    static {
        instance = new SingletonExample2();
    }

    public static SingletonExample2 getInstance(){
        return instance;
    }
    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());

        System.out.println(SingletonExample3.getInstance().hashCode());
    }
}
