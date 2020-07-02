package com.concurrent.demo03_synchronized_feature;

/**
 * 演示synchronized的可重入特性
 */
public class Demo01 {
    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
    }

    public static void test01() {
        synchronized (MyThread.class) {
            System.out.println(Thread.currentThread().getName() + "进入同步代码块2");
        }
    }

    static class  MyThread extends Thread{
        @Override
        public void run() {
            synchronized (MyThread.class) {
                System.out.println(getName() + "进入同步代码块1");
                Demo01.test01();
            }
        }
    }
}
