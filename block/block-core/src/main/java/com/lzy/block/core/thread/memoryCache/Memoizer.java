package com.lzy.block.core.thread.memoryCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 为了解决高并发情况下，某些操作很花时间和资源的操作（比如长时间的计算），通过缓存任务，只执行一次操作
 * 如果缓存对象里面有future则直接get，等待结果即可
 *
 * @param <A>
 * @param <V>
 */
public class Memoizer<A,V> {
	
	private final ConcurrentHashMap<A, Future<V>> cache=new ConcurrentHashMap<A, Future<V>>();

	public V compute(final A key)throws InterruptedException{
		while(true){
			Future<V> f = cache.get(key);
			if(f==null){
				Callable<V> callable=new Callable<V>() {
					public V call()throws InterruptedException{
						//DO SOMETHING... 在回调函数里面执行耗时操作
						return null;
					}
				};
				FutureTask<V> ft=new FutureTask<V>(callable);
				//如果map里面有key，则会返回map里面key对应的value,如果没有该key，则会添加到map里面，并且返回null
				f=cache.putIfAbsent(key, ft);
				if(f==null){
					f=ft;
					ft.run();
				}
			}
			try {
				V v = f.get();
				return v;
			}catch (Exception e){//如果任务被取消或者发生异常，则从cahce里面移除，这样将来可以再计算
				cache.remove(key, f);
			}
		}
		
	}
	
	public static void main(String[] args) {
		ConcurrentHashMap<String, String> s=new ConcurrentHashMap<>();
		String s1 = s.putIfAbsent("a", "s");
		System.err.println(s1);
		String s2=s.putIfAbsent("a", "s2");
		System.err.println(s2);
		System.err.println(s.get("a"));
	}
	
}
