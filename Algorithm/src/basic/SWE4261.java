package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class SWE4261{
	
	static HashMap<Character, List<Character>> keypad;
	static ArrayList<String> words;
	static ArrayList<Integer> cnt = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException{
		keypad = new HashMap<Character, List<Character>>();
		
		List<Character> temp = new ArrayList<Character>(
				Arrays.asList('a', 'b', 'c'));
		List<Character> temp2 = new ArrayList<Character>(
				Arrays.asList('d', 'e', 'f'));
		List<Character> temp3 = new ArrayList<Character>(
				Arrays.asList('g', 'h', 'i'));
		List<Character> temp4 = new ArrayList<Character>(
				Arrays.asList('j', 'k', 'l'));
		List<Character> temp5 = new ArrayList<Character>(
				Arrays.asList('m', 'n', 'o'));
		List<Character> temp6 = new ArrayList<Character>(
				Arrays.asList('p', 'q', 'r', 's'));
		List<Character> temp7 = new ArrayList<Character>(
				Arrays.asList('t', 'u', 'v'));
		List<Character> temp8 = new ArrayList<Character>(
				Arrays.asList('w', 'x', 'y', 'z'));
		
		keypad.put('2', temp);
		keypad.put('3', temp2);
		keypad.put('4', temp3);
		keypad.put('5', temp4);
		keypad.put('6', temp5);
		keypad.put('7', temp6);
		keypad.put('8', temp7);
		keypad.put('9', temp8);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int i=0; i<T; i++){
			String line = br.readLine();
			st = new StringTokenizer(line);
			String keyCombo = st.nextToken();
			
			int wordNum = Integer.parseInt(st.nextToken());
			
			words = new ArrayList<String>();
			String line2 = br.readLine();
			st = new StringTokenizer(line2);
			
			for(int j=0; j<wordNum; j++){
				words.add(st.nextToken());
			}
			
			checkWord(keyCombo);
			
			cnt.add(words.size());
			
		}
		

		for(int i=0; i<cnt.size(); i++){
			System.out.print("#"+(i+1)+" "+cnt.get(i));
			System.out.println();
		}
		
	}
	
	
	public static void checkWord(String keyCombo){
		
		int length = keyCombo.length();
		
		for(int i=0; i<length; i++){
			char key = keyCombo.charAt(i);
			List <Character> charList = keypad.get(key);
			Iterator<String> iter = words.iterator();
			
			while(iter.hasNext()){
				
				String s = iter.next();
				
				if(charList.contains(s.charAt(i))){
					
				}else{
					iter.remove();
				}
			}
			
		}
		
	}

}
