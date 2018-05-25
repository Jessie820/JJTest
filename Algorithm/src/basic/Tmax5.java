package basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Tmax5 {

	//StringUtil 에 넘길 때는 rule도 같이 넘긴다
	static HashMap<String,String> rule = new HashMap<String, String>() //A: 필요한 포맷 - 포맷에 해당하는 정보
			{{
				put("%{host}i", "virtualHost");
				put("%h", "remoteHost");
				put("%l", "remoteLog");
				put("%u", "user");
				put("%t", "logDate");
				put("%r", "mrh");
				put("%s", "status");
				put("%b", "bytes");
				put("%D", "respTime");

			}};
	
	static String format_opt = " ";
	static String binding = "\"";
	static ArrayList<Separator> separators;
	
	
	public static void main(String[] args) {
		//String data = "192.168.13.44:8080 192.168.13.43 - - [18/May/2018:13:41:51 +0900] \"POST /sysmaster/admin/requestDummy.action HTTP/1.1\" 200 2 4";
		//String data = "192.168.13.44:8089 192.168.13.43 - - [25/May/2018:13:15:00 +0900] \"GET /sysmaster/resource/base/style/images/workingList/ico_tree_container_gray.png HTTP/1.1\" 304 - 0";
		//String data ="192.168.13.44:8089 192.168.13.43 - - [25/May/2018:13:15:49 +0900] \"POST /sysmaster/admin/requestDummy.action HTTP/1.1\" 200 2 5 \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36\"";
		//String data = "192.168.13.44:8080 192.168.13.43 [02/May/2018:15:26:42 +0900] \"GET /sysmaster/base/login.action HTTP/1.1\" 200 6309 114 \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36\"";
		
		//String data ="\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36\" 192.168.13.44:8089 192.168.13.43 - - [25/May/2018:13:15:49 +0900] \"POST /sysmaster/admin/requestDummy.action HTTP/1.1\" 200 2 5";
		
		//String data ="\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36\" \"POST /sysmaster/admin/requestDummy.action HTTP/1.1\" 192.168.13.44:8089 192.168.13.43 - - [25/May/2018:13:15:49 +0900] 200 2 5";
		
		String data =" 192.168.13.44:8089 \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36\" 192.168.13.43 - - [25/May/2018:13:15:49 +0900] \"POST /sysmaster/admin/requestDummy.action HTTP/1.1\" 200 2 5";
		
		init();
		
		process(data);

	}
	
	private static void process(String data) { // processLine ()에 들어갈 부분
		HashMap<String,String> accesslog = new HashMap<String, String>();//C: 필요한 정보 - 실데이터 (B를 이용해 담는다)
		
	    int index = 0; //data의 index 관리를 위함
	    data = data.trim();
	    
	    StringBuffer sb = new StringBuffer(" ");
	    sb.append(data);
	    
	    data = sb.toString();
	    
	    for(Separator separator : separators){
	      index = util(separator, index, data);
	      
	      String format = separator.format;
	      
	      if(format.indexOf(binding) == 0){
	    	  format = format.substring(1, format.length()-1);
	      }
	      
	      String key = rule.get(format);
	      
	      if(key != null) {
	    	  accesslog.put(key, separator.value);
	      }
	    }	
	    
	    if(accesslog.size() != 9){
	    	System.out.println("format error");
	    }
	    
	    for(Map.Entry<String, String> entry: accesslog.entrySet()){
			System.out.print(entry.getKey()+": "+entry.getValue());
			System.out.println();
		}    
	    
	}

	private static void init() { // process 메소드에서 logformat을 얻어서 처리 
		
		//String format = "%{host}i %h %l %u %t \"%r\" %s %b %D";
		//String format = "%{host}i %h %t \"%r\" %s %b %D \"%{User-Agent}i\"";
		//String format = "%{host}i %h %l %u %t \"%r\" %s %b %D \"%{User-Agent}i\"";
		//String format = "\"%{User-Agent}i\" %{host}i %h %l %u %t \"%r\" %s %b %D";
		//String format = "\"%{User-Agent}i\" \"%r\" %{host}i %h %l %u %t %s %b %D";
		String format = " %{host}i \"%{User-Agent}i\" %h %l %u %t \"%r\" %s %b %D";
		
		StringTokenizer st = new StringTokenizer(format, format_opt);
		
		int n= st.countTokens();
		
		separators = new ArrayList<Separator>();
		
		for(int i=0; i<n; i++){
			String token = st.nextToken();
			
			if(token.indexOf("%t")  != -1){
				separators.add(new Separator(token, "[", "]"));
			} else if(token.indexOf(binding) == 0){
				separators.add(new Separator(token, binding, binding));
			} else {
				separators.add(new Separator(token, format_opt, format_opt));
			}
		}
	}

	public static int util(Separator separator, int index, String data) { //StringUtil에 만들기
		
		
	    if(separator.inDeliminator.equals(binding)){
	    	index = index+1;
	    }
		
	    int startIndex = index;
	      
	    Boolean start = false;
	    for(int j=index; j<data.length(); j++){
	    	
	    	   if(String.valueOf(data.charAt(j)).equals(separator.outDeliminator) && start){
		    	    index = j;
		    	    break;
		       }

   		       if(String.valueOf(data.charAt(j)).equals(separator.inDeliminator) && !start){
				   startIndex = j+1;
				   start = true;
			   }
   		       
   		     
  		  }
	    		   
	    if(index < startIndex){//마지막 문자 찾을 때(포맷을 제대로 썼을 때 space를 못 찾을 때는 마지막의 경우밖에 없을 테니까)
      	  index = data.length();
        }
	    
	    separator.value = data.substring(startIndex, index);  
	    
	    //String checkValue = separator.value;
		        
        return index;
	}

}


class Factory{
	
	
	
}


class Separator{
	
	String format;
	String inDeliminator;
	String outDeliminator;
	String value;
	
	public Separator(String iformat, String fDelim, String lastDelim){
		this.format = iformat;
		this.inDeliminator = fDelim;
		this.outDeliminator = lastDelim;
	}
	
}
