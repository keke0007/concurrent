package com.concurrent.demo02_solve_problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 目标:演示解决线程原子性问题
 */
public class Test02Atomicity {
    //1.定义一个共享变量number
    private static int number = 0;

    public static void main(String[] args) throws InterruptedException {
        //2.对number进行1000的++操作
        Runnable increment = () -> {
            for (int i = 0; i < 1000; i++) {
                //给number++加锁
                synchronized (Test02Atomicity.class) {
                    number++;
                }
            }
        };

        List<Thread> list = new ArrayList<>();
        //3.使用5个线程来进行
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(increment);
            thread.start();
            list.add(thread);
        }
        for (Thread thread : list) {
            thread.join();
        }
        System.out.println("number的值: " + number);
    }
}