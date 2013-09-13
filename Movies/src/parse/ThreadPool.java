package parse;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	private int corePoolSize = 10;
	private int maximumPoolSize = 10;
	private long keepAliveTime = 10;
	private ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(
			20);
	private ThreadPoolExecutor threadPool;

	public ThreadPool() {
		threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.SECONDS, queue);
		RejectedExecutionHandler handler = new RejectedExecutionHandler() {			
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				executor.execute(r);
			}
		};
		threadPool.setRejectedExecutionHandler(handler);
		threadPool.prestartAllCoreThreads();
	}
	public void runTask(Runnable task){
		threadPool.execute(task);
	}
	
	public void shutDown(){
		threadPool.shutdown();
	}

}
