package basic;

import java.util.HashMap;

public class Tmax1 {

	public static void main(String[] args) {
		
		String format = "%{host}i %h %l %u %t \"%r\" %s %b %D";
		String format2 = "%{host}i %h %t \"%r\" %s %b $D \"%{User-Agent}i\"";
		String data = "192.168.13.44:8080 192.168.13.43 - - [18/May/2018:13:41:51 +0900] \"POST /sysmaster/admin/requestDummy.action HTTP/1.1\" 200 2 4";
		String data2="192.168.13.44:8080 192.168.13.43 - - [02/May/2018:15:26:42 +0900] \"GET /sysmaster/base/login.action HTTP/1.1\" 200 6309 114 \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36\"";
		//[nodeName=claire-System-Product-Name, virtualHost=192.168.13.44:8080, vhostCode=-710490531, remoteHost=192.168.13.43, remoteLog=-, user=-, logDate=20180518134151, method=POST, requestUri=/sysmaster/admin/requestDummy.action, uriCode=1441896060, httpVer=HTTP/1.1, status=200, bytes=2, respTime=4];
		
		HashMap<String,String> rule = new HashMap<String, String>()
				{{
					put("a", "");
					put("b", "");
					put("c", "");
					put("C", "\"");
					put("d", "");
					put("D", "");
					put("}e", "");
					put("g", "");
					put("h", "");
					put("H", "");
					put("i", "\"");
					put("J", "\"");
					put("m", "");
					put("p", "");
					put("q", "");
					put("r", "");
					put("R", "");
					put("s", "");
					put("t", "");
					put("T", "");
					put("u", "");
					put("U", "");
					put("v", "");
					put("z", "");
				}};
		
		HashMap<String, String> divisor = new HashMap<String, String>();		
				
		HashMap<String,String> divided = new HashMap<String, String>();
		
		
		for(int i=0; i<format.length()-1; i++){
			if(format.charAt(i) == '%' && format.charAt(i+1) != '{'){
				divisor.put(String.valueOf(format.charAt(i)), String.valueOf(format.charAt(i+2)));
			}else if(format.charAt(i) == '%' && format.charAt(i+1) == '{'){
				int j=0;
				for(j=i; j<format.length(); j++){
					if(format.charAt(j)=='}'){
						
					  if(j+2 <= format.length()){	
						divisor.put(String.valueOf(format.charAt(j+1)), String.valueOf(format.charAt(j+2)));
					  }	
					}
				}
				
				i=j+1;
			}
		}
		

		
	for(int l=0; l<divisor.size(); l++){	
		int k=0; 
		for(k=0; k<data.length(); k++){
			
			
			
		}
	}	
		
	}

}
