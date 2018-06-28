package basic;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Mutex {
	  class Sync extends AbstractQueuedSynchronizer {
		  
		    public Sync(){
			  setState(0); //현재 동기화 상태 지정
		    }
		  
		    public boolean tryAcquire(int ignore) {
		     boolean check = compareAndSetState(0,1);
		     System.out.println(check);
		      return check;  //locked이면 state == 1
		    }
		    public boolean tryRelease(int ignore) {
		      setState(0);
		      return true;  //unlocked이면 state == 0
		    }
	   }
	  
		  private final Sync sync = new Sync();
		  public void lock() { sync.acquire(0); } // current thread interrupt
		  public void unlock() { sync.release(0); }
		  
		  
		  
}
