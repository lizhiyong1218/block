package com.lzy.block.core.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * java并发编程实战 程序清单6-17
 * 
 * 考虑这样一个旅行预订门户网站：用户输入旅行的日期和其他要求，门户网站获取并显示来自
 * 多条航线、旅店或汽车租赁公司的报价。在获取不同公司报价的过程中，可能会调用web服务、访问
 * 数据库、执行一个EDI事物或其他几只。在这种情况下，不宜让页面的响应时间受限于最慢的响应
 * 时间，而应该只显示在指定时间内收到的信息。对于没有及时响应的服务提供者，页面可以忽略她们，或者
 * 显示一个提示信息，例如“did not hear from air java in time”
 *
 */
public class InvokeAllTest {
	
	private static final  ExecutorService executor=Executors.newFixedThreadPool(8);//线程池	
	
	public static List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo,Set<TravelCompany> companies, Comparator<TravelQuote> ranking,long time, TimeUnit unit)
 throws InterruptedException {

		List<QuoteTask> tasks = new ArrayList<QuoteTask>();
		for (TravelCompany company : companies)
			tasks.add(new QuoteTask(company, travelInfo));
		
		/**
		 * invokeall按照任务集合中迭代器的顺序将所有的future添加到返回的集合中,
		 * 从而使调用者能将各个future与其表示的callable关联起来，当所有任务都执行完毕时，
		 * 或者调用线程被中断时，又或者超过指定实现时，invokeall将返回。当超过指定时限后，
		 * 任何还未完成的任务都会取消。当invokeall返回后，每个任务要么正常地完成，要么被取消，
		 * 而客户端代码可以调用get或isCancelled来判断究竟是何种情况
		 */
		List<Future<TravelQuote>> futures = executor.invokeAll(tasks, time, unit);
		
		List<TravelQuote> quotes = new ArrayList<TravelQuote>(tasks.size());
		Iterator<QuoteTask> taskIter = tasks.iterator();
		for (Future<TravelQuote> f : futures) {
			System.err.println(f.isCancelled());
			QuoteTask task = taskIter.next();
			try {
				quotes.add(f.get());
			} catch (ExecutionException e) {
				quotes.add(task.getFailureQuote(e.getCause()));
			} catch (CancellationException e) {
				quotes.add(task.getTimeoutQuote(e));
			}
		}
		Collections.sort(quotes, ranking);
		return quotes;
	}
	
	public static void main(String[] args) {
		TravelInfo travelInfo=new TravelInfo("去北京玩");
		Set<TravelCompany> companies=new HashSet<TravelCompany>();
		for(int i=0;i<10;i++){
			companies.add(new TravelCompany("公司"+i));
		}
		Comparator<TravelQuote> ranking=new Comparator<TravelQuote>() {
			@Override
			public int compare(TravelQuote o1, TravelQuote o2) {
				if(o1.getPrice()>o2.getPrice()){
					return 1;
				}else if(o1.getPrice()==o2.getPrice()){
					return 0;
				}else{
					return -1;
				}
			}
		};
		long time=1100;
		TimeUnit unit=TimeUnit.MILLISECONDS;
		try {
			List<TravelQuote> rankedTravelQuotes = getRankedTravelQuotes(travelInfo, companies, ranking, time, unit);
			for (TravelQuote travelQuote : rankedTravelQuotes) {
				System.out.println(travelQuote);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

	//旅行报价model
	class TravelQuote{
		private int price;
		
		private String msg;
		
		public TravelQuote(int price,String msg){
			this.price=price;
			this.msg=msg;
		}
		
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}

		@Override
		public String toString() {
			return "TravelQuote [price=" + price + ", msg=" + msg + "]";
		}
		
	}
	//旅行信息model
	class TravelInfo{
		private String name;
		
		public TravelInfo(String name){
			this.name=name;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	//旅行公司model
	class TravelCompany{
		
		private String name;
		
		public TravelCompany(String name){
			this.name=name;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	//旅行报价任务
	class QuoteTask implements Callable<TravelQuote>{
		
		private TravelCompany company;
		private TravelInfo travelInfo;
		
		public QuoteTask(TravelCompany company,TravelInfo travelInfo){
			this.company=company;
			this.travelInfo=travelInfo;
		}
		
		public TravelQuote call(){
			System.out.println(travelInfo.getName()+"计算报价start:"+company.getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(travelInfo.getName()+"计算报价end:"+company.getName());
			Random random = new Random();
			return new TravelQuote(random.nextInt(20),"随机:"+Thread.currentThread().getName());
		}
		
		/**
		 * 计算失败结果
		 * @param cause
		 * @return
		 */
		public TravelQuote getFailureQuote(Throwable cause){
			return new TravelQuote(1, Thread.currentThread().getName()+":"+cause.getMessage());
		}
		
		/**
		 * 计算超时结果
		 * @param cause
		 * @return
		 */
		public TravelQuote getTimeoutQuote(Throwable cause){
			return new TravelQuote(2, Thread.currentThread().getName()+":"+cause.getMessage());
		}
	}
