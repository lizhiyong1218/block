/**  
* @Title: ReentrantLockTest.java
* @Package com.lzy.block.core.thread
* @author 李志勇  
* @date 2015年6月9日 上午10:41:22
* @version V1.0  
*/ 
package com.lzy.block.core.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLockTest
 * @Description: 显示加锁测试 
 * @author 李志勇
 * @date 2015年6月9日 上午10:41:22
 *
 */
public class ReentrantLockTest extends Thread{
	// 锁对象
	private final ReentrantLock lock = new ReentrantLock();

	
	// 保证线程安全方法
	public void method() {
		System.out.println(super.getName()+"开始加锁!");
		// 上锁
		lock.lock();
		try {
			// 保证线程安全操作代码
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			lock.unlock();// 释放锁
			System.out.println(super.getName()+"解锁成功!");
		}
	}
	
	public void run() {
		method();
    }
	
	public ReentrantLockTest(String name){
		super(name);
	}
	
}
