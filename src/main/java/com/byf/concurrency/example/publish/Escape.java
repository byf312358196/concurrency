package com.byf.concurrency.example.publish;

import com.byf.concurrency.annoations.NotRecommend;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotRecommend
public class Escape {


    public Escape(){
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass(){
            log.info("{}", Escape.this.thisCanBeEsape);
        }
    }
    private int thisCanBeEsape = 0;

    public static void main(String[] args) {
        new Escape();
    }
}
