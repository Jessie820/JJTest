package basic;

import java.util.List;

public class GenericsExample {
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//List<?> test = new List();
	}

}

class Box<Object>{
	private Object t;
	public Object get(){return t;}
}