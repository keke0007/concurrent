package com.concurrent.demo02_solve_problem;

/**
 * 目标:演示解决可见性问题
 */
public class Test01Visibility {
    //1.创建一个共享变量
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        //2.创建一个线程读取共享变量
        new Thread(()->{
            while (flag) {
                //println是synchronized修饰线程安全的方法
                //synchronized会刷新工作内存中变量的值,会得到共享变量中最新的值
                System.out.println(flag);
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
