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
		
	  /*lockCondition.await(); // ���� ����ȭ���� wait();
	  lockCondition.signal(); // ���� ����ȭ���� notify();
	  lockCondition.signalAll(); // ���� ����ȭ���� notifyAll();
*/	  
	}finally{
	  lock.unlock();
	   }
}
}
