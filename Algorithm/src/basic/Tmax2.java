package basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tmax2 {

	public static void main(String[] args) {
		
		//master 브랜치
		
		//브랜치 테스트
		String data = "192.168.13.44:8080 192.168.13.43 - - [18/May/2018:13:41:51 +0900] \"POST /sysmaster/admin/requestDummy.action HTTP/1.1\" 200 2 4";
		String data2="192.168.13.44:8080 192.168.13.43 - - [02/May/2018:15:26:42 +0900] \"GET /sysmaster/base/login.action HTTP/1.1\" 200 6309 114 \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36\"";

	    String timeRegex = "\\[([0-9]{1,2}\\/[a-zA-Z]{3,5}\\/[0-9]{4}\\:[0-9]{2}\\:[0-9]{2}\\:[0-9]{2}) \\+0900\\]"; 
		Pattern pattern = Pattern.compile(timeRegex);
		Matcher matcher = pattern.matcher(data2);
		
		while(matcher.find()){
			
			System.out.println(matcher.group());
		}
		
	}

}
