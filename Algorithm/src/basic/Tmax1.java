package basic;

import java.util.HashMap;

public class Tmax1 {

	
	static HashMap<String,String> rule = new HashMap<String, String>() //A: 필요한 포맷 - 포맷에 해당하는 정보
	{{
		put("{host}i", "virtualHost");
		put("h", "remoteHost");
		put("l", "remoteLog");
		put("u", "user");
		put("t", "logDate");
		put("r", "mrh");
		put("s", "status");
		put("b", "bytes");
		put("D", "respTime");

	}};
	
	public static void main(String[] args) {
		
		//제한: 문자열 사이에 구분(스페이스 나 특수문자여야 할 듯)이 있어야 하고 안에 공백의 여지가 있는 것들은 ""로 묶어야 한다.
		
		String format = "%{host}i %h %l %u %t \"%r\" %s %b %D";
		String format2 = "%{host}i %h %t \"%r\" %s %b $D \"%{User-Agent}i\"";
		String data = "192.168.13.44:8080 192.168.13.43 - - [18/May/2018:13:41:51 +0900] \"POST /sysmaster/admin/requestDummy.action HTTP/1.1\" 200 2 4";
		String data2="192.168.13.44:8080 192.168.13.43 - - [02/May/2018:15:26:42 +0900] \"GET /sysmaster/base/login.action HTTP/1.1\" 200 6309 114 \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36\"";
		//[nodeName=claire-System-Product-Name, virtualHost=192.168.13.44:8080, vhostCode=-710490531, remoteHost=192.168.13.43, remoteLog=-, user=-, logDate=20180518134151, method=POST, requestUri=/sysmaster/admin/requestDummy.action, uriCode=1441896060, httpVer=HTTP/1.1, status=200, bytes=2, respTime=4];

		
		HashMap<String, String> divisor = new HashMap<String, String>();//B: 필요한 정보 - 구분자 (A를 이용해 담는다)		
				
		HashMap<String,String> divided = new HashMap<String, String>();//C: 필요한 정보 - 실데이터 (B를 이용해 담는다)

		
		for(int i=0; i<format.length()-1; i++){

			char point;
			if(format.charAt(i) == '%' && format.charAt(i+1) != '{'){
				point = format.charAt(i+1);
				if(rule.get(rule) != null){
				     divisor.put(rule.get(rule), String.valueOf(format.charAt(i+2)));
				}else{
					divisor.put("trash", String.valueOf(format.charAt(i+2)));
				}
		
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
		

		
	}

}
