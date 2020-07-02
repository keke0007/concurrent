package com.concurrent.demo05_cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 通过AtomicInteger来保证线程的原子性
 */
public class Demo01_AtomicInteger {
    private static AtomicInteger a = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                a.incrementAndGet();
            }
        };
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(runnable);
            t.start();
            list.add(t);
        }

        for (Thread thread : list) {
            thread.join();
        }

        System.out.println(a.get());
    }
}
