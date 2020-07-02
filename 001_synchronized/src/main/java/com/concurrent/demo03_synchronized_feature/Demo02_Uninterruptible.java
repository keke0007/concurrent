package com.concurrent.demo03_synchronized_feature;

/**
 * 演示synchronized不可中断特性
 */
public class Demo02_Uninterruptible {
    //定义一把锁
    private static Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            //在Runnable定义同步代码块
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + "进入同步代码块");
                //保证不退出同步代码块
                try {
                    Thread.sleep(888888);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //先开启一个线程执行同步代码块
        Thread t1 = new Thread(runnable);
        t1.start();
        Thread.sleep(1000);
        //之后在开启一个线程来执行同步代码块(阻塞状态)
        Thread t2 = new Thread(runnable);
        t2.start();

        //停止第二个线程
        System.out.println("停止线程前");
        t2.interrupt();
        System.out.println("停止线程后");
        //t1线程的状态
        System.out.println("t1线程的状态"+t1.getState());
        System.out.println("t2线程的状态"+t2.getState());
    }
}
