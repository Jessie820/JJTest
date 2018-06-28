package basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testReentrantlock {

}


class SomeClass extends Thread{
	private final Lock lock = new ReentrantLock();
	private final Condition lockCondition = lock.newCondition();
	
	public void run(){
	lock.lock();
	try{
		
	  /*lockCondition.await(); // 기존 동기화에서 wait();
	  lockCondition.signal(); // 기존 동기화에서 notify();
	  lockCondition.signalAll(); // 기존 동기화에서 notifyAll();
*/	  
	}finally{
	  lock.unlock();
	   }
}
}
