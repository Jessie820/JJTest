package basic;

import java.util.HashMap;

public class Tmax4 {
	
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
		/*
		 * %가 몇 개인지 센다. 1.순서 & 2.해당하는 의미 를 HashMap에 담는다.
		 * 그냥 구분자로 가능한 것들을 몇 개 정해주고 그 몇 개에 해당하는 문자를 만나면 거기서 한 단어를 정한다...?
		*/
		//String format = "%{host}i %h %l %u %t \"%r\" %s %b %D";
		//String format = "%{host}i %h %t \"%r\" %s %b $D \"%{User-Agent}i\"";
		String format = "%{AGENT_HOME}e \"%r\" %s %b %{host}i %l %h %D %u %t";
		String[] format_opt = {" "};
		//String data = "192.168.13.44:8080 192.168.13.43 - - [18/May/2018:13:41:51 +0900] \"POST /sysmaster/admin/requestDummy.action HTTP/1.1\" 200 2 4";
		//String data="192.168.13.44:8080 192.168.13.43 \"[02/May/2018:15:26:42 +0900]\" \"GET /sysmaster/base/login.action HTTP/1.1\" 200 6309 114 \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36\"";
		String data="/home/claire/agent \"GET /sysmaster/resource/base/script/bundle/base/Messages.properties?_=1527074918500 HTTP/1.1\" 200 2722 192.168.13.44:8089 - 192.168.13.43 0 - [23/May/2018:20:29:14 +0900]";
		
		
		/*일단 format을 스페이스로 자르고 format 갯수대로 for문 돌면서 
		 * 1. format 이 필요한지 아닌지 체크
		 * 2.5. format이 "로 시작하는지 체크 "안 붙었으면 - 
		 * 2. 안에서 for문 돌려서 data에서 space 체크 첫번째 space 인 index 확인해서 그 전까지 substring으로 잘라서 최종 map에 보관 / 안 필요한 아이면 그냥 index 만 갖고 있기
		 * 2.7. format이 "로 시작하면  data에서 space 가 아니라 두번째 " 시작할 때까지 찾아서 substring 으로 잘라서 최종 map 에 보관 / 안 필요한 아이면 그냥 index만 갖고 있기
         */		
 
	}

}
