/**  
* @Title: SellTicketTest.java
* @Package com.lzy.block.core.thread
* @author 李志勇  
* @date 2015年5月21日 下午1:52:38
* @version V1.0  
*/ 
package com.lzy.block.core.thread;

/**
 * @ClassName: SellTicketTest
 * @Description: 卖票测试 
 * @author 李志勇
 * @date 2015年5月21日 下午1:52:38
 *
 */
public class SellTicketTest {
	public static void main(String[] args)
    {
        Reservoir r = new Reservoir(100);
        Booth b1 = new Booth(r);
        Booth b2 = new Booth(r);
        Booth b3 = new Booth(r);
        
    }
}


/**
 * contain shared resource
 */
class Reservoir {
    private int total;

    public Reservoir(int t) 
    {
        this.total = t;
    }

    /**
     * Thread safe method
     * serialized access to Booth.total
     */
    public synchronized boolean sellTicket() 
    {
        if(this.total > 0) {
            this.total = this.total - 1;
            return true; // successfully sell one
        }
        else {
            return false; // no more tickets
        }
    }
}

/**
 * create new thread by inheriting Thread
 */
class Booth extends Thread {
    private static int threadID = 0; // owned by Class object

    private Reservoir release;      // sell this reservoir 
    private int count = 0;          // owned by this thread object
    /**
     * constructor
     */
    public Booth(Reservoir r) {
        super("ID:" + (++threadID));
        this.release = r;          // all threads share the same reservoir
        this.start();//创建线程时就启动工作
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
        while(true) {
            if(this.release.sellTicket()) {
                this.count = this.count + 1;
//                System.out.println(this.getName() + ": sell 1");
                try {
                    sleep((int) Math.random()*100);   // random intervals
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                break;
            }
        }
        System.out.println(this.getName() + " I sold:" + count);
    }
}