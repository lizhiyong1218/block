package com.lzy.block.core.thread;

/**
 * 
* @ClassName: RunnableTest
* @Description: runnable创建线程测试 
* @author 李志勇
* @date 2015年6月9日 上午10:40:24
*
 */
public class RunnableTest  {
    public static void main(String[] args)
    {
        Thread thread1 = new Thread(new NewThread2(), "first");
        Thread thread2 = new Thread(new NewThread2(), "second");
        thread1.start(); // start thread1
        thread2.start(); // start thread2
    }
}

/**
 * create new thread by implementing Runnable
 */
class NewThread2 implements Runnable {
    /**
     * convert object to string
     */
    public String toString() {
        return Thread.currentThread().getName();
    }

    /**
     * what does the thread do?
     */
    public void run() {
        System.out.println(this);
    }
}


