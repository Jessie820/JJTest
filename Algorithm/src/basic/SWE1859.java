package basic;

import java.util.ArrayList;
import java.util.Scanner;

public class SWE1859 {
	
	static int T;
	static int num;
	static int arr[];
	static ArrayList<Long> ans = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		
		for(int i=0; i<T; i++){
			num = sc.nextInt();
			arr = new int[num];
			for(int j=0; j<num; j++){
				arr[j] = sc.nextInt();
			}	
		    findMax();	
		}
		
		for(int i=0; i<ans.size(); i++){
			System.out.print("#" + (i+1) + " " + ans.get(i));
			System.out.println();
		}
	}
	
	public static void findMax(){
		
		int last = arr.length -1;
		long answer = 0L;
		int max = arr[last];
		for(int i=last-1; i>=0; i--){
			if(max > arr[i]){
				answer += (max - arr[i]);
			}else{
				max = arr[i];
			}
		}
		ans.add(answer);
		
	}
	
	
	/*
	static int T;
	static int num;
	static int arr[];
	static long dp[];
	static long max;
	static ArrayList<Long> ans = new ArrayList<>();
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		
		for(int i=0; i<T; i++){
			num = sc.nextInt();
			arr = new int[num];
			dp = new long[num];
			dp[0] = 0; 
			max = 0;
			for(int j=0; j<num; j++){
				arr[j] = sc.nextInt();
			}	
			findMax();
		}
		
		
		for(int i=0; i<ans.size(); i++){
			System.out.print("#" + (i+1) + " " + ans.get(i));
			System.out.println();
		}
		
	

	}
	
	public static void findMax(){

		int chk = 0;
		for(int i=1; i<num; i++){
			long sum = 0;
			for(int j=0; j<i; j++){
				if(arr[i]>arr[j]){
					sum += (arr[i] - arr[j]);
				}
			}	
			
			long sum2 = 0;
			for(int k=chk; k<i; k++){
				if(arr[i]>arr[k]){
					sum2 += (arr[i] - arr[k]);
				}
			}
			
			if(sum > dp[i-1]+sum2){
				chk = i;//팔지 않은 지점 체크 
				dp[i] = sum;
				System.out.println("dp-in" + i + ": "+dp[i]);
			}else{
				dp[i] = dp[i-1];
				//System.out.println("predp" + i + ": "+dp[i]);
				
				if(sum2 > 0){
					chk = i;//팔지 않은 지점 체크 
					dp[i] += sum2;
					System.out.println("dp-middle" + i + ": "+dp[i]);
				}
				System.out.println("dp-out" + i + ": "+dp[i]);
			}
			
			
			
		 }
		
		ans.add(dp[num-1]);
	
	}*/
	
}
