package com.concurrent.demo03_synchronized_feature;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示Lock不可中断和可中断
 */
public class Demo03_interruptiable {
    //定义Lock锁
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
//        test01();
        test02();
    }


    //演示Lock可中断
    public static void test02() throws InterruptedException {
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            boolean b = false;
            try {
                 b = lock.tryLock(3, TimeUnit.SECONDS);
                if (b) {
                    System.out.println(name + "获得锁,进入锁执行");
                    Thread.sleep(888888);
                } else {
                    System.out.println(name + "在指定的时间没有获得锁做其他事情");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (b) {
                    lock.unlock();
                    System.out.println(name + "释放锁");
                }
            }
        };
        Thread t1 = new Thread(runnable);
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(runnable);
        t2.start();
//        System.out.println("线程t2停止前");
//        t2.interrupt();
//        System.out.println("线程t2停止后");
//        Thread.sleep(1000);
//        System.out.println("t1线程的状态" + t1.getState());
//        System.out.println("t2线程的状态" + t2.getState());
    }
    //演示Lock不可中断
    public static void test01() throws InterruptedException {
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            try {
                lock.lock();
                System.out.println(name + "获得锁,进入锁执行");
                Thread.sleep(888888);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(name + "释放锁");
            }
        };
        Thread t1 = new Thread(runnable);
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(runnable);
        t2.start();
        System.out.println("线程t2停止前");
        t2.interrupt();
        System.out.println("线程t2停止后");
        Thread.sleep(1000);
        System.out.println("t1线程的状态" + t1.getState());
        System.out.println("t2线程的状态" + t2.getState());
    }
}
