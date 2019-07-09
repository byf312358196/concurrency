package com.byf.concurrency.example.immutable;

import com.byf.concurrency.annoations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class ImmutableExample3 {

    private final static ImmutableList list = ImmutableList.of(1,2,3);
    private final static ImmutableList set = ImmutableList.copyOf(list);
    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1,3,2,4);
    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer,Integer>builder().put(1,3).put(2,4).build();

    public static void main(String[] args) {
        // list.add(1);
        // set.add(4);
        // map.put(1,4);
        map2.put(4,4);
    }
}
