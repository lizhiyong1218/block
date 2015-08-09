/**  
* @Title: TreadTest.java
* @Package org.block.core.tread
* @author 李志勇  
* @date 2014年12月26日 下午3:24:35
* @version V1.0  
*/ 
package org.block.core.thread;

//import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
//import net.sourceforge.groboutils.junit.v1.TestRunnable;
//
//import org.block.core.BaseTest;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//
//
///**
// * @ClassName: TreadTest
// * @Description: 多线程测试 
// * @author 李志勇
// * @date 2014年12月26日 下午3:24:35
// *
// */
//public class TreadTest extends BaseTest  {
//	/* 多线程测试需要下载GroboUtils,然后需要将GroboUtils-core-1.2.1.jar加入本地仓库, */
//	
//	/**
//	 * 
//	* @Title: groboTest
//	* @Description: 多线程测试
//	* @throws Throwable:     
//	* void    
//	* @throws
//	 */
//	@Test
//	public void groboTest() throws Throwable {
//		// 构造一个Runner
//		/*
//		 * TestRunnable runner = new TestRunnable() {
//		 * 
//		 * @Override public void runTest() throws Throwable { // 测试内容
//		 * System.out.println(">>>>>>"+Thread.currentThread().getName()); } };
//		 */
//
//		Runner runner = new Runner();
//
//		int runnerCount = 5;
//		// Rnner数组，想当于并发多少个。
//		TestRunnable[] trs = new TestRunnable[runnerCount];
//		for (int i = 0; i < runnerCount; i++) {
//			trs[i] = runner;
//		}
//		// 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入
//		MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);
//		// 开发并发执行数组里定义的内容
//		mttr.runTestRunnables();
//	}
//	
//	private class Runner extends TestRunnable {
//		@Override
//		public void runTest() throws Throwable {
//			// 线程要调用的方法或者要执行的操作
//			// 测试内容
//			System.out.println(">>>>>>>>>" + Thread.currentThread().getName());
//		}
//	}
//	
//	
//	/**
//	 * 
//	* @Title: testThr
//	* @Description: 获取所有线程:     
//	* void    
//	* @throws
//	 */
//	@Test
//	public void testThr(){
//		ThreadGroup group = Thread.currentThread().getThreadGroup();  
//		ThreadGroup topGroup = group;  
//		// 遍历线程组树，获取根线程组  
//		while (group != null) {  
//		    topGroup = group;  
//		    group = group.getParent();  
//		}  
//		// 激活的线程数加倍  
//		int estimatedSize = topGroup.activeCount() * 2;  
//		Thread[] slackList = new Thread[estimatedSize];  
//		// 获取根线程组的所有线程  
//		int actualSize = topGroup.enumerate(slackList);  
//		// copy into a list that is the exact size  
//		Thread[] list = new Thread[actualSize];  
//		System.arraycopy(slackList, 0, list, 0, actualSize);  
//		System.out.println("Thread list size == " + list.length);  
//		for (Thread thread : list) {  
//		    System.out.println(">>>>>>>>"+thread.getName());  
//		} 
//	}
//	
//}
