/**  
* @Title: Test.java
* @Package com.lzy.block.core.thread
* @author 李志勇  
* @date 2015年5月21日 上午11:56:49
* @version V1.0  
*/ 
package com.lzy.block.core.thread;

/**
 * @ClassName: Test
 * @Description: thread创建线程测试 
 * @author 李志勇
 * @date 2015年5月21日 上午11:56:49
 *
 */
public class ThreadTest
{
    public static void main(String[] args)
    {
        NewThread thread1 = new NewThread();
        NewThread thread2 = new NewThread();
        NewThread thread3 = new NewThread();
        thread1.start(); // start thread1
        thread2.start(); // start thread2
        thread3.start(); // start thread3
    }
}

/**
 * create new thread by inheriting Thread
 */
class NewThread extends Thread {

    private static int threadID = 0; // shared by all

    /**
     * constructor
     */
    public NewThread() {
        super("ID:" + (++threadID));
    }

    /**
     * convert object to string
     */
    public String toString() {
        return super.getName();
    }

    /**
     * what does the thread do?
     */
    public void run() {
        System.out.println(this);
    }
}
