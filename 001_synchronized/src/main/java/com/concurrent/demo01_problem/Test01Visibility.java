package com.concurrent.demo01_problem;

/**
 * 目标:演示可见性问题
 *  1.创建一个共享变量
 *  2.创建一条线程不断读取共享变量
 *  3.创建一条线程修改共享变量
 *  result: 测试发现flag的值为false,但是循环线程并没有停止,线程与线程之间存在可见性问题
 */
public class Test01Visibility {
    //1.创建一个共享变量
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        //2.创建一个线程读取共享变量
        new Thread(()->{
            while (flag) {

            }
        }).start();
        Thread.sleep(2000);

        //3.另一个线程修改变量
        new Thread(()->{
            flag = false;
            System.out.println("线程修改了共享变量的flag的值为: " + flag);
        }).start();

    }
}
