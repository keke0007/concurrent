package com.concurrent.demo01_problem;

import org.openjdk.jcstress.infra.results.I_Result;

/**
 * 演示有序性问题
 */
//@JCStressTest
//@Outcome(id = {"1","4"},expect = Expect.ACCEPTABLE,desc = "ok")
//@Outcome(id = {"0"},expect = Expect.ACCEPTABLE_INTERESTING,desc = "danger")
//@State
public class Test03Ordering {
    int num = 0;
    boolean ready = false;

    //线程1执行的代码
//    @Actor
    public void actor1(I_Result r) {
        if (ready) {
            r.r1 = num + num;
        } else {
            r.r1 = 1;
        }
    }

    //线程2执行的代码
//    @Actor
    public void actor2(I_Result r) {
        num = 2;
        ready = true;
    }
}
