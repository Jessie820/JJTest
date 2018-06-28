package basic;

public class MutexTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mutex mutex = new Mutex();
		
		MyThread mt1 = new MyThread(mutex);
		MyThread mt2 = new MyThread(mutex);
		MyThread mt3 = new MyThread(mutex);
		
		mt1.start();
		mt2.start();
		mt3.start();
	}

}

 class MyThread extends Thread{
	
	Mutex mutex;
	
	MyThread(Mutex mutex){
		this.mutex = mutex;
	}
	
	public void run(){
		try{
			mutex.lock();
			System.out.println("Hello "+this.getName());
				try{
					sleep(2000);
				}catch(Exception e){}			
	 	   }catch(Exception ie){
		   }finally{
				mutex.unlock();
				System.out.println("Goodbye " + this.getName());
		   }
			
		}
	}

	
